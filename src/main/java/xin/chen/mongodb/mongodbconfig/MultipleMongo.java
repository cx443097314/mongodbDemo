package xin.chen.mongodb.mongodbconfig;

import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Author: create_By: chenxin
 * @QQ: 443097314
 * @Data:Created in 2018/5/8 18:27
 * @Version:
 * @Acton:  配置多个mongodb数据库
 */
@Data
@Configuration
public class MultipleMongo {

    /**
     * 主数据库
     * @return
     */
    @Bean(name = "mongo1Properties")
    @Primary
    @ConfigurationProperties(prefix = "spring.data.mongodb.mongo1")
    public MongoProperties mongo1(){
        return new MongoProperties();
    }

    /**
     * 从数据库
     */
    @Bean(name="mongo2Properties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.mongo2")
    public MongoProperties mongo2(){
        return new MongoProperties();
    }
}
