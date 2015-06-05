package com.stefankendall.BigLiftsPro.data;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ObjectHelper {
    public static Object getProperty(Object object, String property) {
        List<Field> fields = getInheritedFields(object.getClass());
        for (Field field : fields) {
            if (field.getName().equals(property)) {
                try {
                    return field.get(object);
                } catch (IllegalAccessException e) {
                }
            }
        }
        return null;
    }

    private static List<Field> getInheritedFields(Class<?> type) {
        List<Field> fields = Lists.newArrayList();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }
}
