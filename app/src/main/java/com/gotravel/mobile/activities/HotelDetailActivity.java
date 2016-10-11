package com.gotravel.mobile.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gotravel.mobile.R;
import com.gotravel.mobile.adapters.RoomAdapter;
import com.gotravel.mobile.adapters.TransitionAdapter;
import com.gotravel.mobile.fragment.dummy.HotelContent;
import com.gotravel.mobile.models.Hotel;
import com.gotravel.mobile.models.Room;
import com.gotravel.mobile.models.TourPackage;
import com.gotravel.mobile.models.TourPackageData;
import com.gotravel.mobile.util.Constants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HotelDetailActivity extends Activity implements View.OnClickListener{

    ArrayList<Room> rooms = new ArrayList<>();

    public static final String EXTRA_PARAM_ID = "hotel_id";
    public static final String EXTRA_PARAM_NAME = "hotel_name";
    public static int PALETTE_COLOR = 0;

    public static final String NAV_BAR_VIEW_NAME = Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME;
    private ListView hotelRoomListView;
    private ImageView hotelPictureImageView;
    private TextView hotelNameTextView;
    private LinearLayout hotelNameHolder;
    private Palette mPalette;
    //private ImageButton btnRateImageButton;
    private FloatingActionButton btnRateFloatingActionButton;
    private Animatable mAnimatable;
    private LinearLayout mRevealView;
    private EditText mEditTextTodo;
    private boolean isEditTextVisible;
    private InputMethodManager mInputManager;
    //private Hotel hotel;
    private ArrayList<String> mTodoList;
    private ArrayAdapter mToDoAdapter;
    int defaultColorForRipple;
    Hotel hotel = null;
    RecyclerView roomRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        int hotelId = getIntent().getIntExtra(EXTRA_PARAM_ID,0);
        String hotelName = getIntent().getStringExtra(EXTRA_PARAM_NAME);

        getHotel(hotelId);

        roomRecyclerView = (RecyclerView) findViewById(R.id.roomRecyclerView);
        hotelPictureImageView = (ImageView) findViewById(R.id.hotelPictureImageView);
        hotelNameTextView = (TextView) findViewById(R.id.hotelNameTextView);
        hotelNameHolder = (LinearLayout) findViewById(R.id.hotelNameHolder);
        //btnRateImageButton = (ImageButton) findViewById(R.id.btnRateImageButton);
        btnRateFloatingActionButton = (FloatingActionButton) findViewById(R.id.btnRateFloatingActionButton);
        mRevealView = (LinearLayout) findViewById(R.id.llEditTextHolder);
        mEditTextTodo = (EditText) findViewById(R.id.etTodo);

        //btnRateImageButton.setImageResource(R.drawable.icn_morph_reverse);
        btnRateFloatingActionButton.setOnClickListener(this);
        defaultColorForRipple = getResources().getColor(R.color.primary_dark);
        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mRevealView.setVisibility(View.INVISIBLE);
        isEditTextVisible = false;

        getRooms(hotelId);
        mTodoList = new ArrayList<>();
        mToDoAdapter = new ArrayAdapter(this, R.layout.row_todo, mTodoList);

        roomRecyclerView.setAdapter(new RoomAdapter(rooms, hotelId,hotelName));

        windowTransition();

    }

    public void getRooms(int idHotel){
        Log.d("GOTRAVEL","getRooms");
        JsonArrayRequest jsonRequest = new JsonArrayRequest(
                Request.Method.GET, Constants.RESTFUL_MAIN_URL+Constants.RESTFUL_HOTELS_GET_ROOMS_PATH_PREFIX+idHotel+Constants.RESTFUL_HOTELS_GET_ROOMS_PATH_SUFFIX, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // the response is already constructed as a JSONObject!

                rooms.clear();
                try {

                    for(int i=0;i<response.length();i++){
                        JSONObject roomJson = response.getJSONObject(i);
                        Room roomTemp = new Room(roomJson.getInt("id"),
                                roomJson.getString("name"),
                                roomJson.getString("room_type"),
                                roomJson.getInt("number_people"),
                                roomJson.getDouble("price"),
                                roomJson.getString("description"),
                                roomJson.getInt("hotel_id"));
                        mTodoList.add(roomTemp.name);
                        rooms.add(roomTemp);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Allow the adapter refresh their content
                roomRecyclerView.getAdapter().notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        Volley.newRequestQueue(this.getApplicationContext()).add(jsonRequest);
    }

    public void getHotel(int idHotel){
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET, Constants.RESTFUL_MAIN_URL+Constants.RESTFUL_HOTELS_GET_PATH+idHotel+".json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // the response is already constructed as a JSONObject!

                try {
                    hotel = new Hotel(response.getInt("id"),response.getString("name"),response.getString("description"), response.getString("pictureUrl"));
                    hotelNameTextView.setText(hotel.name);
                    Picasso.with(getApplicationContext()).load(hotel.pictureUrl).into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            hotelPictureImageView.setImageBitmap(bitmap);
                            colorize(bitmap);
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        Volley.newRequestQueue(this.getApplicationContext()).add(jsonRequest);
    }

    private void windowTransition() {
        getWindow().setEnterTransition(makeEnterTransition());
        getWindow().getEnterTransition().addListener(new TransitionAdapter() {
            @Override
            public void onTransitionEnd(Transition transition) {
                //btnRateImageButton.animate().alpha(1.0f);
                getWindow().getEnterTransition().removeListener(this);
            }
        });
    }

    public static Transition makeEnterTransition() {
        Transition fade = new Fade();
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        return fade;
    }

    private void addToDo(String todo) {
        mTodoList.add(todo);
    }

    private void colorize(Bitmap photo) {
        mPalette = Palette.generate(photo);
        applyPalette();
    }

    private void applyPalette() {
        //getWindow().setBackgroundDrawable(new ColorDrawable(mPalette.getDarkMutedColor(defaultColorForRipple)));
        Constants.HOLDER_BACKGROUND_COLOR = mPalette.getMutedColor(defaultColorForRipple);
        hotelNameHolder.setBackgroundColor(Constants.HOLDER_BACKGROUND_COLOR);
        mRevealView.setBackgroundColor(mPalette.getLightVibrantColor(defaultColorForRipple));
    }

    /*private void applyRippleColor(int bgColor, int tintColor) {
        colorRipple(btnRateImageButton, bgColor, tintColor);
    }*/

    private void colorRipple(ImageButton id, int bgColor, int tintColor) {
        View buttonView = id;
        RippleDrawable ripple = (RippleDrawable) buttonView.getBackground();
        GradientDrawable rippleBackground = (GradientDrawable) ripple.getDrawable(0);
        rippleBackground.setColor(bgColor);
        ripple.setColor(ColorStateList.valueOf(tintColor));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                if (!isEditTextVisible) {
                    revealEditText(mRevealView);
                    mEditTextTodo.requestFocus();
                    mInputManager.showSoftInput(mEditTextTodo, InputMethodManager.SHOW_IMPLICIT);
                    //btnRateImageButton.setImageResource(R.drawable.ic_action_rate);
                    mAnimatable = (Animatable) (btnRateFloatingActionButton).getDrawable();
                    mAnimatable.start();
                } else {
                    addToDo(mEditTextTodo.getText().toString());
                    mToDoAdapter.notifyDataSetChanged();
                    mInputManager.hideSoftInputFromWindow(mEditTextTodo.getWindowToken(), 0);
                    hideEditText(mRevealView);
                    //btnRateImageButton.setImageResource(R.drawable.icn_morph_reverse);
                    mAnimatable = (Animatable) (btnRateFloatingActionButton).getDrawable();
                    mAnimatable.start();
                }
        }
    }

    private void revealEditText(LinearLayout view) {
        int cx = view.getRight() - 30;
        int cy = view.getBottom() - 60;
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        view.setVisibility(View.VISIBLE);
        isEditTextVisible = true;
        anim.start();
    }

    private void hideEditText(final LinearLayout view) {
        int cx = view.getRight() - 30;
        int cy = view.getBottom() - 60;
        int initialRadius = view.getWidth();
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });
        isEditTextVisible = false;
        anim.start();
    }

    @Override
    public void onBackPressed() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(100);
        btnRateFloatingActionButton.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btnRateFloatingActionButton.setVisibility(View.GONE);
                finishAfterTransition();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }}
