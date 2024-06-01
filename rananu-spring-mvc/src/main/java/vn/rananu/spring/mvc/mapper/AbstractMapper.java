package vn.rananu.spring.mvc.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static vn.rananu.spring.mvc.configuration.MapperConfiguration.MODEL_MAPPER_SKIP_NULL_DISABLED;
import static vn.rananu.spring.mvc.configuration.MapperConfiguration.MODEL_MAPPER_SKIP_NULL_ENABLED;

public abstract class AbstractMapper<Entity> {
    @Autowired
    @Qualifier(MODEL_MAPPER_SKIP_NULL_ENABLED)
    protected ModelMapper modelMapper;
    @Autowired
    @Qualifier(MODEL_MAPPER_SKIP_NULL_DISABLED)
    protected ModelMapper modelMapperSkipNullDisabled;
    protected final Class<Entity> entityType;

    public AbstractMapper() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] arguments = pt.getActualTypeArguments();
        entityType = (Class) pt.getActualTypeArguments()[arguments.length - 1];
    }

    public <DTOResult> DTOResult toDTO(Object source, Class<DTOResult> clazz) {
        return modelMapper.map(source, clazz);
    }

    public <DTOResult> List<DTOResult> toDTOList(Collection<Entity> entities, Class<DTOResult> clazz) {
        return entities.stream().map(entity -> toDTO(entity, clazz)).collect(Collectors.toList());
    }

    public <DTOResult> Set<DTOResult> toDTOSet(Collection<Entity> entities, Class<DTOResult> clazz) {
        return entities.stream().map(entity -> toDTO(entity, clazz)).collect(Collectors.toSet());
    }

    public Entity toEntity(Object param) {
        return modelMapper.map(param, entityType);
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
