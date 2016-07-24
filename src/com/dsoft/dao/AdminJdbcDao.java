package com.dsoft.dao;


import java.util.List;
import java.util.Map;

public interface AdminJdbcDao {

    List getPartialDataList( int page, int rp , String  qtype, String query, String sortname, String sortorder, String className);
    List getPartialDataListWithJoinQuery(int page, int rp, String qtype, String query, String sortname, String sortorder, String className, String joinQuery);
    Map<String, Object> getUsers(Integer start, Integer length, String sortColName, String sortType, String searchKey) throws Exception ;
    void deleteEntityByAnyColValue(String tableName,String colName, String colValue) throws Exception;

    Map<String, Object> getPersons(Integer start, Integer length, String sortColName, String sortType, String searchKey) throws Exception ;
    Map<String, Object> getGroups(Integer start, Integer length, String sortColName, String sortType, String searchKey) throws Exception;
}