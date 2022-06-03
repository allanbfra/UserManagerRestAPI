package com.challange.usermanagerrest.core.usecase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CpfValidatorTest {
    @Autowired
    private CpfValidator cpfValidator;

    @Test
    void shouldVaLidateCpfProperly() {
        assertTrue(cpfValidator.validate("436.170.208-01"));
    }

    @Test
    void shouldVaLidateCpfProperlyAndThrowException() {
        InvalidParameterException thrown1 = throwInvalidParameter("333.333.333-33");
        InvalidParameterException thrown2 = throwInvalidParameter("123.456.789-00");

        assertTrue(thrown1.getMessage().contains("Invalid CPF!"));
    }

    private InvalidParameterException throwInvalidParameter(String cpf) {
        return assertThrows(
                InvalidParameterException.class,
                () -> cpfValidator.validate(cpf),
                "Expected validate() to throw, but it didn't"
        );
    }

}