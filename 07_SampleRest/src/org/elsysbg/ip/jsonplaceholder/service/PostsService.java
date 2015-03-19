package org.elsysbg.ip.jsonplaceholder.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.elsysbg.ip.jsonplaceholder.Services;
import org.elsysbg.ip.jsonplaceholder.model.Post;

/**
 * TODO write tests for posts service
 */
public class PostsService {
	
	private final EntityManagerFactory emf;
	public PostsService() {
		emf = Services.getEntityManagerFactory();
	}

	public List<Post> getPosts() {
		final EntityManager em =
			emf.createEntityManager();
		try {
			return em
				.createNamedQuery("allPosts", Post.class)
				.getResultList();
		} finally {
			em.close();
		}
	}
	
	public Post getPost(long postId) {
		final EntityManager em =
			emf.createEntityManager();
		try {
			return em.find(Post.class, postId);
		} finally {
			em.close();
		}
	}
	// synchronized because of lastPostId
	public synchronized Post createPost(Post post) {
		EntityManager em =
			emf.createEntityManager();
		final EntityTransaction tx =
			em.getTransaction();
		try {
			tx.begin();
			em.persist(post);
			tx.commit();
			return post;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
		
	}
	public Post updatePost(long postId, Post post) {
		EntityManager em =
			emf.createEntityManager();
		final EntityTransaction tx =
			em.getTransaction();
		try {
			tx.begin();
			final Post fromDb = em.find(Post.class, postId);
			if (fromDb != null) {
				// only body and title should be updated
				// author should not be changed
				// disadvantage is that we can miss some
				// fields that can be updated
				
				fromDb.setBody(post.getBody());
				fromDb.setTitle(post.getTitle());
				em.merge(fromDb);
			}
			tx.commit();
			return fromDb;
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
	}
	public void deletePost(long postId) {
		EntityManager em =
			emf.createEntityManager();
		final EntityTransaction tx =
			em.getTransaction();
		try {
			tx.begin();
			final Post fromDb = em.find(Post.class, postId);
			if (fromDb != null) {
				em.remove(fromDb);
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
	}

}