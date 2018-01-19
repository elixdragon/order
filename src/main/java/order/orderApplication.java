package order;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class orderApplication {

    public static void main(String[] args) {
        SpringApplication.run(order.orderApplication.class, args);
    }


    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Email Thread-");
        executor.initialize();
        return executor;
    }

    @Bean
    public Mongo mongoSecondary() throws Exception {
//         + Values.SECONDARY_USERNAME + ":" + Values.SECONDARY_PASSWORD + "@" +
        return new MongoClient(new MongoClientURI("mongodb://"+ Values.SECONDARY_HOST));
    }

    @Bean(autowire = Autowire.BY_NAME, name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(mongoSecondary(), Values.SECONDARY_DB_NAME);

    }

    @Bean
    public Mongo mongoPrimary() throws Exception {
        return new MongoClient(Values.PRIMARY_HOST, 27017);
    }

    @Bean(autowire = Autowire.BY_NAME, name = "primaryMongoTemplate")
    @Primary
    public MongoTemplate primaryMongoTemplate() throws Exception {
        return new MongoTemplate(mongoPrimary(), Values.PRIMARY_DB_NAME);

    }
}