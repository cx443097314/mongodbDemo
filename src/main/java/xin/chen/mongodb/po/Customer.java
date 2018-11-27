package xin.chen.mongodb.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: create_By: chenxin
 * @QQ: 443097314
 * @Data:Created in 2018/5/8 15:03
 * @Version:
 * @Acton:
 */
@Document(collection = "customer")
@Data
public class Customer {

    @Id
    private String id;

    private String name;

    private Integer age;

    private String gender;

    private String surname;

}
