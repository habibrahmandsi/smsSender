package com.dsoft.service.impl;

import com.dsoft.dao.AdminDao;
import com.dsoft.entity.AbstractBaseEntity;
import com.dsoft.entity.Person;
import com.dsoft.entity.ProductKeyValidation;
import com.dsoft.entity.User;
import com.dsoft.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    private static Logger logger = Logger.getLogger(AdminServiceImpl.class);

    @Autowired(required = true)
    @Qualifier("adminDao")
    private AdminDao adminDao;

    @Override
    public List<User> getAllUserList() {

        return adminDao.getAllUserList();
    }

    @Override
    public List<User> getAllUserList(String userName) {
        return null;
    }

    public int getEntitySize(String entity) {
        return adminDao.getEntitySize(entity);

    }

    public void saveOrUpdateUser(User user) {
        adminDao.saveOrUpdateUser(user);

    }

    public User getUser(Long userId) throws Exception {
        return adminDao.getUser(userId);
    }

    public void deleteUser(User user) throws Exception {
        adminDao.deleteUser(user);
    }

    public ProductKeyValidation getActiveProductKeyValidation() throws Exception {
        return adminDao.getActiveProductKeyValidation();
    }

    public void saveOrUpdateObject(Object object) throws Exception {
        adminDao.saveOrUpdateObject(object);
    }

    public AbstractBaseEntity getAbstractBaseEntityByString(String className, String anyColumn, String columnValue) {
        return adminDao.getAbstractBaseEntityByString(className, anyColumn, columnValue);
    }

    public Person getPerson(Long personId) throws Exception {
        return adminDao.getPerson(personId);
    }

    public void deletePerson(Person person) throws Exception {
        adminDao.deletePerson(person);
    }

    public int getPersonEntitySize() throws Exception {
        return adminDao.getPersonEntitySize();
    }
}
