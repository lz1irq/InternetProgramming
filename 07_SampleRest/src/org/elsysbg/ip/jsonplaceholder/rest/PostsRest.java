package org.elsysbg.ip.jsonplaceholder.rest;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elsysbg.ip.jsonplaceholder.Services;
import org.elsysbg.ip.jsonplaceholder.model.Post;
import org.elsysbg.ip.jsonplaceholder.model.User;
import org.elsysbg.ip.jsonplaceholder.service.PostsService;
import org.elsysbg.ip.jsonplaceholder.service.UsersService;

@Path("posts")
public class PostsRest {
	private final PostsService postsService;
	private final UsersService usersService;
	// TODO should be get from session
	private final String defaultAuthorEmail = "hello@world";

	public PostsRest() {
		postsService = Services.getPostsService();
		usersService = Services.getUsersService();
	}

	@GET
	@Path("/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Post> getPosts() {
		return postsService.getPosts();
	}

	@GET
	@Path("/{postId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Post getPost(@PathParam("postId") long postId) {
		return postsService.getPost(postId);
	}

	@POST
	@Path("/")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Post createPost(Post post) {
		final User author = usersService.getUserByEmail(defaultAuthorEmail);
		post.setAuthor(author);
		return postsService.createPost(post);
	}

	@PUT
	@Path("/{postId}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Post updatePost(@PathParam("postId") long postId, Post post) {
		return postsService.updatePost(postId, post);
	}

	@DELETE
	@Path("/{postId}")
	public void deletePost(@PathParam("postId") long postId) {
		postsService.deletePost(postId);
	}

	@GET
	@Path("/{postId}/likedbyusers")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<User> getUsersByLikedPost(@PathParam("postId") long postId) {
		final Post likedPost = postsService.getPost(postId);
		return usersService.getUsersByLikedPost(likedPost);
	}

	@POST
	@Path("/{postId}/likedbyusers")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Set<User> likePost(@PathParam("postId") long postId) {
		final User likedByUser = usersService.getUserByEmail(defaultAuthorEmail);
		return postsService.likePost(postId, likedByUser);
	}

}