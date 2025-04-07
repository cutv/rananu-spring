package vn.rananu.spring.mvc.mapper;

import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractMapper<Entity> {

    protected ModelMapper mapperSkipNullEnabled = vn.rananu.spring.mvc.mapper.ModelMapper.INSTANCE.mapperSkipNullEnabled;

    protected ModelMapper mapperSkipNullDisabled = vn.rananu.spring.mvc.mapper.ModelMapper.INSTANCE.mapperSkipNullDisabled;
    protected final Class<Entity> entityType;

    public AbstractMapper() {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] arguments = pt.getActualTypeArguments();
        entityType = (Class) pt.getActualTypeArguments()[arguments.length - 1];
    }

    public <DTOResult> DTOResult toDTO(Object source, Class<DTOResult> clazz) {
        return mapperSkipNullEnabled.map(source, clazz);
    }

    public <DTOResult> List<DTOResult> toDTOList(Collection<Entity> entities, Class<DTOResult> clazz) {
        return entities.stream().map(entity -> toDTO(entity, clazz)).collect(Collectors.toList());
    }

    public <DTOResult> Set<DTOResult> toDTOSet(Collection<Entity> entities, Class<DTOResult> clazz) {
        return entities.stream().map(entity -> toDTO(entity, clazz)).collect(Collectors.toSet());
    }

    public Entity toEntity(Object param) {
        return mapperSkipNullEnabled.map(param, entityType);
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
