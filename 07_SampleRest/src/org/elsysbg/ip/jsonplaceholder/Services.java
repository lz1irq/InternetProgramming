package org.elsysbg.ip.jsonplaceholder;

import org.elsysbg.ip.jsonplaceholder.service.PostsService;

public class Services {

	private static PostsService postsService;

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
}