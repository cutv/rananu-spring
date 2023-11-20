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


public abstract class BaseMapper<DTOResult, Entity, BaseDTO> implements InitializingBean {


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

    public Entity toEntity(BaseDTO creationParam) {
        return modelMapper.map(creationParam, entityType);
    }


    public void transferFields(BaseDTO updateParam, Entity entity) {
        transferFields(updateParam, entity, false);
    }

    public <T> void transferFields(T updateParam, Entity entity, boolean skipNullEnabled) {
        if (skipNullEnabled)
            modelMapper.map(updateParam, entity);
        else
            modelMapperSkipNullDisabled.map(updateParam, entity);
    }
}
