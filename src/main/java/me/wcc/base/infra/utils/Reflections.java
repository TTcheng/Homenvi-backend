package me.wcc.base.infra.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reflections {
    private static final Logger logger = LoggerFactory.getLogger(Reflections.class);

    private Reflections() {
    }

    public static Class getClassGenericType(Class clazz) {
        return getClassGenericType(clazz, 0);
    }

    public static Class getClassGenericType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        } else {
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if (index < params.length && index >= 0) {
                if (!(params[index] instanceof Class)) {
                    logger.warn("{} not set the actual class on superclass generic parameter", clazz.getSimpleName());
                    return Object.class;
                } else {
                    return (Class)params[index];
                }
            } else {
                logger.warn("Index: {}, Size of {}'s Parameterized Type: {}", index, clazz.getSimpleName(), params.length);
                return Object.class;
            }
        }
    }

    public static Field getField(Class entityClass, String fieldName) {
        try {
            Field field = entityClass.getDeclaredField(fieldName);
            makeAccessible(field);
            return field;
        } catch (NoSuchFieldException var3) {
            logger.error(var3.getMessage(), var3);
            return null;
        }
    }

    public static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }

    }

    @SuppressWarnings("unchecked")
    public static Field[] getAllField(Class<?> clazz) {
        List<Field> fieldList = new ArrayList();

        for(Class tempClass = clazz; tempClass != null; tempClass = tempClass.getSuperclass()) {
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
        }

        Field[] result = new Field[fieldList.size()];
        return fieldList.toArray(result);
    }
}
