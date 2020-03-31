package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText email,password;
    CheckBox checkBox;
    ImageButton signin;
    Button signup;
    RequestQueue LoginRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        password.setShowSoftInputOnFocus(true);
        checkBox = (CheckBox)findViewById(R.id.checkbox);

        signin =(ImageButton)findViewById(R.id.signin);

        signup = (Button) findViewById(R.id.signup);

        LoginRequest = Volley.newRequestQueue(this);

        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Sign In Button Clicked",Toast.LENGTH_LONG).show();
                if (email.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Email is empty",Toast.LENGTH_SHORT).show();
                }
                else if (password.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"password is empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Login(email.getText().toString(),password.getText().toString());
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SignUp.class);
                startActivity(i);
            }
        });
    }

    private void Login(String email,String password){
        String LoginURL = "http://10.0.2.2:3000/users/login";
        JSONObject user = new JSONObject();
        Log.i("LOGINNN",password);
        try {
            user.put("email",email);
            user.put("password",password);
            Log.i("LOGINNN",email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest login = new JsonObjectRequest(Request.Method.POST, LoginURL, user, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject use = response.getJSONObject("user");
                    Log.i("LOGINNN",response.getString("token"));
                    Intent intent = new Intent(MainActivity.this,Home.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"Welcome "+use.getString("name"),Toast.LENGTH_LONG).show();
                    finish();
                } catch (Exception e) {
                    Log.i("LOGINNN","Catch");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOGINNN","Error");
                Toast.makeText(MainActivity.this,"Error in Email or Password ",Toast.LENGTH_LONG).show();

            }
        });
        Log.i("LOGINNN","Finish");
        LoginRequest.add(login);
    }
}
