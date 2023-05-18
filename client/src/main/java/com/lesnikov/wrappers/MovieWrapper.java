package com.lesnikov.wrappers;

import com.lesnikov.model.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class MovieWrapper implements Serializable {

    private Long id;                                // not null, unique, auto-generated, > 0
    private String name;                            // not null, not empty
    private Coordinates coordinates;                // not null
    private String creationDate;                    // not null, auto-generated
    private Integer oscarsCount;                    // must be > 0
    private Double totalBoxOffice;                  // not null, > 0
    private MovieGenre genre;                       // not null
    private MpaaRating mpaaRating;                  // not null
    private Person operator;                        // not null

    public MovieWrapper() {}

    public MovieWrapper(Long id, String name, Coordinates coordinates, String creationDate, Integer oscarsCount,
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
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

        MovieWrapper movieWrapper = (MovieWrapper) o;
        if (!Objects.equals(id, movieWrapper.id)) return false;
        if (!Objects.equals(name, movieWrapper.name)) return false;
        if (!Objects.equals(coordinates, movieWrapper.coordinates)) return false;
        if (!Objects.equals(creationDate, movieWrapper.creationDate)) return false;
        if (!Objects.equals(oscarsCount, movieWrapper.oscarsCount)) return false;
        if (!Objects.equals(totalBoxOffice, movieWrapper.totalBoxOffice))
            return false;
        if (genre != movieWrapper.genre) return false;
        if (mpaaRating != movieWrapper.mpaaRating) return false;
        return Objects.equals(operator, movieWrapper.operator);
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

    public static MovieWrapper wrap(Movie movie) {
        MovieWrapper movieWrapper = new MovieWrapper();
        movieWrapper.setId(movie.getId());
        movieWrapper.setName(movie.getName());
        movieWrapper.setCoordinates(movie.getCoordinates());
        movieWrapper.setCreationDate(movie.getCreationDate().toString());
        movieWrapper.setOscarsCount(movie.getOscarsCount());
        movieWrapper.setTotalBoxOffice(movie.getTotalBoxOffice());
        movieWrapper.setGenre(movie.getGenre());
        movieWrapper.setMpaaRating(movie.getMpaaRating());
        movieWrapper.setOperator(movie.getOperator());
        return movieWrapper;
    }

    public Movie unwrap() {
        Movie movie = new Movie();
        movie.setId(this.getId());
        movie.setName(this.getName());
        movie.setCoordinates(this.getCoordinates());
        movie.setCreationDate(ZonedDateTime.parse(this.getCreationDate()));
        movie.setOscarsCount(this.getOscarsCount());
        movie.setTotalBoxOffice(this.getTotalBoxOffice());
        movie.setGenre(this.getGenre());
        movie.setMpaaRating(this.getMpaaRating());
        movie.setOperator(this.getOperator());
        return movie;
    }

}