package my.tinygallery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {

    @SerializedName("totalHits")
    @Expose
    private int totalPics;

    @SerializedName("hits")
    @Expose
    private List<Hit> hitList;

    public int getTotalPics() {
        return totalPics;
    }

    public void setTotalPics(int totalPics) {
        this.totalPics = totalPics;
    }

    public List<Hit> getHitList() {
        return hitList;
    }

    public void setHitList(List<Hit> hitList) {
        this.hitList = hitList;
    }
}
