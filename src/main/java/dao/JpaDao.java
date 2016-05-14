/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author ahas36
 */
public class JpaDao<E, K> implements Dao<E, K> {

    protected final Class<E> entityClass;

    public JpaDao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("au.edu.monash_MonashBookingSystem_jar_1.0-SNAPSHOTPU");
    protected EntityManager entityManager = emf.createEntityManager();

    @Override
    public E create(E entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.persist(entity);
            entityManager.flush();
        } catch (Exception e) {

        } finally {
            transaction.commit();
        }

        return entity;
    }

    @Override
    public void remove(E entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.merge(entity));
            entityManager.flush();
        } catch (Exception e) {

        } finally {
            transaction.commit();
        }
    }

    @Override
    public E update(E entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            entityManager.flush();
        } catch (Exception e) {

        } finally {
            transaction.commit();
        }
        return entity;
    }

    @Override
    public List<E> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public E findById(K id) {
        return entityManager.find(entityClass, id);
    }

    public void flush() {
        entityManager.flush();
    }
}
