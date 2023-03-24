package com.example.clothingregistration.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.clothingregistration.database.entitas.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("INSERT INTO user (name, brand, year, price) VALUES(:name, :brand, :year, :price)")
    void insertAll(String name, String brand, String year, String price);

    @Query("UPDATE user SET name=:name, brand=:brand, year=:year, price=:price WHERE uid=:uid")
    void update(int uid, String name, String brand, String year, String price);

    @Query("SELECT * FROM user WHERE uid=:uid")
    User get(int uid);

    @Delete
    void delete(User user);
}