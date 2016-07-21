package com.dsoft.service;

import com.dsoft.entity.AbstractBaseEntity;
import com.dsoft.entity.Person;
import com.dsoft.entity.ProductKeyValidation;
import com.dsoft.entity.User;

import java.util.List;

public interface AdminService {

    List<User> getAllUserList();
    List<User> getAllUserList(String userName);
    int getEntitySize(String entity);
    void saveOrUpdateUser(User user);
    User getUser(Long userId) throws Exception;
    void deleteUser(User user) throws Exception ;
    ProductKeyValidation getActiveProductKeyValidation() throws Exception;
    void saveOrUpdateObject(Object object) throws Exception;
    AbstractBaseEntity getAbstractBaseEntityByString(String className,String anyColumn,String columnValue);

    Person getPerson(Long personId) throws Exception;
    void deletePerson(Person person) throws Exception;
    int getPersonEntitySize() throws Exception;
}