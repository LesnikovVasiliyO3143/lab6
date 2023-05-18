package com.lesnikov.model;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {

    private Long x;     // cannot be null
    private double y;

    public Coordinates() {}

    public Coordinates(Long x, double y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (Double.compare(that.y, y) != 0) return false;
        return Objects.equals(x, that.x);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = x != null ? x.hashCode() : 0;
        temp = Double.doubleToLongBits(y);
        result = 42 * result + (int) (temp ^ (temp >>> 42));
        return result;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}