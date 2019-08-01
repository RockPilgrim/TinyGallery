package my.tinygallery.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetApi {
    //    pixabay.com/api/?key=13141951-0300f6e9a2dd12c9e8b28d812
    @GET("api/")
    Observable<Photo> loadImage(@Query("key") String key);
}
