package org.elsysbg.ip.jsonplaceholder.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
// name specifies the name of the table that will hold this entity
@Entity(name = "Posts")

@NamedQueries({
	@NamedQuery(name = "allPosts", 
		query = "SELECT p from Posts p"),
	@NamedQuery(name = "postsByAuthor", 
		query = "SELECT p from Posts p where p.author=:author")
})

public class Post {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(optional=true)
	private User author;
	
	@Column(nullable=false, length=50)
	private String title;
	
	@Column(nullable=false, length=500)
	private String body;
	
	@ManyToMany
	private Set<User> likedByUsers = new HashSet<User>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	@XmlTransient
	public Set<User> getLikedByUsers() {
		return likedByUsers;
	}
	
	public void setLikedByUsers(Set<User> likedByUsers) {
		this.likedByUsers = likedByUsers;
	}

}
