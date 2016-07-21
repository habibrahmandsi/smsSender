package com.dsoft.dao.impl;

/**
 * Created with IntelliJ IDEA.
 * User: habib
 * Date: 7/22/13
 * Time: 3:04 PM
 * To change this template use File | Settings | File Templates.
 */

import com.dsoft.dao.AdminJdbcDao;
import com.dsoft.util.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdminJdbcDaoImpl implements AdminJdbcDao {
    private static Logger logger = Logger.getLogger(AdminJdbcDaoImpl.class);
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired(required = true)
    public void setJdbcDataSource(DataSource jdbcDataSource) {
        this.jdbcTemplate = new JdbcTemplate(jdbcDataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcDataSource);
    }

    public List<Object> getPartialDataList(int page, int rp, String qtype, String query, String sortname, String sortorder, String className) {
        int start = (page - 1) * rp;
        String sql = "SELECT * "
                + " FROM " + className;
        if (!Utils.isEmpty(query)) {
            sql += " WHERE " + qtype + " LIKE ?  LIMIT ?, ? ";
        } else {
            sql += " ORDER BY " + sortname + " " + sortorder + " LIMIT ?, ? ";
        }

        List paramList = new ArrayList();
        if (!Utils.isEmpty(query)) {
            paramList.add("%" + query + "%");
        }
        paramList.add(start);
        paramList.add(rp);
        List list = jdbcTemplate.queryForList(sql, paramList.toArray());

        if (list != null && list.size() > 0)
            return list;
        return null;
    }

    @Override
    public List getPartialDataListWithJoinQuery(int page, int rp, String qtype, String query, String sortname, String sortorder, String className, String joinQuery) {
        int start = (page - 1) * rp;
        String sql = "SELECT * ," + className + ".id " + className + "_id"
                + " FROM " + className;

        if (!Utils.isEmpty(joinQuery))
            sql = sql + " " + joinQuery;

        if (!Utils.isEmpty(query)) {
            sql += " WHERE " + qtype + " LIKE ?  LIMIT ?, ? ";
        } else {
            sql += " ORDER BY " + sortname + " " + sortorder + " LIMIT ?, ? ";
        }

        List paramList = new ArrayList();
        if (!Utils.isEmpty(query)) {
            paramList.add("%" + query + "%");
        }
        paramList.add(start);
        paramList.add(rp);
        List list = jdbcTemplate.queryForList(sql, paramList.toArray());

        if (list != null && list.size() > 0)
            return list;
        return null;
    }

    public Map<String, Object> getUsers(Integer start, Integer length, String sortColName, String sortType, String searchKey) throws Exception {
        Map<String, Object> result = new HashMap();
        String sql = "SELECT *  FROM user";

        logger.debug("SMNLOG: searchKey:" + searchKey + " length:" + length);

        if (!Utils.isEmpty(searchKey)) {
            sql = sql + " WHERE name LIKE ? "
                    + "OR user_name LIKE ? "
                    + "OR father_name LIKE ? "
                    + "OR mother_name LIKE ? "
                    + "OR national_id_no LIKE ? "
                    + "OR address LIKE ? "
                    + "OR max_discount_percent LIKE ? "
                    + "OR age LIKE ? "
                    + "OR email LIKE ?";
        }

        sql = sql + " ORDER BY " + sortColName + " " + sortType + " LIMIT ?, ? ";

        List paramList = new ArrayList();
        if (!Utils.isEmpty(searchKey)) {
            paramList.add(searchKey + "%");
            paramList.add(searchKey + "%");
            paramList.add(searchKey + "%");
            paramList.add(searchKey + "%");
            paramList.add(searchKey + "%");
            paramList.add(searchKey + "%");
            paramList.add(searchKey + "%");
            paramList.add(searchKey + "%");
            paramList.add(searchKey + "%");
        }

        paramList.add(start);
        paramList.add(length);
        logger.debug("SMNLOG:sql:" + sql);

        List list = jdbcTemplate.queryForList(sql, paramList.toArray());
//        logger.debug("SMNLOG:list:"+list);

        result.put("data", list);
        if (list != null && list.size() > 0)
            result.put("total", list.size());
        else
            result.put("total", 0);
        return result;
    }

    public void deleteEntityByAnyColValue(String tableName, String colName, String colValue) throws Exception {
        String sql = "DELETE FROM " + tableName + " WHERE " + colName + " = ?";
        List paramList = new ArrayList();
        paramList.add(colValue);
        jdbcTemplate.update(sql, paramList.toArray());

    }

    public Map<String, Object> getPersons(Integer start, Integer length, String sortColName, String sortType, String searchKey) throws Exception {
        Map<String, Object> result = new HashMap();
        String sql = "SELECT * "
                + "FROM person p "
                + "WHERE 1=1 ";

        logger.debug("SMNLOG: searchKey:" + searchKey + " length:" + length);

        if (!Utils.isEmpty(searchKey)) {
            sql = sql + " AND (p.name LIKE ? "
                    + "OR p.address LIKE ? "
                    + "OR p.mobile_no LIKE ? ) "
            ;
        }

        sql = sql + " ORDER BY " + sortColName + " " + sortType + " LIMIT ?, ? ";

        List paramList = new ArrayList();
        if (!Utils.isEmpty(searchKey)) {
            paramList.add(searchKey + "%");
            paramList.add(searchKey + "%");
            paramList.add(searchKey + "%");
        }

        paramList.add(start);
        paramList.add(length);
        logger.debug("SMNLOG:sql:" + sql);

        List list = jdbcTemplate.queryForList(sql, paramList.toArray());
//        logger.debug("SMNLOG:list:"+list);

        result.put("data", list);
        if (list != null && list.size() > 0)
            result.put("total", list.size());
        else
            result.put("total", 0);
        return result;
    }

}
