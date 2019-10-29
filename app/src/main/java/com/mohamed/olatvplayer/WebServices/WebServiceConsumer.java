package com.mohamed.olatvplayer.WebServices;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mohamed.olatvplayer.MainApplication.App;

public class WebServiceConsumer {
    private static final String TAG = "WebServiceConsumer";
    private static  WebServiceConsumer ourInstance = null ;
    private Context baseContext;
    public static WebServiceConsumer getInstance() {
        if(ourInstance == null)
           WebServiceConsumer.ourInstance  = new WebServiceConsumer(App.instance.getBaseContext());
        return ourInstance;
    }

    private WebServiceConsumer(Context baseContext) {
        this.baseContext = baseContext ;

    }
    public void callWebService(String url, final onRawDataRecived callBack){
        Log.d(TAG, "calling WebService using URL: " + url);
        RequestQueue queue = Volley.newRequestQueue(this.baseContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);
                        callBack.doWork(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "OnError: " + error.networkResponse);
            }
        });
        queue.add(stringRequest);
    }





}
