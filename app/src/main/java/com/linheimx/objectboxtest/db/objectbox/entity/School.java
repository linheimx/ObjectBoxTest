package com.linheimx.objectboxtest.db.objectbox.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Generated;

/**
 * Created by x1c on 2017/4/29.
 */

@Entity
public class School {

    @Id
    long id;

    String name;

    @Generated(hash = 637449455)
    public School(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1579966795)
    public School() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
