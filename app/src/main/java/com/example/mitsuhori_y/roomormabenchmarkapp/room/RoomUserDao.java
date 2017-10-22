package com.example.mitsuhori_y.roomormabenchmarkapp.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mitsuhori_y on 2017/10/17.
 */

@Dao
public interface RoomUserDao {
    @Insert
    void putUser(RoomUserEntity user);

    @Insert
    void putUsers100(List<RoomUserEntity> users);

    @Query("SELECT * FROM user")
    List<RoomUserEntity> getAll();

    @Query("SELECT * FROM user WHERE name LIKE 'hoge'")
    List<RoomUserEntity> getUsersNameHoge();

}
