package com.example.springmvc.model;


import javax.persistence.*;

/**
 * Created by tatsuya on 2014/05/31.
 */
@Entity
@Table(name = "todo")
public class Todo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static Todo getBuilder(final String title, final String description) {
        return new Builder(title, description).build();
    }

    public static class Builder {
         Todo built;

        Builder(final String title, final String description) {
            built             = new Todo();
            built.title       = title;
            built.description = description;
        }

        public Todo build() {
            return built;
        }
    }

    public void update(final String title, final String description) {
        this.title = title;
        this.description = description;
    }
}

