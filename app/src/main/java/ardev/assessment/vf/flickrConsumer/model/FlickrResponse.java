package ardev.assessment.vf.flickrConsumer.model;

import java.util.ArrayList;

/**
 * Created by RowanTarek on 10/11/2015.
 */
public class FlickrResponse {
    private final String STATUS_OK = "ok";
    private final String STATUS_FAILED = "fail";
    private boolean isOk;
    private String errorMessage;
    private ArrayList<FlickrImage> images;

    public void setIsOk(String status) {
        isOk = status.equalsIgnoreCase(STATUS_OK);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setImages(ArrayList<FlickrImage> images) {
        this.images = images;
    }

    public boolean isOk() {
        return isOk;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ArrayList<FlickrImage> getImages() {
        return images;
    }
}
