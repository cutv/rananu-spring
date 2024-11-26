package vn.rananu.spring.mvc.mapper;

import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ModelMapper {
    private final static ModelMapper INSTANCE = new ModelMapper();
    protected org.modelmapper.ModelMapper modelMapper;
    protected org.modelmapper.ModelMapper modelMapperSkipNullDisabled;

    private ModelMapper() {
        modelMapper = new org.modelmapper.ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapperSkipNullDisabled = new org.modelmapper.ModelMapper();
        modelMapperSkipNullDisabled.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public <DTOResult> DTOResult toDTO(Object source, Class<DTOResult> clazz) {
        return modelMapper.map(source, clazz);
    }

    public <DTOResult> List<DTOResult> toDTOList(Collection<Object> entities, Class<DTOResult> clazz) {
        return entities.stream().map(entity -> toDTO(entity, clazz)).collect(Collectors.toList());
    }

    public <DTOResult> Set<DTOResult> toDTOSet(Collection<Object> entities, Class<DTOResult> clazz) {
        return entities.stream().map(entity -> toDTO(entity, clazz)).collect(Collectors.toSet());
    }

    public <Entity> Entity toEntity(Object param, Class<Entity> clazz) {
        return modelMapper.map(param, clazz);
    }

    public void transferFields(Object source, Object destination) {
        transferFields(source, destination, false);
    }

    public void transferFields(Object source, Object destination, boolean skipNullEnabled) {
        if (skipNullEnabled)
            modelMapper.map(source, destination);
        else
            modelMapperSkipNullDisabled.map(source, destination);
    }
}
