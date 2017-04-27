package com.linheimx.objectboxtest.db.objectbox.entity;

/**
 * Created by x1c on 2017/4/27.
 */

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Property;
import io.objectbox.converter.PropertyConverter;
import io.objectbox.annotation.Generated;

/**
 * av影片
 */
@Entity
public class Av {

    @Id
    long id;

    /**
     * 影片的名字
     */
    String name;

    /**
     * 女演员
     */
    @Convert(converter = ActorConverter.class, dbType = Integer.class)
    Actor actor;

    @Generated(hash = 712553122)
    public Av(long id, String name, Actor actor) {
        this.id = id;
        this.name = name;
        this.actor = actor;
    }

    @Generated(hash = 615302020)
    public Av() {
    }


    /**
     * 演员
     */
    public static enum Actor {
        /**
         * 苍井空
         */
        CangJK(0),
        /**
         * 泷泽罗拉
         */
        LongZLL(1);

        final int id;

        Actor(int id) {
            this.id = id;
        }
    }

    public static class ActorConverter implements PropertyConverter<Actor, Integer> {

        @Override
        public Actor convertToEntityProperty(Integer integer) {
            if (integer == null) {
                return null;
            }
            for (Actor actor : Actor.values()) {
                if (actor.id == integer) {
                    return actor;
                }
            }
            return Actor.CangJK;
        }

        @Override
        public Integer convertToDatabaseValue(Actor actor) {
            return actor == null ? null : actor.id;
        }
    }


}
