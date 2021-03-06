package com.example.inventory2.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity
public class Dependency implements Comparable, Serializable {
    public static final String TAG = "dependency";

    @PrimaryKey (autoGenerate = true)
    private int id;

    @NonNull
    private String name;
    @NonNull
    private String shortName;
    @NonNull
    private String description;
    private String image;

    @Ignore
    public Dependency(){}

    @Ignore
    public Dependency(String name, String shortName, String description, String image) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Dependency{" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((Dependency) obj).getName().equals(getName());
    }

    @Override
    public int compareTo(Object o) {
        //return ((Dependency)o).getName().compareTo(getName());

        if (((Dependency) o).getName().equals(getName())) {
            return ((Dependency) o).getDescription().compareTo(getDescription());
        } else {
            return ((Dependency) o).getName().compareTo(getName());
        }
    }
}
