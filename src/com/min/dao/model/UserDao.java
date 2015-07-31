package com.min.dao.model;

import com.min.bean.TypeUsers;
import com.min.bean.Users;
import com.min.dao.AbstractDao;
import com.min.dao.DAOFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao<Users>{
    
    private final DAOFactory daoFactory;
    private final String tableName = "users";
    
    public UserDao() throws Exception {
        daoFactory = DAOFactory.getInstance();
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
    public boolean create(Users obj) throws SQLException, Exception {
        String sql = "INSERT INTO " + getTableName()
                   + "(username, password, type) "
                    + "VALUE(?, ?, ?)";
        
        int up = getDaoFactory().update(sql, 
                obj.getUsername(), 
                obj.getPassword(), 
                obj.getType().toString());
        
        return up > 0;
    }

    @Override
    public boolean update(Users obj) throws SQLException, Exception {
        String sql = "UPDATE " + getTableName() + " "
                   + "SET username = ? , password = ? , type = ? "
                    + "WHERE id = ?";
        
        int up = getDaoFactory().update(sql, 
                obj.getUsername(), 
                obj.getPassword(), 
                obj.getType().toString(),
                obj.getId());
        
        return up > 0;
    }

    @Override
    public Users map(ResultSet rs) throws SQLException, Exception {
        Users u = new Users();
        u.setId(rs.getLong("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setType(TypeUsers.getType(rs.getString("type")));
        return u;
    }

    public ResultSet search(String text) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " "
                   + "WHERE username LIKE ?";
        return getDaoFactory().select(sql, "%" + text + "%");
    }
    
    /**
     * check if user exist
     * @param user
     * @return
     * @throws SQLException 
     */
    public boolean exist(Users user) throws SQLException, Exception {
        String sql = "SELECT * FROM " + tableName + " "
                + "WHERE username = ? ";

        ResultSet rs = daoFactory.select(
                sql,
                user.getUsername());

        if (rs.next()) {
            return map(rs) != null;
        }
        
        return false;
    }
}
