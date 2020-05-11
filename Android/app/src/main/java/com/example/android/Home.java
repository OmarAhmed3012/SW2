package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.model.USER;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.dr_home)
    DrawerLayout layout;
    private ActionBarDrawerToggle toggle;
    @BindView(R.id.nav_view)
    NavigationView nav;
    private RequestQueue requestLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setTitle("Home");
        requestLogout = Volley.newRequestQueue(this);
        toggle = new ActionBarDrawerToggle(this, layout, R.string.open, R.string.close);
        layout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav.setNavigationItemSelectedListener(this);

        nav.setNavigationItemSelectedListener(this);
        View headerView = nav.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.name);
        navUsername.setText(USER.getName());
        Log.i("LOGINNN",USER.getName());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case (R.id.logout):
                    logout();
        }
        return false;
    }

    private void logout() {
        String url = "http://10.0.2.2:3000/users/logout";
        int added = 0;
        JsonObjectRequest logout = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("LOGINNN","12345");
                    Toast.makeText(Home.this, "Logout done", Toast.LENGTH_SHORT).show();
                    switchActivity();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            //Header https
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", USER.getToken());
                return params;
            }
        };
        requestLogout.add(logout);
        Toast.makeText(Home.this, "Logout done", Toast.LENGTH_SHORT).show();
        switchActivity();
    }

    private void switchActivity (){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void showPopup(View view) {
    }
}
