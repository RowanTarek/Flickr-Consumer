package ardev.assessment.vf.flickrConsumer;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ardev.assessment.vf.flickrConsumer.BEHandler.VolleyRequester;
import ardev.assessment.vf.flickrConsumer.utils.AppLog;
import ardev.assessment.vf.flickrConsumer.utils.Constants;


public class ImagesOfUserActivity extends BaseActivity  {
    private String targetUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recycler);
        try{
            targetUserId = getIntent().getExtras().getString(Constants.TARGET_USER_ID_INTENT_TAG);
            AppLog.d("", "target user id = " + targetUserId);
        }catch(NullPointerException noDataEx){
            Toast.makeText(this, R.string.cantPerformActionMsg , Toast.LENGTH_SHORT).show();
        }
        initialize();
    }//end onCreate

    protected void initialize() {
        super.initialize();
        recyclerProgress.setVisibility(View.VISIBLE);
        VolleyRequester.getInstance(this).requestJsonNwCall(flicker.constructUserPhotosRequestUrl(targetUserId), this);
    }//end initialize

}//end activity
