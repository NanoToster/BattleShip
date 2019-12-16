package ru.vsu.exceptions;

/**
 * @author Ivan Rovenskiy
 * 16 December 2019
 */
public class CommonBaseException extends RuntimeException {
    private String message;

    public CommonBaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static class BuilderReuseException extends CommonBaseException {
        public BuilderReuseException(String message) {
            super(message);
        }
    }
}
