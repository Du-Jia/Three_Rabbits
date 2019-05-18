package com.example.cly.word.login_signUp;


import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cly.word.DataBase;
import com.example.cly.word.GeneralDBHelper;
import com.example.cly.word.MainActivity;
import com.example.cly.word.News;
import com.example.cly.word.R;
import com.example.cly.word.common.Encrypting;
import com.example.cly.word.common.USER_TYPE;
import com.example.cly.word.common.User;

import java.util.ArrayList;
import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;



/*
* 普通用户表：GENERAL_USER
*
* */
public class LoginActivity extends AppCompatActivity {
    /*private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() throws Exception{
        Log.d(TAG, "Login");

      *//*  if (!validate()) {
            onLoginFailed();
            return;
        }*//*

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String user = _emailText.getText().toString();
        String password = Encrypting.getMD5Str(_passwordText.getText().toString());


        // TODO: Implement your own authentication logic here.
        User user1 = getUser(user,password);
        if(user1 != null){
            Intent intent = new Intent();
            intent.putExtra("userId",user1.getUserId());
            intent.putExtra("name",user1.getName());
            if(user1.getType().equals(USER_TYPE.GENERAL))
                intent.setClass(this,MainActivity.class);
            else
                intent.setClass(this,MainActivity.class);          //!!!!!!!
            startActivity(intent);
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    private User getUser(String user_Id,String psd){
        String[] userId = new String[1];
        userId[0] = user_Id;
        GeneralDBHelper dbHelpermDBHelper=new GeneralDBHelper(this);
        SQLiteDatabase db=dbHelpermDBHelper.getWritableDatabase();
        Cursor c;
        c=db.query( DataBase.TABLE_NAME1,null,"userId=?", userId,null,null,null ,null);
        if(c.moveToFirst()){
            String password=c.getString( c.getColumnIndex( "password" ) );
            String name = c.getString( c.getColumnIndex( "name" ) );
            String type=c.getString( c.getColumnIndex( "type" ) );
            USER_TYPE type1 = type=="GENERAL"?USER_TYPE.GENERAL:USER_TYPE.ADMIN;
            if(psd.equals(password)){
                return new User(user_Id,name,psd,type1);
            }
        }
        return null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    *//*public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }*/
}
