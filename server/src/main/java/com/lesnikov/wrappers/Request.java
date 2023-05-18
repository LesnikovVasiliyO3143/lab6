package com.lesnikov.wrappers;

import com.lesnikov.model.Person;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Request implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    private String command;
    transient private Long key;
    private MovieWrapper movieWrapper;
    private List<Request> commandList;
    private Person person;

    public Request() {}

    public Request(String command, Long key, MovieWrapper movieWrapper, List<Request> list,
                   Person person) {
        this.command = command;
        this.key = key;
        this.commandList = list;
        this.movieWrapper = movieWrapper;

        this.person = person;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public MovieWrapper getMovieWrapper() {
        return movieWrapper;
    }

    public void setMovieWrapper(MovieWrapper movieWrapper) {
        this.movieWrapper = movieWrapper;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Request> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Request> commandList) {
        this.commandList = commandList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request that = (Request) o;
        return key == that.key && Objects.equals(command, that.command) &&
                Objects.equals(movieWrapper, that.movieWrapper) && Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, key, movieWrapper, person);
    }

    @Override
    public String toString() {
        return "CommandWrapper{" +
                "command='" + command + '\'' +
                ", key=" + key +
                ", movieWrapper=" + movieWrapper +
                ", commandList=" + commandList +
                ", person=" + person +
                '}';
    }

}
