package org.springframework.amqp.component.util;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class CollectionUtil {

    private CollectionUtil() {
    }

    public static <T> Set<T> EMPTY_SET() {
        return new HashSet<T>();
    }

    public static <T> Set<T> EMPTY_SET(T... initialValues) {

        Set<T> set = EMPTY_SET();
        set.addAll(Arrays.asList(initialValues));

        return set;

    }

}
