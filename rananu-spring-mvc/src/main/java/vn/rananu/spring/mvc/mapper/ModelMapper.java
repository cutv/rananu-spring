package vn.rananu.spring.mvc.mapper;

import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ModelMapper {
    public final static ModelMapper INSTANCE = new ModelMapper();
    protected org.modelmapper.ModelMapper mapperSkipNullEnabled = new org.modelmapper.ModelMapper();
    protected org.modelmapper.ModelMapper mapperSkipNullDisabled = new org.modelmapper.ModelMapper();

    private ModelMapper() {
        mapperSkipNullEnabled.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        mapperSkipNullDisabled.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public org.modelmapper.ModelMapper getMapperSkipNullEnabled() {
        return mapperSkipNullEnabled;
    }

    public org.modelmapper.ModelMapper getMapperSkipNullDisabled() {
        return mapperSkipNullDisabled;
    }

    public <Entity, DTOResult> DTOResult toDTO(Entity source, Class<DTOResult> clazz) {
        return mapperSkipNullEnabled.map(source, clazz);
    }

    public <Entity, DTOResult> List<DTOResult> toDTOList(Collection<Entity> entities, Class<DTOResult> clazz) {
        return entities.stream().map(entity -> toDTO(entity, clazz)).collect(Collectors.toList());
    }

    public <Entity, DTOResult> Set<DTOResult> toDTOSet(Collection<Entity> entities, Class<DTOResult> clazz) {
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
