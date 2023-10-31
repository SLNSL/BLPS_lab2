package ru.ntv.config.converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import ru.ntv.entity.users.Privilege;

@WritingConverter
@Component
public class PrivilegeToObjectIdConverter implements Converter<Privilege, ObjectId> {


    private final MongoConverter mongoConverter;

    public PrivilegeToObjectIdConverter(MongoConverter mongoConverter) {
        this.mongoConverter = mongoConverter;
    }

    @Override
    public ObjectId convert(Privilege role) {
        return (ObjectId) mongoConverter.getConversionService().convert(role.getId(), ObjectId.class);
    }
}