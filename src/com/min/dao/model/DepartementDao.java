package com.min.dao.model;

import com.min.bean.AbstractBean;
import com.min.dao.AbstractDao;
import com.min.dao.DAOFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nabil
 */
public class DepartementDao extends AbstractDao<AbstractBean>{

    
    @Override
    public String getTableName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DAOFactory getDaoFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(AbstractBean obj) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(AbstractBean obj) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractBean map(ResultSet rs) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
