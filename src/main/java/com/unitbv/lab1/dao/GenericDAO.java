package com.unitbv.lab1.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class GenericDAO<T> {

	private Class<T> type;

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	public GenericDAO(String persistenceUnitName, Class<T> type) {
		this.type = type;
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	public void close() {
		entityManagerFactory.close();
	}

	public T createOrUpdate(T entity) {
		try {
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			entityManager.getTransaction().rollback();
			return null;
		} finally {
			entityManager.close();
		}
	}
}