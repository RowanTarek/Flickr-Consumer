package ardev.assessment.vf.flickrConsumer.listeners;

/**
 * Created by RowanTarek on 10/11/2015.
 */
public interface OnRequestCompletedListener {
    public void onSuccess(Object response);
    public void onFail(Exception ex);
}
