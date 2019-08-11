package my.tinygallery.model.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_table")
public class FavoritePhoto {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String url;
    private String previewURL;

    public FavoritePhoto() {
    }

    @Ignore
    public FavoritePhoto(String url) {
        this.url = url;
    }

    @Ignore
    public FavoritePhoto(String url, String previewURL) {
        this.url = url;
        this.previewURL = previewURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }
}
