package org.elsysbg.ip.jsonplaceholder.service;

import org.elsysbg.ip.jsonplaceholder.model.Post;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * For unit testing you can use JUnit or TestNG
 */
public class PostsServiceTest {
	
	private PostsService postsService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		postsService = new PostsService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateGetPost() {
		
		String postTitle = "hi";
		String postBody = "world";
		
		final Post post = new Post();
		post.setTitle(postTitle);
		post.setBody(postBody);
		final Post result = postsService.createPost(post);
		assertNotEquals(0, result.getId());
		
		final Post fromService = postsService.getPost(post.getId());
		assertEquals(postTitle, fromService.getTitle());
		assertEquals(postBody, fromService.getBody());
		
	}

}
