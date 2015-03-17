package org.elsysbg.ip.jsonplaceholder.service;

import java.util.LinkedList;
import java.util.List;

import org.elsysbg.ip.jsonplaceholder.model.Post;

public class PostsService {

	private final List<Post> posts = new LinkedList<Post>();
	
	private long lastPostId = 0;

	public List<Post> getPosts() {
		return posts;
	}
	
	public Post getPost(long postId) {
		for (Post next : posts) {
			if (next.getId() == postId) {
				return next;
			}
		}
		return null;
	}
	
	// synchronized because of lastPostId
	public synchronized Post createPost(Post post) {
		lastPostId++;
		post.setId(lastPostId);
		posts.add(post);
		return post;
	}
	public Post updatePost(long postId, Post post) {
		//don't allow the ID to be changed
		final Post updated = getPost(postId);
		updated.setBody(post.getBody());
		updated.setTitle(post.getTitle());
		return post;
	}
	public void deletePost(long postId) {
		final Post toBeDeleted = getPost(postId);
		posts.remove(toBeDeleted);
	}

}
