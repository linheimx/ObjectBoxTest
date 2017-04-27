package com.linheimx.objectboxtest.db.objectbox.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Generated;

/**
 * Created by x1c on 2017/4/27.
 */

@Entity
public class Dog {

    @Id
    long id;

    /**
     * 狗的名字
     */
    String name;

    @Generated(hash = 1985487526)
    public Dog(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 2001499333)
    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }
}
