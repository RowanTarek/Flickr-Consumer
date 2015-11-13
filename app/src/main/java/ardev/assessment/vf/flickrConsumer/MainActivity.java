package ardev.assessment.vf.flickrConsumer;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import ardev.assessment.vf.flickrConsumer.BEHandler.VolleyRequester;
import ardev.assessment.vf.flickrConsumer.listeners.OnItemClickListener;
import ardev.assessment.vf.flickrConsumer.utils.Constants;

public class MainActivity extends BaseActivity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        initialize();
        handleSearchIntent(getIntent());
    }//end onCreate

    @Override
    protected void onNewIntent(Intent intent) {
        /*from developer site: (see <a href="http://developer.android.com/guide/topics/search/search-dialog.html"></a>)
        * When the system calls onNewIntent(Intent), the activity has not been restarted,
        * so the getIntent() method returns the same intent that was received with onCreate().
        * This is why you should call setIntent(Intent) inside onNewIntent(Intent) (
        * so that the intent saved by the activity is updated in case you call getIntent() in the future).*/
        setIntent(intent);
        handleSearchIntent(intent);
    }//end onNewIntent


    private void handleSearchIntent(Intent searchIntent){
        //if started via search
        // Get the intent, verify the action and get the query
        if (Intent.ACTION_SEARCH.equals(searchIntent.getAction())) {
            String query = searchIntent.getStringExtra(SearchManager.QUERY);
             findViewById(R.id.imgSearchView).clearFocus();
            recyclerProgress.setVisibility(View.VISIBLE);
            VolleyRequester.getInstance(this).requestJsonNwCall(flicker.constructSearchRequestUrl(query), this);
        }
    }//end handleSearchIntent

    protected void initialize(){
       super.initialize();

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.imgSearchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false);
        searchView.clearFocus();

        recyclerProgress.setVisibility(View.VISIBLE);
        VolleyRequester.getInstance(this).requestJsonNwCall(flicker.constructRecentRequestUrl(), this);
    }//end initialize


    @Override
    public void onItemClick(String ownerId) {
        Intent detailsIntent = new Intent(this, ImagesOfUserActivity.class);
        detailsIntent.putExtra(Constants.TARGET_USER_ID_INTENT_TAG, ownerId);
        startActivity(detailsIntent);
    }
}//end mainActivity
