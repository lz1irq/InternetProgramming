package org.elsysbg.ip.jsonplaceholder.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elsysbg.ip.jsonplaceholder.Services;
import org.elsysbg.ip.jsonplaceholder.model.Post;
import org.elsysbg.ip.jsonplaceholder.model.User;
import org.elsysbg.ip.jsonplaceholder.service.PostsService;

@Path("posts")
public class PostsRest {
	private final PostsService postsService;
	private final User defaultAuthor;
	public PostsRest() {
		postsService = Services.getPostsService();
		
		// TODO should be authenticated via session
		defaultAuthor = new User();
		defaultAuthor.setEmail("azure@diamond");
		defaultAuthor.setPassword("hunter2");
	}

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Post> getPosts() {
		return postsService.getPosts();
	}
	
	
	@GET
	@Path("/{postID}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Post getPost(@PathParam("postID") long postID) {
		return postsService.getPost(postID);
	}
	
	
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Post createPost(Post post) {
		// TODO set author by user session
		post.setUser(defaultAuthor);
		return postsService.createPost(post);
	}
	
	public Post updatePost(Post post) {
		return postsService.updatePost(post);
	}
	
	public void deletePost(long postId) {
		postsService.deletePost(postId);
	}
}