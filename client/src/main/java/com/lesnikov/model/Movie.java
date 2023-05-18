package com.lesnikov.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Movie implements Comparable<Movie>, Serializable {

    private Long id;                                // not null, unique, auto-generated, > 0
    private String name;                            // not null, not empty
    private Coordinates coordinates;                // not null
    private java.time.ZonedDateTime creationDate;   // not null, auto-generated
    private Integer oscarsCount;                    // must be > 0
    private Double totalBoxOffice;                  // not null, > 0
    private MovieGenre genre;                       // not null
    private MpaaRating mpaaRating;                  // not null
    private Person operator;                        // not null

    public Movie() {}

    public Movie(Long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Integer oscarsCount,
                 Double totalBoxOffice, MovieGenre genre, MpaaRating mpaaRating, Person operator) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.totalBoxOffice = totalBoxOffice;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.operator = operator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getOscarsCount() {
        return oscarsCount;
    }

    public void setOscarsCount(Integer oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    public Double getTotalBoxOffice() {
        return totalBoxOffice;
    }

    public void setTotalBoxOffice(Double totalBoxOffice) {
        this.totalBoxOffice = totalBoxOffice;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public Person getOperator() {
        return operator;
    }

    public void setOperator(Person operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!Objects.equals(id, movie.id)) return false;
        if (!Objects.equals(name, movie.name)) return false;
        if (!Objects.equals(coordinates, movie.coordinates)) return false;
        if (!Objects.equals(creationDate, movie.creationDate)) return false;
        if (!Objects.equals(oscarsCount, movie.oscarsCount)) return false;
        if (!Objects.equals(totalBoxOffice, movie.totalBoxOffice))
            return false;
        if (genre != movie.genre) return false;
        if (mpaaRating != movie.mpaaRating) return false;
        return Objects.equals(operator, movie.operator);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 42 * result + (name != null ? name.hashCode() : 0);
        result = 42 * result + (coordinates != null ? coordinates.hashCode() : 0);
        result = 42 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 42 * result + (oscarsCount != null ? oscarsCount.hashCode() : 0);
        result = 42 * result + (totalBoxOffice != null ? totalBoxOffice.hashCode() : 0);
        result = 42 * result + (genre != null ? genre.hashCode() : 0);
        result = 42 * result + (mpaaRating != null ? mpaaRating.hashCode() : 0);
        result = 42 * result + (operator != null ? operator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", oscarsCount=" + oscarsCount +
                ", totalBoxOffice=" + totalBoxOffice +
                ", genre=" + genre +
                ", mpaaRating=" + mpaaRating +
                ", operator=" + operator +
                '}';
    }


    @Override
    public int compareTo(Movie o) {
        return this.getId().compareTo(o.getId());
    }

}