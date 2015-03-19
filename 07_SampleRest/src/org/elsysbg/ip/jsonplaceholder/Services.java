package org.elsysbg.ip.jsonplaceholder;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.elsysbg.ip.jsonplaceholder.service.PostsService;

public class Services {

	private static PostsService postsService;
	private static EntityManagerFactory entityManagerFactory;

	// TODO synchronized should be done in a better way in real projects
	public synchronized static PostsService getPostsService() {
		// lazy loading
		if (postsService == null) {
			postsService = new PostsService();
		}
		return postsService;
	}
	
	// for tests purposes
	static void setPostsService(PostsService postsService) {
		Services.postsService = postsService;
	}
	
	public synchronized static EntityManagerFactory getEntityManagerFactory() {
				// lazy loading
				if (entityManagerFactory == null) {
					try {
						Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
					} catch (ClassNotFoundException e) {
						throw new IllegalStateException("No driver", e);
					}
					entityManagerFactory = Persistence.createEntityManagerFactory("07_SampleRest");
				}
				return entityManagerFactory;
			}
			
			// for tests purposes
			static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
				Services.entityManagerFactory = entityManagerFactory;
			}
			
}