package com.server.core.product.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("productDAO")
public class ProductDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Product product) {
		product.setLaspUpdate(new Date());

		if(product.getId() != null) {
			this.entityManager.merge(product);
		} else {
			this.entityManager.persist(product);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		Product product = this.getById(id);
		this.entityManager.remove(product);
	}

	public List<Product> listAll() {
		Query query = this.entityManager.createNamedQuery(Product.FIND_ALL);

		@SuppressWarnings("unchecked")
		List<Product> result = query.getResultList();

		return result;
	}

	public Product getById(Long id) {
		Query query = this.entityManager.createNamedQuery(Product.FIND_BY_ID);
		query.setParameter("id", id);

		query.setMaxResults(1);

		Product result = (Product) query.getSingleResult();

		return result;
	}

	public List<Product> listByPaging(int page, int size) {
		Query query = this.entityManager.createNamedQuery(Product.FIND_ALL);

		query.setFirstResult(page * size);
		query.setMaxResults(size);

		@SuppressWarnings("unchecked")
		List<Product> result = query.getResultList();

		return result;
	}
}
