package com.example.proj_2.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proj_2.entities.User;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UserDao {


    @Query("SELECT * FROM user WHERE username = (:username) AND password = (:password)")
    Single<User> login(String username, String password);

    @Insert
    Completable insert(User user);

}
