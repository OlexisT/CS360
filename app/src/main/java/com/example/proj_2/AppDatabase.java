package com.example.proj_2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.proj_2.dao.ItemDao;
import com.example.proj_2.dao.UserDao;
import com.example.proj_2.entities.Item;
import com.example.proj_2.entities.User;

@Database(entities = {Item.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
    public abstract UserDao userDao();

}