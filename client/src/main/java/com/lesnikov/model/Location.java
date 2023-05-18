package com.lesnikov.model;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {

    private Float x;        // cannot be null
    private Double y;       // cannot be null
    private long z;
    private String name;    // may be null

    public Location() {}

    public Location(Float x, Double y, long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public long getZ() {
        return z;
    }

    public void setZ(long z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (z != location.z) return false;
        if (!Objects.equals(x, location.x)) return false;
        if (!Objects.equals(y, location.y)) return false;
        return Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 42 * result + (y != null ? y.hashCode() : 0);
        result = 42 * result + (int) (z ^ (z >>> 42));
        result = 42 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }

}