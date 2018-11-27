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
@EnableMongoRepositories(basePackages = "xin.chen.mongodb.dao.mongo1",mongoTemplateRef = "mongo1")
public class Mongo1Template {

    @Autowired
    @Qualifier("mongo1Properties")
    private MongoProperties mongoProperties;

    /**
     * 配置多数据源只有一个数据源的配置能使用@Primary，谁使用谁就代表主数据源
     * @return
     */
    @Primary
    @Bean(name = "mongo1")
    public MongoTemplate mongo1(){
        return new MongoTemplate(mongo1Factory(this.mongoProperties));
    }


    @Bean
    @Primary
    public MongoDbFactory mongo1Factory(MongoProperties mongoProperties) {
        ServerAddress serverAddress = new ServerAddress(mongoProperties.getHost(),mongoProperties.getPort());
        return new SimpleMongoDbFactory(new MongoClient(serverAddress),mongoProperties.getDatabase());
    }
}
