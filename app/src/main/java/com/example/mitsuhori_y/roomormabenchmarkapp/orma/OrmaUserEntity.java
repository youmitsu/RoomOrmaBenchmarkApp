package com.example.mitsuhori_y.roomormabenchmarkapp.orma;

import android.arch.persistence.room.PrimaryKey;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by mitsuhori_y on 2017/10/16.
 */

@Table
public class OrmaUserEntity {

    @PrimaryKey
    public String email;

    @Column
    public String name;

    @Column
    public String gender;

    @Column
    public String age;

    public OrmaUserEntity() {

    }

    public OrmaUserEntity(String e, String n, String g, String a) {
        this.email = e;
        this.name = n;
        this.gender = g;
        this.age = a;
    }

}
