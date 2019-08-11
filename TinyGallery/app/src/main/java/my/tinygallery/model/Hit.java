package my.tinygallery.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit {

    @SerializedName("webformatURL")
    @Expose
    private String url;

    @SerializedName("previewURL")
    @Expose
    private String previewURL;

    private boolean isFavorite;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Hit() {
    }

    public Hit(String url, String previewURL) {
        this.url = url;
        this.previewURL = previewURL;
        isFavorite = false;
    }

    public Hit(String url, String previewURL, boolean isFavorite) {
        this.url = url;
        this.previewURL = previewURL;
        this.isFavorite = isFavorite;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
