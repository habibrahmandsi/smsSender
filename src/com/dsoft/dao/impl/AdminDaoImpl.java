package com.dsoft.dao.impl;

import com.dsoft.dao.AdminDao;
import com.dsoft.entity.*;
import com.dsoft.service.AdminJdbcService;
import com.dsoft.util.Utils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository("adminDao")
@Transactional
public class AdminDaoImpl implements AdminDao {

    private static Logger logger = Logger.getLogger(AdminDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    @Autowired(required = true)
    private AdminJdbcService adminJdbcService;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected final Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }


    @Override
    public List<User> getAllUserList() {
        return hibernateTemplate.find("FROM User");
//        return hibernateTemplate.find("FROM User WHERE role != ?", Role.ROLE_SUPER_ADMIN.getLabel());
    }

    @Override
    public List<User> getAllUserList(String userName) {
        return null;
    }

    public int getEntitySize(String entity) {
        Session session = getSession();
        List list = session.createQuery("Select count(*) From " + entity).list();

        if (list != null && list.size() > 0) {
            return Integer.parseInt((list.get(0)).toString());

        }
        return 0;

    }

    public void saveOrUpdateUser(User user) {
        hibernateTemplate.saveOrUpdate(user);
    }

    public User getUser(Long userId) throws Exception {
        Session session = getSession();
        String sql = "FROM User WHERE id = :id ";
        if (!Utils.isInRole(Role.ROLE_SUPER_ADMIN.getLabel())) {
            logger.debug("SMNLOG:This is super Admin");
            sql = "FROM User WHERE role != :roleName AND id = :id ";
        }

        Query query = session.createQuery(sql);

        if (!Utils.isInRole(Role.ROLE_SUPER_ADMIN.getLabel()))
            query.setParameter("roleName", Role.ROLE_SUPER_ADMIN.getLabel());

        query.setParameter("id", userId);

        Object object = query.uniqueResult();
        if (object != null)
            return (User) object;
        return null;
    }

    public void deleteUser(User user) throws Exception {
        hibernateTemplate.delete(user);
    }

    public void saveOrUpdateObject(Object object) throws Exception {
        hibernateTemplate.saveOrUpdate(object);
    }

    public ProductKeyValidation getActiveProductKeyValidation() throws Exception {
        Session session = getSession();
        Query query = session.createQuery("FROM ProductKeyValidation WHERE active = :active");
        query.setParameter("active", true);
        Object object = query.uniqueResult();
        if (object != null)
            return (ProductKeyValidation) object;
        return null;
    }
    public AbstractBaseEntity getAbstractBaseEntityByString(String className, String anyColumn, String columnValue) {

        List<AbstractBaseEntity> entityList = hibernateTemplate.find("From " + className + " where " + anyColumn + " = ?", columnValue);
        if (entityList != null && entityList.size() > 0)
            return entityList.get(0);
        return null;
    }

    public Person getPerson(Long personId) throws Exception {
        Session session = getSession();
        Query query = session.createQuery("FROM Person WHERE id = :id");
        query.setParameter("id", personId);
        Object object = query.uniqueResult();
        if (object != null)
            return (Person) object;
        return null;
    }

    public void deletePerson(Person person) throws Exception {
        hibernateTemplate.delete(person);
    }

    public int getPersonEntitySize() throws Exception {
        Session session = getSession();
        String sql = "Select count(*) From Person";

        Query query = session.createQuery(sql);
        List list = query.list();

        if (list != null && list.size() > 0) {
            return Integer.parseInt((list.get(0)).toString());

        }
        return 0;
    }

    public List<Person> getPersonList() throws Exception{
        List<Person> personList = null;
        DetachedCriteria cr = DetachedCriteria.forClass(Person.class);
        cr.addOrder(Order.asc("name"));
        personList = hibernateTemplate.findByCriteria(cr);
        return personList;
    }

    public Group getGroup(Long groupId) throws Exception {
        Session session = getSession();
        Query query = session.createQuery("FROM Group WHERE id = :id");
        query.setParameter("id", groupId);
        Object object = query.uniqueResult();
        if (object != null)
            return (Group) object;
        return null;
    }

    public void deleteGroup(Group group) throws Exception{
        hibernateTemplate.delete(group);
    }

    public int getGroupEntitySize() throws Exception{
        Session session = getSession();
        String sql = "Select count(*) From Group";

        Query query = session.createQuery(sql);
        List list = query.list();

        if (list != null && list.size() > 0) {
            return Integer.parseInt((list.get(0)).toString());

        }
        return 0;
    }

    public List<GroupMember> getGroupPersonList(Long groupId) throws Exception{
        List<GroupMember> groupPersonList = null;

        if(groupId > 0){
            groupPersonList = hibernateTemplate.find("FROM GroupMember WHERE group.id= ? ", groupId);
        }else{
            groupPersonList = hibernateTemplate.find("FROM RolePermission");
        }

        if (groupPersonList != null && groupPersonList.size() > 0){
            return groupPersonList;
        }
        return null;
    }


    public void deleteGroupMember(List<GroupMember> groupMemberList) throws Exception{
        logger.debug(":: DELETE deleteGroupMember ITEM ::");
        GroupMember groupMember = null;
        if (groupMemberList != null && groupMemberList.size() > 0) {
            for (int i = 0; i < groupMemberList.size(); i++) {
                groupMember = groupMemberList.get(i);
                logger.debug("SMNLOG:groupMember Item id:" + groupMember.getId());
                hibernateTemplate.delete(groupMember);
            }
        }
    }

    public void saveOrUpdateGroup(Group group) throws Exception{

        hibernateTemplate.saveOrUpdate(group);
        GroupMember groupMember = null;
        if(group.getId() != null && group.getId() > 0){
            List<GroupMember> groupMemberList = this.getGroupPersonList(group.getId());
            this.deleteGroupMember(groupMemberList);
        }
        logger.debug("SMNLOG:Previous role permission was deleted");
        List<Person> personList = group.getPersonList() != null ? group.getPersonList() : new ArrayList<Person>();

        if (!Utils.isEmpty(personList)) {
            for (Person person : personList) {
                logger.debug("SMNLOG:: person::"+person);
                if (person != null && person.getId() != null
                        && person.getActive()) {
                    groupMember = new GroupMember();
                    groupMember.setPerson(person);
                    groupMember.setGroup(group);
                    hibernateTemplate.save(groupMember);
                }
            }
        }

    }
}
