package com.dsoft.service.impl;

import com.dsoft.dao.AdminJdbcDao;
import com.dsoft.service.AdminJdbcService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("adminJdbcService")
public class AdminJdbcServiceImpl implements AdminJdbcService {

    private static Logger logger = Logger.getLogger(AdminJdbcServiceImpl.class);
    @Autowired(required = true)
    private AdminJdbcDao adminJdbcDao;


    public List getPartialDataList( int page, int rp , String qtype, String query, String sortname, String sortorder,String className) {
        return  adminJdbcDao.getPartialDataList( page, rp ,qtype, query,   sortname,   sortorder,className);
    }

    @Override
    public List getPartialDataListWithJoinQuery(int page, int rp, String qtype, String query, String sortname, String sortorder, String className, String joinQuery) {
        return adminJdbcDao.getPartialDataListWithJoinQuery(page, rp, qtype, query, sortname, sortorder, className, joinQuery);
    }

    @Override
    public Map<String, Object> getUsers(Integer start, Integer length, String sortColName, String sortType, String searchKey) throws Exception{
        return adminJdbcDao.getUsers(start, length, sortColName, sortType, searchKey);
    }

    public void deleteEntityByAnyColValue(String tableName,String colName, String colValue) throws Exception{
        adminJdbcDao.deleteEntityByAnyColValue(tableName, colName, colValue);
    }

    @Override
    public Map<String, Object> getPersons(Integer start, Integer length, String sortColName, String sortType, String searchKey) throws Exception{
        return adminJdbcDao.getPersons(start, length, sortColName, sortType, searchKey);
    }
}
