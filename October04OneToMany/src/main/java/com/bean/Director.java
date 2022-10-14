package com.bean;

import java.util.Set;

public class Director {
    private int directorId;
    private String name;
    private Set<Movie> movies;

    public Director() {
        directorId = 0;
        name = "";
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}