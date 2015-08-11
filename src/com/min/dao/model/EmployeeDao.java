package com.min.dao.model;

import com.min.bean.Employee;
import com.min.dao.AbstractDao;
import com.min.dao.DAOFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao extends AbstractDao<Employee>{

    private final String tableName = "emploies";
    private final DAOFactory daoFactory;

    public EmployeeDao() throws Exception {
        daoFactory = DAOFactory.getInstance();;
    }
    
    
    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public DAOFactory getDaoFactory() {
        return daoFactory;
    }

    @Override
    public boolean create(Employee obj) throws SQLException, Exception {
        String sql = "INSERT INTO " + getTableName()
                   + "(name, salary, job) "
                    + "VALUE(?, ?, ?)";
        
        int up = getDaoFactory().update(sql, 
                obj.getName(), 
                obj.getSalary(), 
                obj.getJob());
        
        return up > 0;
    }

    @Override
    public boolean update(Employee obj) throws SQLException, Exception {
        String sql = "UPDATE " + getTableName() + " "
                   + "SET name = ? , salary = ? , job = ? "
                    + "WHERE id = ?";
        
        int up = getDaoFactory().update(sql, 
                obj.getName(), 
                obj.getSalary(), 
                obj.getJob(),
                obj.getId());
        
        return up > 0;
    }

    @Override
    public Employee map(ResultSet rs) throws SQLException, Exception {
        Employee e = new Employee();
        e.setId(rs.getInt("id"));
        e.setName(rs.getString("name"));
        e.setSalary(rs.getInt("salary"));
        e.setJob(rs.getString("job"));
        return e;
    }
    
}
