/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Administrator;
import javax.persistence.Query;

/**
 *
 * @author ahas36
 */
public class JpaAdministratorDao extends JpaDao<Administrator, String> implements AdministratorDao {

    public JpaAdministratorDao() {
        super(Administrator.class);
    }

    @Override
    public boolean isExsist(String userName, String password) {
        Query q = entityManager.createQuery("SELECT a FROM Administrator as a where a.userName=:u and a.password=:p", Administrator.class);
        q.setParameter("u", userName);
        q.setParameter("p", password);
        Administrator admin = null;
        try {
            admin = (Administrator) q.getSingleResult();
        } catch (Exception e) {
        }
        return admin != null;
    }

}
