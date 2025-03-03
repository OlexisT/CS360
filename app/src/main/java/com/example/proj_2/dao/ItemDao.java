package com.example.proj_2.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.proj_2.entities.Item;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;


@Dao
public interface ItemDao {



    @Query("SELECT * FROM item ORDER BY itemQuantity ASC")
    Observable<List<Item>> listByQuantity();

    @Query("UPDATE Item SET itemQuantity = itemQuantity + 1 WHERE id = :id")
    Completable incrementById(Integer id);

    @Query("UPDATE Item SET itemQuantity = itemQuantity - 1 WHERE id = :id")
    Completable decrementById(Integer id);

    @Insert
    Completable insert(Item items);

    @Delete
    Completable delete(Item item);


}

