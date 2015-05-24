package com.elsennovraditya.materialdesign.responses;

import java.io.Serializable;

/**
 * Created by elsen on 4/8/15.
 */
public class NoteResponse implements Serializable {

    private int id;

    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
