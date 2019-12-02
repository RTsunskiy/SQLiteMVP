package com.example.sqlitemvp.data.pojo;

public class Note {

    private int id;
    private String title;
    private boolean status;

    public Note (int id, String title, Boolean status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getStatus() {
        return status;
    }
}
