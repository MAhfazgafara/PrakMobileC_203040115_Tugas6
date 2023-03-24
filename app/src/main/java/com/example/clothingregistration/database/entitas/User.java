package com.example.clothingregistration.database.entitas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int uid;
    public String name;

    public String brand;

    public String year;

    public String price;
}
