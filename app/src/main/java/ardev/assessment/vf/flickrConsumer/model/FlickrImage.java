package ardev.assessment.vf.flickrConsumer.model;

/**
 * Created by RowanTarek on 10/11/2015.
 */
public class FlickrImage {
    private String id, serverId, farmId, secret, title, owner;  //from response
    private String imageUrl; //built in app

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getServerId() {
        return serverId;
    }

    public String getFarmId() {
        return farmId;
    }

    public String getSecret() {
        return secret;
    }

    public String getOwner() {
        return owner;
    }
}
