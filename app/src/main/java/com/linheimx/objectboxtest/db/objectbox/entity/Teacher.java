package com.linheimx.objectboxtest.db.objectbox.entity;

/**
 * Created by x1c on 2017/4/27.
 */

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;
import io.objectbox.annotation.Generated;

/**
 * av影片
 */
@Entity
public class Teacher {

    @Id
    long id;

    String name;

    @Convert(converter = TeacherCategoryConverter.class, dbType = Integer.class)
    TeacherCategry actor;

    @Generated(hash = 613298964)
    public Teacher(long id, String name, TeacherCategry actor) {
        this.id = id;
        this.name = name;
        this.actor = actor;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
    }


    public static enum TeacherCategry {
        /**
         * 汉语老师
         */
        Chinese(0),
        /**
         * 数学老师
         */
        Math(1),

        /**
         * 英语老师
         */
        English(2);

        final int id;

        TeacherCategry(int id) {
            this.id = id;
        }
    }

    public static class TeacherCategoryConverter implements PropertyConverter<TeacherCategry, Integer> {

        @Override
        public TeacherCategry convertToEntityProperty(Integer integer) {
            if (integer == null) {
                return null;
            }
            for (TeacherCategry actor : TeacherCategry.values()) {
                if (actor.id == integer) {
                    return actor;
                }
            }
            return TeacherCategry.English;
        }

        @Override
        public Integer convertToDatabaseValue(TeacherCategry actor) {
            return actor == null ? null : actor.id;
        }
    }


}
