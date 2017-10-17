package com.example.mitsuhori_y.roomormabenchmarkapp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by mitsuhori_y on 2017/10/17.
 */

@Database(entities = {RoomUserEntity.class}, version = 1)
public abstract class RmDatabase extends RoomDatabase {

    public abstract RoomUserDao roomUserDao();
}
