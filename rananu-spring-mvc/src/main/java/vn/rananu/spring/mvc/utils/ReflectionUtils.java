package vn.rananu.spring.mvc.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ReflectionUtils {
    public static void setFieldValue(String fieldName, Object obj, Object value) {
        try {
            Class<?> cls = obj.getClass();
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new FieldAccessException("Unable to set value to private filed of object!", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(String fieldName, Object obj, Class<T> resultCls) {
        try {
            Class<?> cls = obj.getClass();
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (Exception e) {
            throw new FieldAccessException("Unable to get value from private filed of object!", e);
        }
    }

    public static Object getFieldValue(String fieldName, Object obj) {
        return getFieldValue(fieldName, obj, Object.class);
    }

    public static String getFieldName(Class<?> model, Class<? extends Annotation> anotation) {
        Field[] fields = model.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(anotation)) {
                return field.getName();
            }
        }
        throw new IllegalArgumentException("Can't find the field name that present of " + anotation.getSimpleName());
    }

    public static Object getNestedFieldValue(String fieldName, Object obj) {
        String[] keys = fieldName.split("\\.");
        Object nested = obj;
        for (String field : keys) {
            nested = getFieldValue(field, nested);
            if (nested == null) {
                return null;
            }
        }
        return nested;
    }


    @SuppressWarnings("unchecked")
    public static <T> T getArg(Object[] args, Class<T> clazz) {
        for (Object object : args) {
            if (clazz.isInstance(object)) {
                return (T) object;
            }
        }
        return null;
    }

    public static class FieldAccessException extends RuntimeException {
        public FieldAccessException(String message, Exception e) {
            super(message, e);
        }
    }
}
