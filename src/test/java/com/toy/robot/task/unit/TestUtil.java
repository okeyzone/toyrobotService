package com.toy.robot.task.unit;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class TestUtil {

    public static <T extends Enum<T>> void assertEnums(Class<T> enumeration) {

        try {
            Method methodValues = enumeration.getMethod("values");
            T[] values = (T[]) methodValues.invoke(null);
            assertNotNull(values);
            assertTrue(values.length > 0);
            Method methodValueOf = enumeration.getMethod("valueOf", String.class);
            T value = (T) methodValueOf.invoke(null, values[0].name());
            assertSame(values[0], value);

        } catch (Exception e) {
            fail("Exception thrown: " + e);
        }
    }
}
