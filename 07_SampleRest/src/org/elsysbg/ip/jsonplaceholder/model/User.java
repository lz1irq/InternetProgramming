package org.elsysbg.ip.jsonplaceholder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Model is copied by http://jsonplaceholder.typicode.com/
 */
@XmlRootElement
//name specifies the name of the table that will hold this entity
@Entity(name="Users")
@NamedQueries({
	@NamedQuery(name = "userByEmail", 
		query = "SELECT u from Users u where u.email=:email")
})
public class User {
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable=false, length=50, unique=true)
	private String email;
	
	// TODO password should be stored in DB as hash+salt
	// TODO read about storing passwords, hashing, salting, etc.
	@Column(nullable=false, length=50)
	private String password;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlTransient
	//sending out the password is a bad idea
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}