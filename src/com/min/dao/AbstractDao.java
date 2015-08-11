package com.min.dao;

import com.min.bean.AbstractBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCName
 * @param <T>
 */
public abstract class AbstractDao<T extends AbstractBean> implements DAO<T>{
    
    private final String primaryKey;
    
    public AbstractDao(){
        this.primaryKey = "id";
    }
    
    /**
     * get primary key of table
     * @return
     */
    public String getPrimaryKey(){
        return primaryKey;
    }
    
    /**
     * get table name
     * 
     * @return 
     */
    public abstract String getTableName();

    /**
     * 
     * @return 
     */
    public abstract DAOFactory getDaoFactory();
    
    /**
     *
     * @return
     * @throws java.sql.SQLException
     */
    public  ResultSet select() throws SQLException {
        return select("*");
    }
    
    /**
     *
     * @param columns
     * @return
     * @throws java.sql.SQLException
     */
    public  ResultSet select(String columns) throws SQLException {
        String sql = "SELECT " + columns + " FROM " + getTableName();
        return getDaoFactory().select(sql);
    }

    @Override
    public List<T> get() throws SQLException, Exception {
        List<T> items = new ArrayList<>();
        
        ResultSet rs = select();
        while(rs.next()) {
            items.add(map(rs));
        }
        return items;
    }

    @Override
    public boolean delete(T obj) throws SQLException, Exception {
        String sql = "DELETE FROM " + getTableName()  + " "
                   + "WHERE " + getPrimaryKey() + " = ?";
        
        return getDaoFactory().update(sql, obj.getId()) > 0;
    }

    @Override
    public T find(Object id) throws SQLException, Exception {
        T item = null;
        String sql = "SELECT * FROM " + getTableName() + " "
                   + "WHERE " + getPrimaryKey() + " = ?";
        
        ResultSet rs = getDaoFactory().select(sql, id);
        if(rs.next()) {
            item = map(rs);
        }
        
        return item;
    }
  
}
