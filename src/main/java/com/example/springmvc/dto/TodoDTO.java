package com.example.springmvc.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by tatsuya on 2014/05/31.
 */
public class TodoDTO {

    private long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    public TodoDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }


}
