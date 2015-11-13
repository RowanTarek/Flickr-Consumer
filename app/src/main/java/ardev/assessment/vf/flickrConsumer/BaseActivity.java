package ardev.assessment.vf.flickrConsumer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONObject;

import java.util.ArrayList;

import ardev.assessment.vf.flickrConsumer.BEHandler.FlickrHandler;
import ardev.assessment.vf.flickrConsumer.adapters.ItemsAdapter;
import ardev.assessment.vf.flickrConsumer.listeners.OnRequestCompletedListener;
import ardev.assessment.vf.flickrConsumer.model.FlickrImage;
import ardev.assessment.vf.flickrConsumer.model.FlickrResponse;
import ardev.assessment.vf.flickrConsumer.utils.AppLog;


public class BaseActivity extends AppCompatActivity implements OnRequestCompletedListener {

    protected FlickrHandler flicker;
    protected RecyclerView itemsRecList;
    protected ProgressBar recyclerProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initialize() {
        itemsRecList = (RecyclerView) findViewById(R.id.itemsRecycler);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        itemsRecList.setLayoutManager(recyclerLayoutManager);
        recyclerProgress = (ProgressBar) findViewById(R.id.recyclerProgress);

        flicker = new FlickrHandler();

    }//end initialize

    protected void populateImages(ArrayList<FlickrImage> images){
        ItemsAdapter adapter = new ItemsAdapter(images);
        itemsRecList.setAdapter(adapter);
    }//end populateView


    @Override
    public void onSuccess(Object response) {

        recyclerProgress.setVisibility(View.GONE);
        FlickrResponse flickerResponse = flicker.parseResponse((JSONObject) response);
        if(flickerResponse.isOk()){
            populateImages(flickerResponse.getImages());
        }else{
            //TODO display error message
        }
    }//end onSuccess

    @Override
    public void onFail(Exception ex) {
        recyclerProgress.setVisibility(View.GONE);

        AppLog.d("", "flickr call failed" + ex.getMessage());

    }
}//end BaseActivity
