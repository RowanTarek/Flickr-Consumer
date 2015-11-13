package ardev.assessment.vf.flickrConsumer.BEHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ardev.assessment.vf.flickrConsumer.model.FlickrImage;
import ardev.assessment.vf.flickrConsumer.model.FlickrResponse;

/**
 * Created by RowanTarek on 10/11/2015.
 */
public class FlickrHandler {
    private final String BASE_URL = "https://api.flickr.com/services/rest/?method=";
    private final String THUMB_URL = "https://farm%s.staticflickr.com/%s/%s_%s_t.jpg";
    private   final String FLICKR_API_KEY = "68b03b8f546186e0438fd288af8f018d";
    private  final String FLICKR_APP_SECRET = "b8cc33a9b610cb70";

    private enum FlickrMethods {
        SEARCH("flickr.photos.search"),
        RECENT("flickr.photos.getRecent"),
        USER_PHOTOS("flickr.people.getPhotos")
        ;

        private final String methodUrl;
        FlickrMethods(final String text) {
            this.methodUrl = text;
        }
        @Override
        public String toString() {
            return methodUrl;
        }
    }//end enum FlickrMethods

    private enum UrlParameters {
        API_KEY("&api_key="),
        PAGE_SIZE("&per_page="),
        PAGE_NUMBER("&page="),
        RESPONSE_FORMAT("&format="),
        NO_JSON_CALLBACK("&nojsoncallback="),
        SEARCH_TEXT("&text="),
        USER_ID("&user_id=")
        ;


        private final String param;
        UrlParameters(final String text) {
            this.param = text;
        }
        @Override
        public String toString() {
            return param;
        }
    }//end enum url params

    public String constructSearchRequestUrl(String searchQuery){
        return (BASE_URL + FlickrMethods.SEARCH
                + UrlParameters.API_KEY + FLICKR_API_KEY
                + UrlParameters.SEARCH_TEXT + searchQuery
                + UrlParameters.RESPONSE_FORMAT + "json"
                + UrlParameters.NO_JSON_CALLBACK + 1);
    }//end constructSearchReq


    public String constructRecentRequestUrl(){

        return (BASE_URL + FlickrMethods.RECENT
                + UrlParameters.API_KEY + FLICKR_API_KEY
                + UrlParameters.RESPONSE_FORMAT + "json"
                + UrlParameters.NO_JSON_CALLBACK + 1);
    }//end constructRecentRequest

    public String constructUserPhotosRequestUrl(String userId){
        return (BASE_URL + FlickrMethods.USER_PHOTOS
                + UrlParameters.API_KEY + FLICKR_API_KEY
                + UrlParameters.USER_ID + userId
                + UrlParameters.RESPONSE_FORMAT + "json"
                + UrlParameters.NO_JSON_CALLBACK + 1);
    }//end constructSearchReq

    public FlickrResponse parseResponse(JSONObject response){
        FlickrResponse flickrResponse = new FlickrResponse();
        try {
            flickrResponse.setIsOk(response.getString("stat"));
            if(flickrResponse.isOk()){
                flickrResponse.setImages(parseImages(response.getJSONObject("photos").getJSONArray("photo")));
            }else{
                flickrResponse.setErrorMessage(response.getString("message"));
            }//end else --> failed to retrieve data
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return flickrResponse;
    }//end parseFlickrResponse

    private ArrayList<FlickrImage> parseImages(JSONArray jsonImagesArr){
        final int imagesCount = jsonImagesArr.length();
        ArrayList<FlickrImage> imagesList = new ArrayList<FlickrImage>(imagesCount);
        for(int counter=0 ; counter<imagesCount ; counter++){
            try {
                FlickrImage curImage = new FlickrImage();
                JSONObject jsonCurImage = jsonImagesArr.getJSONObject(counter);
                curImage.setId(jsonCurImage.getString("id"));
                curImage.setSecret(jsonCurImage.getString("secret"));
                curImage.setServerId(jsonCurImage.getString("server"));
                curImage.setFarmId(jsonCurImage.getString("farm"));
                curImage.setTitle(jsonCurImage.getString("title"));
                curImage.setOwner(jsonCurImage.getString("owner"));
                String thumbUrl = String.format(THUMB_URL,
                        curImage.getFarmId(), curImage.getServerId() , curImage.getId(), curImage.getSecret());
                curImage.setImageUrl(thumbUrl);
                imagesList.add(curImage);
            } catch (JSONException e) {
                e.printStackTrace();
            }//end catch
        }//end loop
        return imagesList;
    }//end parseImages
    /*
// dfd0c774e843da3c8bf2aada4bccc0ce&format=rest

    public static final String FLICKR_SEARCH_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search";*/
}//end class FlickrHandler
