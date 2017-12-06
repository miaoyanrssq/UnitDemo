package com.example.component_db.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by gary on 2017/12/6.
 */
@Entity
public class Student {
    @Id
    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Generated(hash = 298027456)
    public Student(long id) {
        this.id = id;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }
}
