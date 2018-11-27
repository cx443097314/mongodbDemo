package xin.chen.mongodb.mongodbconfig;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @Author: create_By: chenxin
 * @QQ: 443097314
 * @Data:Created in 2018/5/8 18:46
 * @Version:
 * @Acton:
 */
@Configuration
@EnableMongoRepositories(basePackages = "xin.chen.mongodb.dao.mongo2",mongoTemplateRef = "mongo2")
public class Mongo2Template {

    @Autowired
    @Qualifier("mongo2Properties")
    private MongoProperties mongoProperties;

    @Bean(name = "mongo2")
//    @Primary
    public MongoTemplate mongo2(){
        return new MongoTemplate(mongo2Factory(this.mongoProperties));
    }


    @Bean
//    @Primary
    public MongoDbFactory mongo2Factory(MongoProperties mongoProperties) {
        ServerAddress serverAddress = new ServerAddress(mongoProperties.getHost(),mongoProperties.getPort());
        return new SimpleMongoDbFactory(new MongoClient(serverAddress),mongoProperties.getDatabase());
    }
}
