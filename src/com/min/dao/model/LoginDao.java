package com.min.dao.model;

import com.min.bean.TypeUsers;
import com.min.bean.Users;
import com.min.dao.DAOFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private final DAOFactory daoFactory;
    private final String tableName = "users";

    public LoginDao() throws Exception{
        daoFactory = DAOFactory.getInstance();
    }
    
    /**
     * Login user to the system
     * 
     * @param user
     * @return
     * @throws SQLException 
     */
    public Users login(Users user) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " "
                + "WHERE username = ? AND password = ?";

        ResultSet rs = daoFactory.select(
                sql,
                user.getUsername(),
                user.getPassword());

        if (rs.next()) {
            user = map(rs);
        } else {
            user = null;
        }
        return user;
    }
    
    public Users map(ResultSet rs) throws SQLException {
        Users u = new Users();
        u.setId(rs.getLong("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setType(TypeUsers.getType(rs.getString("type")));
        return u;
    }

}
