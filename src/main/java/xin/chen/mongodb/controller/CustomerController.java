package xin.chen.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.chen.mongodb.service.CustomerService;
import xin.chen.mongodb.po.Customer;

import java.util.*;

/**
 * @Author: create_By: chenxin
 * @QQ: 443097314
 * @Data:Created in 2018/5/8 15:07
 * @Version:
 * @Acton:
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MongoOperations mongoOperations;

    /* 保存 */
    @PostMapping("cust/save")
    public void save(Customer customer){
        customerService.save(customer);
    }

    /* 添加 */
    @PostMapping("cust/insert")
    public void insert(Customer customer){
        customerService.insert(customer);
    }

    /* 批量添加 */
    @PostMapping("cust/batch")
    public void batch(){
        List<Customer> lists = new ArrayList<>();
        String[] name = {"孙悟空","猪八戒","唐僧","沙和尚","王母","玉帝","观音","如来","蜘蛛精","白骨精","黑熊精","青牛精","白象精","大鹏精","狮子精"};
        String[] gender = {"男","女"};
        String[] surname = {"宝马","奔驰"};
        Customer customer;
        for (int i = 0; i < name.length; i++) {
            customer = new Customer();
            customer.setName(name[i]);
            customer.setAge((int)(20+Math.random()*(30-20+1)));
            customer.setGender(gender[new Random().nextInt(2)]);
            customer.setSurname(surname[new Random().nextInt(2)]);
            lists.add(customer);
        }
        customerService.insertAll(lists);
    }

    /* 查询根据条件查询1个 */
    @GetMapping("cust/findOne")
    public Optional<Customer> find(@RequestParam String name){
        Customer customer = new Customer();
        customer.setName(name);
        Optional<Customer> one = customerService.findOne(customer);
        return one;
    }

    /* 查询所有 */
    @GetMapping("cust/findAll")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    /* 统计个数 */
    @GetMapping("cust/count")
    public Long count(){
        return customerService.findAllCount();
//        return mongoOperations.getCollection("customer").count();
    }

    /* 复杂查询 */
    @GetMapping("cust/findAlls")
    public List<Customer> findAlls(){
//        MongoCollection<Document> customer = mongoOperations.getCollection("customer");
//        BasicDBObject basicDBObject = new BasicDBObject();
//        return customer.find(basicDBObject).limit(2).sort(new BasicDBObject("age",-1));
        //根据条件查询到的数据然后根据条件排序，然后分页
        return  mongoOperations.find(
                Query.query(Criteria.where("gender").is("男").and("surname").is("宝马"))
                        .with(PageRequest.of(1,2,new Sort(Sort.Direction.ASC,"age"))),Customer.class);
    }

    @GetMapping("cust/findAll1")
    public List<Customer> findAll1(){
        return customerService.findAll();
    }

    @GetMapping("cust/findAll1")
    public List<Customer> findAll12(){
        return customerService.findAll();
    }

}
