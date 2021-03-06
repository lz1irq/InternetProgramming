package org.elsysbg.ip.jsonplaceholder.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.elsysbg.ip.jsonplaceholder.Services;
import org.elsysbg.ip.jsonplaceholder.model.Post;
import org.elsysbg.ip.jsonplaceholder.model.User;

/**
 * TODO write tests for users service
 */
public class UsersService {

	private final EntityManagerFactory emf;

	public UsersService() {
		emf = Services.getEntityManagerFactory();
	}

	public User getUserByEmail(String email) {
		final EntityManager em = emf.createEntityManager();
		try {
			return em.createNamedQuery("userByEmail", User.class)
					.setParameter("email", email).getSingleResult();
		} finally {
			em.close();
		}
	}

	public User getUser(long userId) {
		final EntityManager em = emf.createEntityManager();
		try {
			return em.find(User.class, userId);
		} finally {
			em.close();
		}
	}

	public User createUser(User user) {
		EntityManager em = emf.createEntityManager();
		final EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(user);
			tx.commit();
			return user;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}

	}

	public List<User> getUsersByLikedPost(Post likedPost) {
		final EntityManager em = emf.createEntityManager();
		try {
			return em.createNamedQuery("usersByLikedPost", User.class)
					.setParameter("likedPost", likedPost).getResultList();
		} finally {
			em.close();
		}
	}

}