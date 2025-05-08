package com.server.core.user.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository("userDAO")
public class UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(User user) {
		user.setLastUpdated(new Date());

		if(user.getId() != null) {
			this.entityManager.merge(user);
		} else {
			this.entityManager.persist(user);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		User user = this.getById(id);
		this.entityManager.remove(user);
	}

	public User getById(Long id) {
		Query query = this.entityManager.createNamedQuery(User.FIND_BY_ID);
		query.setParameter("id", id);
		query.setMaxResults(1);

		User result = (User) query.getSingleResult();

		return result;
	}

	public User findByEmail(String email) {
		Query query = this.entityManager.createNamedQuery(User.FIND_BY_EMAIL);
		query.setParameter("email", email);
		query.setMaxResults(1);

		User result = (User) query.getSingleResult();

		return result;
	}
}
