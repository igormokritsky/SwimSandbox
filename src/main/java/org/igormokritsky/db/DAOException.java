package org.igormokritsky.db;

public class DAOException extends Exception {


    public DAOException() {
        super();
    }


    public DAOException(String message) {
        super(message);
    }


    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
