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
import org.elsysbg.ip.jsonplaceholder.service.UsersService;

@Path("users")
public class UsersRest {
	private final UsersService usersService;
	private final PostsService postsService;

	public UsersRest() {
		usersService = Services.getUsersService();
		postsService = Services.getPostsService();
	}
	

	@GET
	@Path("/{userId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	// @PathParam binds url parameter (postId) to method parameter (postId)
	public User getUser(@PathParam("userId") long userId) {
		return usersService.getUser(userId);
	}
	
	@GET
	@Path("/{userId}/posts")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	// @PathParam binds url parameter (postId) to method parameter (postId)
	public List<Post> getUserPosts(@PathParam("userId") long userId) {
		final User author = usersService.getUser(userId);
		return postsService.getPostsByAuthor(author);
	}
	
	@POST
	@Path("/")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User createUser(User user) {
		// TODO set author by user session
//		post.setAuthor(defaultAuthor);
		return usersService.createUser(user);
	}
	// TODO implement update, delete
}