package vn.rananu.spring.mvc.parser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @author Bien Ly
 * @version 1.0.0
 * @Email lnbienit@gmail.com
 */
@SuppressWarnings("unchecked")
public class JacksonParser {
    public static JacksonParser getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        public static final JacksonParser INSTANCE = new JacksonParser();
    }

    private final ObjectMapper mapper;

    private JacksonParser() {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.setDefaultPropertyInclusion(Value.construct(Include.ALWAYS, Include.NON_NULL));
        mapper.setSerializationInclusion(Include.NON_NULL);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public <T> List<T> toList(String jsonString, Class<T> cls) throws JsonParseException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(List.class, cls);
        try {
            return mapper.readValue(jsonString, listType);
        } catch (Exception e) {
            throw new JsonParseException("Unnable to parse json string to list/array!", e);
        }
    }
    public <T> List<T> toList(byte[] jsonBytes, Class<T> cls) throws JsonParseException {
        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(List.class, cls);
        try {
            return mapper.readValue(jsonBytes, listType);
        } catch (Exception e) {
            throw new JsonParseException("Unnable to parse json string to list/array!", e);
        }
    }


    public <T> T toObject(String jsonString, Class<T> baseCls, Class<?>... paramCls) throws JsonParseException {
        JavaType type = TypeFactory.defaultInstance().constructParametricType(baseCls, paramCls);
        try {
            return mapper.readValue(jsonString, type);
        } catch (Exception e) {
            throw new JsonParseException("Unnable to parse json string to parametric object!", e);
        }
    }

    public <T> T[] toArray(String jsonString, Class<T> cls) throws JsonParseException {
        List<T> list = toList(jsonString, cls);
        T[] result = (T[]) Array.newInstance(cls, list.size());
        list.toArray(result);
        return result;
    }

    public <T> T[] toArray(byte[] jsonBytes, Class<T> cls) throws JsonParseException {
        List<T> list = toList(jsonBytes, cls);
        T[] result = (T[]) Array.newInstance(cls, list.size());
        list.toArray(result);
        return result;
    }

    public String toJson(Object value) throws JsonParseException {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new JsonParseException("Can not parse object to Json!", e);
        }
    }

    public byte[] toBytes(Object value) throws JsonParseException {
        try {
            return mapper.writeValueAsBytes(value);
        } catch (Exception e) {
            throw new JsonParseException("Can not parse object to Json!", e);
        }
    }

    public <T> T toObject(String jsonString, Class<T> cls) throws JsonParseException {
        try {
            return mapper.readValue(jsonString, cls);
        } catch (Exception e) {
            throw new JsonParseException("Unnable to parse json string to object! Json String:" + jsonString, e);
        }
    }

    public <T> T toObject(byte[] jsonBytes, Class<T> cls) throws JsonParseException {
        try {
            return mapper.readValue(jsonBytes, cls);
        } catch (Exception e) {
            throw new JsonParseException("Unnable to parse json string to object! Json String:" + new String(jsonBytes), e);
        }
    }

    public static class JsonParseException extends RuntimeException {
        public JsonParseException(String message, Throwable throwable) {
            super(message, throwable);
        }
    }
}