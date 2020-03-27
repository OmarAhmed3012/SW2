package com.example.android;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class SignUp extends AppCompatActivity {

    Toolbar toolbar;
    EditText email,password,name,con_password;
    ImageButton signup;
    Button signin;
    RequestQueue requestQueue ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        con_password = (EditText) findViewById(R.id.confirm_password);
        name = (EditText)findViewById(R.id.name);
        requestQueue = Volley.newRequestQueue(this);

        signup =(ImageButton)findViewById(R.id.signup);

        signin = (Button) findViewById(R.id.signin);


        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals("")|| password.getText().toString().equals("") ||
                        name.getText().toString().equals("")){
                    Toast.makeText(SignUp.this,"you must complete filed",Toast.LENGTH_LONG).show();
                }
                else {
                    if (password.getText().toString().equals(con_password.getText().toString())){
                        register(name.getText().toString(),email.getText().toString(),password.getText().toString());
                    }
                    else {
                        Toast.makeText(SignUp.this,"Confirm password not same ",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,SignUp.class);
                startActivity(i);
            }
        });


    }

    private void register(String s_name ,String s_email ,String s_password){
        String u = "https://aboelwafa-taskmanagerapi-vtwo.herokuapp.com/users";
//                "http://10.0.2.2:3000/weather?address=giza";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",s_name);
            jsonObject.put("email",s_email);
            jsonObject.put("password",s_password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest signup = new JsonObjectRequest(Request.Method.POST, u,jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String s = response.getString("token");
                    Log.i("JJson",s+" ");
                    clear();

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("JJson","OMAR catch");

                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUp.this,"password weak or Email exit",Toast.LENGTH_LONG).show();
                error.printStackTrace();
                Log.i("JJson","OMAR Error "+error);
            }
        });
        requestQueue.add(signup);

    }

    private void clear() {
        password.setText("");
        name.setText("");
        email.setText("");
        con_password.setText("");
        Toast.makeText(SignUp.this,"user Added",Toast.LENGTH_LONG).show();
    }

}