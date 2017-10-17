package com.example.mitsuhori_y.roomormabenchmarkapp.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

/**
 * Created by mitsuhori_y on 2017/10/17.
 */

@Dao
public interface RoomUserDao {
    @Insert
    void putUser(RoomUserEntity user);
}
