package com.alura.literatura.exception;

/**
 * @author Kevin
 */
public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(String message) {
        super(message);
    }
}