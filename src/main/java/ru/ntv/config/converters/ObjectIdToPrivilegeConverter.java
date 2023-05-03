package ru.ntv.config.converters;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import ru.ntv.entity.users.Privilege;
import ru.ntv.entity.users.Role;
import ru.ntv.repo.user.PrivilegeRepository;
import ru.ntv.repo.user.RoleRepository;

import javax.annotation.PostConstruct;

@ReadingConverter
@Component
public class ObjectIdToPrivilegeConverter implements Converter<ObjectId, Privilege> {

    @Autowired
    PrivilegeRepository roleRepository;



    @Override
    public Privilege convert(ObjectId objectId) {
        return roleRepository.findById(ObjectId.get().toString()).get();
    }

    @PostConstruct
    public void go(){
        System.out.println("__");
    }
}

