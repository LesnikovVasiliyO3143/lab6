package com.lesnikov.wrappers;

import java.io.Serializable;
import java.util.Objects;

public class Response implements Serializable {

    private static final long serialVersionUID = 6529685098267757691L;
    private String message;

    public Response() {}

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                '}';
    }

}
