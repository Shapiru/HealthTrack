package com.example.healthtrack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    EditText ed_username,ed_email,ed_pass,ed_cpass;
    String username,email,password,c_password;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ed_username=(EditText)findViewById(R.id.user);
        ed_email=(EditText)findViewById(R.id.email);
        ed_pass=(EditText)findViewById(R.id.password);
        ed_cpass=(EditText)findViewById(R.id.confirm);
        login=(Button)findViewById(R.id.login);
    }

    public void Register(View view) {
        username=ed_username.getText().toString().trim();
        email=ed_email.getText().toString().trim();
        password=ed_pass.getText().toString().trim();
        c_password=ed_cpass.getText().toString().trim();

        if (username.isEmpty()){
            ed_username.setError("Enter username");
        }
        else if (password.isEmpty()){
            ed_pass.setError("Enter Password");
        }else if (c_password.isEmpty()){
            ed_cpass.setError("Confirm password");

        }else {
            RegUser();
        }
    }

    private void RegUser() {
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Health Track Registration");
        dialog.setMessage("Please wait....");
        dialog.setIcon(R.drawable.medbg5);
        dialog.show();

        String url="http://10.3.34.48/healthtrack/insert.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Toast.makeText(HomeActivity.this, response, Toast.LENGTH_SHORT).show();

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(HomeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String>params=new HashMap<String, String>();
                params.put("username",username);
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



    }
    public void signup(View view) {
        Intent data = new Intent(HomeActivity.this, dataActivity.class);
        startActivity(data);
    }
}


