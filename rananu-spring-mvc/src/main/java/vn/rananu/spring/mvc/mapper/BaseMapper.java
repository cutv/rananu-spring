package vn.rananu.spring.mvc.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static vn.rananu.spring.mvc.configuration.MapperConfiguration.MODEL_MAPPER_SKIP_NULL_DISABLED;
import static vn.rananu.spring.mvc.configuration.MapperConfiguration.MODEL_MAPPER_SKIP_NULL_ENABLED;


public abstract class BaseMapper<DTOResult, Entity> implements InitializingBean {


    @Autowired
    @Qualifier(MODEL_MAPPER_SKIP_NULL_ENABLED)
    protected ModelMapper modelMapper;
    @Autowired
    @Qualifier(MODEL_MAPPER_SKIP_NULL_DISABLED)
    protected ModelMapper modelMapperSkipNullDisabled;
    private final Class<DTOResult> dtoResultType;
    private final Class<Entity> entityType;

    public BaseMapper() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        dtoResultType = (Class) pt.getActualTypeArguments()[0];
        entityType = (Class) pt.getActualTypeArguments()[1];
    }

    @Override
    public void afterPropertiesSet() {
    }

    public DTOResult toDTO(Entity entity) {
        return modelMapper.map(entity, dtoResultType);
    }

    public List<DTOResult> toDTOList(Collection<Entity> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Set<DTOResult> toDTOSet(Collection<Entity> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toSet());
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
