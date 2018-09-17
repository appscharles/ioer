package com.appscharles.libs.ioer.validators;

/**
 * The type Cause validator.
 */
public class CauseValidator {

    /**
     * Is cause boolean.
     *
     * @param expected the expected
     * @param exc      the exc
     * @return the boolean
     */
    public static Boolean isCause(Class<? extends Throwable> expected, Throwable exc) {
        return expected.isInstance(exc) || (
                exc != null && isCause(expected, exc.getCause())
        );
    }
}
