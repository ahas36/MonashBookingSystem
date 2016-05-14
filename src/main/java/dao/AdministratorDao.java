/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Administrator;

/**
 *
 * @author ahas36
 */
public interface AdministratorDao extends Dao<Administrator, String>{
    public boolean isExsist(String userName,String password);
}
