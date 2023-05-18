package com.lesnikov.model;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {

    private String name;        // cannot be null, cannot be empty
    private Long height;        // cannot be null, must be grater than 0
    private Location location;  // cannot be null

    public Person() {}

    public Person(String name, Long height, Location location) {
        this.name = name;
        this.height = height;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!Objects.equals(name, person.name)) return false;
        if (!Objects.equals(height, person.height)) return false;
        return Objects.equals(location, person.location);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", location=" + location +
                '}';
    }


    @Override
    public int compareTo(Person o) {
        return (int) (this.getHeight() - o.getHeight());
    }

}