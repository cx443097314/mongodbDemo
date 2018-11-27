package xin.chen.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import xin.chen.mongodb.dao.mongo1.Customer1Repository;
import xin.chen.mongodb.dao.mongo2.Customer2Repository;
import xin.chen.mongodb.po.Customer;

import java.util.List;
import java.util.Optional;

/**
 * @Author: create_By: chenxin
 * @QQ: 443097314
 * @Data:Created in 2018/5/8 15:06
 * @Version:
 * @Acton:
 */
@Service
public class CustomerService {

    @Autowired
    private Customer1Repository customer1Repository;

    @Autowired
    private Customer2Repository customer2Repository;

    public void save(Customer customer){
        customer1Repository.save(customer);
    }

    public void insert(Customer customer){
        customer1Repository.insert(customer);
    }

    public void insertAll(List<Customer> customers){
        customer2Repository.insert(customers);
    }

    public Optional<Customer> findOne(Customer customer){
        Example<Customer> example = Example.of(customer);
        Optional<Customer> one = customer1Repository.findOne(example);
        return one;
    }

    public List<Customer> findAll(){
        return customer1Repository.findAll();
    }

    public Long findAllCount() {
        return customer1Repository.count();
    }

    public List<Customer> findByCriteria() {
        return null;
    }
}
