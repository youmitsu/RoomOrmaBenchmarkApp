package com.example.mitsuhori_y.roomormabenchmarkapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.mitsuhori_y.roomormabenchmarkapp.room.RmDatabase;

/**
 * Created by mitsuhori_y on 2017/10/17.
 */

public class App extends Application {

    public static App INSTANCE;
    private RmDatabase roomDB;

    private final String DATABASE_NAME = "RoomDB2";

    public static App get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        roomDB = Room.databaseBuilder(getApplicationContext(), RmDatabase.class, DATABASE_NAME).build();

        INSTANCE = this;
    }

    public RmDatabase getRoomDB() {
        return roomDB;
    }

}
