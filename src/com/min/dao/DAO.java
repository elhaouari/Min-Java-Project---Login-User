package com.min.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    /**
     * Méthode de selection des informations
     *
     * @return T
     * @throws java.sql.SQLException
     */
    public abstract List<T> get() throws SQLException, Exception;

    /**
     * Méthode de création
     *
     * @param obj
     * @return boolean
     * @throws java.sql.SQLException
     */
    public abstract boolean create(T obj) throws SQLException, Exception;

    /**
     * Méthode pour effacer
     *
     * @param obj
     * @return boolean
     * @throws java.sql.SQLException
     */
    public abstract boolean delete(T obj) throws SQLException, Exception;

    /**
     * Méthode de mise à jour
     *
     * @param obj
     * @return boolean
     * @throws java.sql.SQLException
     */
    public abstract boolean update(T obj) throws SQLException, Exception;

    /**
     * Méthode de recherche of informations
     *
     * @param id
     * @return T
     * @throws java.sql.SQLException
     */
    public abstract T find(Object id) throws SQLException, Exception;

    /**
     * Méthode de mapping des informations dans ResultSet
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public abstract T map(ResultSet rs) throws SQLException, Exception;

}
