package vn.rananu.spring.mvc.mapper;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public abstract class BaseMapper<DTOResult, Entity> extends AbstractMapper<Entity> {
    private final Class<DTOResult> dtoResultType;

    public BaseMapper() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        dtoResultType = (Class) pt.getActualTypeArguments()[0];

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
}
