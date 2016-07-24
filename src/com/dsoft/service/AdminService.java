package com.dsoft.service;

import com.dsoft.entity.*;

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
    List<Person> getPersonList() throws Exception;

    Group getGroup(Long groupId) throws Exception;
    void deleteGroup (Group  group) throws Exception;
    int getGroupEntitySize() throws Exception;
    List<GroupMember> getGroupPersonList(Long groupId) throws Exception;
    void saveOrUpdateGroup(Group group) throws Exception;

}