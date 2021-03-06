package ardev.assessment.vf.flickrConsumer.BEHandler;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import ardev.assessment.vf.flickrConsumer.listeners.OnRequestCompletedListener;
import ardev.assessment.vf.flickrConsumer.utils.AppLog;

/**
 * Created by RowanTarek on 10/11/2015.
 */
public class VolleyRequester {
    private  final RequestQueue queue ;
    private  static VolleyRequester _instance;
    private final String LOG_TAG = "VolleyRequester";

    private VolleyRequester(Context ctx){
        queue = Volley.newRequestQueue(ctx);
    }//end constructor

    public static VolleyRequester getInstance(Context ctx){
        return _instance == null ? new VolleyRequester(ctx) :  _instance;
    }//end initialize

    public void requestJsonNwCall(String url, final OnRequestCompletedListener requestCompletedListener){
        AppLog.d(LOG_TAG, "in requestJsonNwCall with url = " + url) ;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        AppLog.d(LOG_TAG, "in requestJsonNwCall with response = " + response) ;
                        requestCompletedListener.onSuccess(response);
                    }//end onResponse
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AppLog.d(LOG_TAG, "in requestJsonNwCall failed = " + error.getMessage()) ;
                        requestCompletedListener.onFail(error);
                    }//end onError
                });

        queue.add(jsObjRequest);
    }//end requestJsonNwCall

}//end VolleyRequester
