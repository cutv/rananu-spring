package vn.rananu.spring.mvc.mapper;

import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ModelMapper {
    public final static ModelMapper INSTANCE = new ModelMapper();
    protected org.modelmapper.ModelMapper mapperSkipNullEnabled;
    protected org.modelmapper.ModelMapper mapperSkipNullDisabled;

    private ModelMapper() {
        mapperSkipNullEnabled = new org.modelmapper.ModelMapper();
        mapperSkipNullEnabled.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        mapperSkipNullDisabled = new org.modelmapper.ModelMapper();
        mapperSkipNullDisabled.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public org.modelmapper.ModelMapper getMapperSkipNullEnabled() {
        return mapperSkipNullEnabled;
    }

    public org.modelmapper.ModelMapper getMapperSkipNullDisabled() {
        return mapperSkipNullDisabled;
    }

    public <DTOResult> DTOResult toDTO(Object source, Class<DTOResult> clazz) {
        return mapperSkipNullEnabled.map(source, clazz);
    }

    public <DTOResult> List<DTOResult> toDTOList(Collection<Object> entities, Class<DTOResult> clazz) {
        return entities.stream().map(entity -> toDTO(entity, clazz)).collect(Collectors.toList());
    }

    public <DTOResult> Set<DTOResult> toDTOSet(Collection<Object> entities, Class<DTOResult> clazz) {
        return entities.stream().map(entity -> toDTO(entity, clazz)).collect(Collectors.toSet());
    }

    public <Entity> Entity toEntity(Object param, Class<Entity> clazz) {
        return mapperSkipNullEnabled.map(param, clazz);
    }

    public void transferFields(Object source, Object destination) {
        transferFields(source, destination, false);
    }

    public void transferFields(Object source, Object destination, boolean skipNullEnabled) {
        if (skipNullEnabled)
            mapperSkipNullEnabled.map(source, destination);
        else
            mapperSkipNullDisabled.map(source, destination);
    }
}
