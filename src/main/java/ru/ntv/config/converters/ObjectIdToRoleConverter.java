package ru.ntv.config.converters;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import ru.ntv.entity.users.Role;
import ru.ntv.repo.user.RoleRepository;

@ReadingConverter
@Component
public class ObjectIdToRoleConverter implements Converter<ObjectId, Role> {

    @Autowired
    RoleRepository roleRepository;


    @Override
    public Role convert(ObjectId objectId) {
        return roleRepository.findById(ObjectId.get().toString()).get();
    }
}