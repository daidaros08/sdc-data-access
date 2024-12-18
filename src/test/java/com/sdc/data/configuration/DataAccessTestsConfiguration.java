package com.sdc.data.configuration;

import java.io.IOException;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.sdc.data.repository.SampleRepository;
import com.sdc.data.service.SampleBaseService;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;


@Configuration
@ComponentScan({"com.sdc.data.service",
    "com.sdc.data.repository"})
@EnableMongoRepositories(basePackages = "com.sdc.data.repository")
public class DataAccessTestsConfiguration {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";
    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "testdb";
    private static final int MONGO_DB_PORT = 8001;

    @Bean
    public SampleBaseService sampleBaseServiceBean(SampleRepository sampleRepository) {
        return new SampleBaseService(sampleRepository);
    }

    @Bean
    public Class<?> sampleBaseServiceClass() {
        return SampleBaseService.class;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        IMongodConfig mongodConfig = new MongodConfigBuilder()
            .version(Version.Main.PRODUCTION)
            .net(new Net(MONGO_DB_URL, MONGO_DB_PORT, false))
            .build();
        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodExecutable mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        MongoClient mongoClients = MongoClients.create(String.format(CONNECTION_STRING, MONGO_DB_URL, MONGO_DB_PORT));

        return new MongoTemplate(mongoClients, MONGO_DB_NAME);
    }

    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
