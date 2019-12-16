package ru.vsu.exceptions;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class GameBaseException extends RuntimeException {
    private String message;

    public GameBaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
