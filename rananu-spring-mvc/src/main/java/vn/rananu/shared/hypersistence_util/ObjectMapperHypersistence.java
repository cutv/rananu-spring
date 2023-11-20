package vn.rananu.shared.hypersistence_util;

import com.fasterxml.jackson.databind.ObjectMapper;
import vn.rananu.shared.parser.JacksonParser;

//hypersistence-utils.properties file
//hypersistence.utils.jackson.object.mapper=vn.sapo.shared.hypersistence_utils.ObjectMapperHypersistence
public class ObjectMapperHypersistence implements  io.hypersistence.utils.hibernate.type.util.ObjectMapperSupplier{
    @Override
    public ObjectMapper get() {
        return JacksonParser.getInstance().getMapper();
    }
}
