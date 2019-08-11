package my.tinygallery.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = FavoritePhoto.class, version = 1, exportSchema = false)
public abstract class PhotoDatabase extends RoomDatabase {
    public abstract FavoritePhotoDao photoDao();
}
