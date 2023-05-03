package ru.ntv.config.converters;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import ru.ntv.entity.users.Role;
import ru.ntv.repo.user.RoleRepository;

@WritingConverter
@Component
public class RoleToObjectIdConverter implements Converter<Role, ObjectId> {
    private final MongoConverter mongoConverter;

    public RoleToObjectIdConverter(MongoConverter mongoConverter) {
        this.mongoConverter = mongoConverter;
    }

    @Override
    public ObjectId convert(Role role) {
        return (ObjectId) mongoConverter.getConversionService().convert(role.getId(), ObjectId.class);
    }
}