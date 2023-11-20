package vn.rananu.spring.mvc.parser;

import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;

public class JsonPathParser {
    public static JsonPathParser getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        public static final JsonPathParser INSTANCE = new JsonPathParser();
    }

    private final ParseContext parseContext;

    private JsonPathParser() {
        Configuration configuration = Configuration.defaultConfiguration();
        configuration.addOptions(Option.ALWAYS_RETURN_LIST);
        configuration.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
        configuration.jsonProvider(new JacksonJsonProvider());
        parseContext = JsonPath.using(configuration);
    }

    public ReadContext parse(String json) {
        return parseContext.parse(json);
    }

}
