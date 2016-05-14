/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author ahas36
 */
public interface Dao<E, K> {

    E create(E entity);

    void remove(E entity);

    E update(E enity);

    List<E> findAll();

    E findById(K id);
}
