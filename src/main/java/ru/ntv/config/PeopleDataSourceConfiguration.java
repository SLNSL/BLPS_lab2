package ru.ntv.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = {"ru.ntv.repo.user"}
)
public class PeopleDataSourceConfiguration {

    @Bean
    public MongoTemplate mongoTemplate() {
        final var template = new MongoTemplate(peopleDatabaseFactory());
        final var conv = (MappingMongoConverter) template.getConverter();

//        MappingMongoConverter converter = new MappingMongoConverter(peopleDatabaseFactory(), new MongoMappingContext());
//        converter.setCustomConversions(new MongoCustomConversions(
//                Arrays.asList(new ObjectIdToRoleConverter(), new RoleToObjectIdConverter(converter),
//                        new ObjectIdToPrivilegeConverter(), new PrivilegeToObjectIdConverter(converter)))
//        );
//        converter.afterPropertiesSet();

        return new MongoTemplate(peopleDatabaseFactory());
    }


    @Bean
    public MongoDatabaseFactory peopleDatabaseFactory() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        return new SimpleMongoClientDatabaseFactory(mongoClient, "test");
    }

}