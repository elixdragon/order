package order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "order.repository",
        mongoTemplateRef = "primaryMongoTemplate")
public class MongoConfiguration {
}