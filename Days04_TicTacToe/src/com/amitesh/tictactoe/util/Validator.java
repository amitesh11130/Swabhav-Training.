package com.amitesh.tictactoe.util;

import com.amitesh.tictactoe.exception.InvalidInputException;

public class Validator {

    public static void validateName(String name) throws InvalidInputException {
        if (name == null || name.trim().isEmpty())
            throw new InvalidInputException("Name cannot be empty");

        if (!name.matches("[a-zA-Z ]+"))
            throw new InvalidInputException("Only letters allowed");
    }
}