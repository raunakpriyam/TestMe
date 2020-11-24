package com.example.testme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText usn,sem,sec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        Button button = (Button)findViewById(R.id.start);
        usn=(EditText)findViewById(R.id.usntext);
        sem=(EditText)findViewById(R.id.semtext);
        sec=(EditText)findViewById(R.id.sectext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(usn.getText().toString()) || TextUtils.isEmpty(sem.getText().toString()) || TextUtils.isEmpty(sec.getText().toString()) ) {
                    Toast.makeText(MainActivity.this,"Enter the details",Toast.LENGTH_SHORT).show();

                }else{
                    addItemtosheet();
                    Intent intent = new Intent(MainActivity.this, quesActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void addItemtosheet() {
        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");
        final String usnvar = usn.getText().toString().trim();
        final String semvar = sem.getText().toString().trim();
        final String secvar = sec.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbx8MtrFSqWG89s-8WysGZR8bgcuvcH0VDxPsJxNggpwUTHf524/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addItem");
                parmas.put("USN",usnvar);
                parmas.put("SEM",semvar);
                parmas.put("SEC",secvar);

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }

}