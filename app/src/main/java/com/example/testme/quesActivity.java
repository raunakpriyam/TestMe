package com.example.testme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class quesActivity extends AppCompatActivity {
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);
        mQueue= Volley.newRequestQueue(this);
        jsonParse();
        Button submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //operation


            }
        });


    }
    private void jsonParse(){
        String url="https://script.googleusercontent.com/macros/echo?user_content_key=1FE11Jlr4EZGov07k1cJIjCqdYqhPA2xVVRHs74KRwwpJevg7W_UKw5fJafwdfKsR3FR4RVAJNaFLn1-4w-2qE3mjzsNuQ-2OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUzx1qJeNhDPC0ot0ZmnGR2QeK2ePn8F22rU_KuR8TVlnjdjzMAAwvTDHIgkvOLUTN5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("Sheet1");
                            final ArrayList<eachquestion> questions= new ArrayList<eachquestion>();
                            Random rand = new Random();

                            for(int i=0;i<10;i++){
                                int n=rand.nextInt(26);
                                JSONObject q=jsonArray.getJSONObject(n);
                                int ques_num=q.getInt("Question_No.");
                                String ques=q.getString("Question");
                                String Option_A=q.getString("Option_A");
                                String Option_B=q.getString("Option_B");
                                String Option_C=q.getString("Option_C");
                                String Option_D=q.getString("Option_D");
                                int answer=q.getInt("Answer");
                                questions.add(new eachquestion(String.valueOf(ques_num),ques,Option_A,Option_B,Option_C,Option_D,String.valueOf(answer)));

                            }
                            questionAdapter adapter = new questionAdapter(quesActivity.this, questions);
                            ListView listView = (ListView) findViewById(R.id.list);
                            listView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);


    }

}