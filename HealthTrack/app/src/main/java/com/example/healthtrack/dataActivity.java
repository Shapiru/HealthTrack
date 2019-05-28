package com.example.healthtrack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class dataActivity extends AppCompatActivity {
 EditText lusername,lpassword;
 String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        lusername=(EditText)findViewById(R.id.user);

        lpassword=(EditText)findViewById(R.id.password);
    }

    public void Login(View view) {
        username=lusername.getText().toString().trim();
        password=lpassword.getText().toString().trim();

        if (username.isEmpty()){
            lusername.setError("Enter username");
        }
        else if (password.isEmpty()){
            lpassword.setError("Enter Password");


        }else {
            Login();
        }
    }

    private void Login() {
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Logging in");
        dialog.setMessage("Please wait....");
        dialog.setIcon(R.drawable.medbg5);
        dialog.show();

        String url="http://10.3.34.48/healthtrack/signin.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                Toast.makeText(dataActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(dataActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String>params=new HashMap<String, String>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



    }
}




