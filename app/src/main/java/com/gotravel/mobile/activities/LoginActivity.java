package com.gotravel.mobile.activities;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.accounts.*;
import android.app.DialogFragment;
import android.widget.Toast;

import java.io.IOException;

import com.gotravel.mobile.R;
import com.gotravel.mobile.fragment.SelectAccountDialogFragment;
import com.gotravel.mobile.fragment.SelectAccountDialogFragment.SelectAccountDialogProtocol;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity implements SelectAccountDialogProtocol {

    private static final String TAG = "GoTravel";

    private AccountManager mAccountManager;
    private static final int REQUEST_GET_ACCOUNTS = 2;

    //Button btnLogin;
    Button btnGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //btnLogin = (Button) findViewById(R.id.btnLogin);
        btnGoogleSignIn = (Button) findViewById(R.id.btnGoogleSignIn);
        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(main);
            }
        });*/
        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "btnGoogleSignIn onClick");
                selectAccount();
            }
        });
    }

    @Override
    public void gotAccount(int index) {
        Log.d(TAG, "gotAccount");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "gotAccount - Check Permission Now");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.GET_ACCOUNTS}, REQUEST_GET_ACCOUNTS);
        } else {
            Log.d(TAG, "gotAccount - Permission has been granted");
            Account[] accounts = mAccountManager.getAccountsByType("com.google");
            getTokenForAccount(accounts[index]);
        }

    }

    private void selectAccount() {
        Log.d(TAG, "selectAccount");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "selectAccount - Check Permission Now");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.GET_ACCOUNTS}, REQUEST_GET_ACCOUNTS);
        }else{
            Log.d(TAG, "selectAccount - Permission has been granted");

            mAccountManager = AccountManager.get(this);

            Account[] accounts = mAccountManager.getAccountsByType("com.google");

            Log.d(TAG, "selectAccount - accounts.length = "+accounts.length);
            if (accounts.length == 0) {
                Toast.makeText(getApplicationContext(), R.string.no_account_found_message, Toast.LENGTH_SHORT).show();
            } else if (accounts.length == 1) {
                getTokenForAccount(accounts[0]);
            } else {
                String[] accountNames = new String[accounts.length];

                for (int i = 0; i < accounts.length; i++) {
                    accountNames[i] = accounts[i].name;
                }

                DialogFragment newFragment = SelectAccountDialogFragment.newInstance(R.string.select_account_dialog_title, accountNames);
                newFragment.show(getFragmentManager(), "dialog");
            }
        }

    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        // Exit gracefully by starting OS Home Activity
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

    private void getTokenForAccount(Account account) {
        Log.d(TAG, "getTokenForAccount");
        Bundle options = new Bundle();

        Log.d(TAG,"getTokenForAccount - account.name=" + account.name);

        mAccountManager.getAuthToken(
                account,
                "oauth2:https://www.googleapis.com/auth/userinfo.profile",
                options,
                this,
                new OnTokenAcquired(),
                null
        );
    }

    private class OnTokenAcquired implements AccountManagerCallback<Bundle> {
        @Override
        public void run(AccountManagerFuture<Bundle> result) {
            Log.d(TAG, "OnTokenAcquired run");
            Bundle bundle = null;
            try {
                bundle = result.getResult();

                String token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
                Intent launch = (Intent) result.getResult().get(AccountManager.KEY_INTENT);

                if (launch != null) {
                    startActivityForResult(launch, 0);
                    return;
                }

                requestAPIKeyFromOAuthToken(token);
            } catch (OperationCanceledException e) {
                e.printStackTrace();
            } catch (AuthenticatorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void requestAPIKeyFromOAuthToken(String token) {
        Log.d(TAG, "OnTokenAcquired requestAPIKeyFromOAuthToken");
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams("token", token);


        Intent main = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(main);

        /*client.post("https://gotravel-restful-dev-jhanrojas.c9users.io/auth/verify", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d(TAG, "OnTokenAcquired onSuccess");
                Log.w("OAUTH", "Got API key from server: " + responseBody);
                //finish();
                Intent main = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(main);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "OnTokenAcquired onFailure");
                Log.w("OAUTH", "Error getting API key");
                Toast.makeText(getApplicationContext(), R.string.connection_error_message, Toast.LENGTH_LONG).show();

                error.printStackTrace();
            }
        });*/
    }
}
