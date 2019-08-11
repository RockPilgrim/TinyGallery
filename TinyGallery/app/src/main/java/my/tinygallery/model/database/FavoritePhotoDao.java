package my.tinygallery.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FavoritePhotoDao {

    @Query("SELECT * FROM favorite_table")
    Single<List<FavoritePhoto>> getAllPhoto();

    @Insert
    long addPhoto(FavoritePhoto photo);

    @Query("DELETE FROM favorite_table WHERE url =:url")
    int deleteByUrl(String url);

    @Delete
    int deletePhoto(FavoritePhoto photo);

    @Update
    int updatePhoto(FavoritePhoto photo);
}
