package org.example;

public class Exception{
    public static class SsnAlreadyExistsException extends RuntimeException {
        public SsnAlreadyExistsException(String message) {
            super(message);
        }
    }
    public static class EmployeeDoesNotExistsException extends RuntimeException {
        public EmployeeDoesNotExistsException(String message) {
            super(message);
        }
    }
    public static class InvalidInputException extends RuntimeException {
        public InvalidInputException(String message) {
            super(message);
        }
    }
}
