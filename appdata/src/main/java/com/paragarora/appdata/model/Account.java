package com.paragarora.appdata.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
@Table(name="account")
public class Account implements Serializable {
	private static final long serialVersionUID = 3050803285097715656L;
	private long id;
	private String accountUnique;
	private String name;
	private String website;
	private String address;
	private String phone;
	private Date createdAt;
	private User admin;
	private Set<User> users = new HashSet<User>(0);
	private Set<System> systems = new HashSet<System>(0);
	
	public Account() {
	}

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="ACCOUNT_UNIQUE", nullable=false, unique=true, length=255)
	public String getAccountUnique() {
		return accountUnique;
	}

	public void setAccountUnique(String accountUnique) {
		this.accountUnique = accountUnique;
	}

	@Column(name="NAME", nullable=false, length=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="WEBSITE", length=255)
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column( name = "ADDRESS" )
	@Lob
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="PHONE", length=63)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_AT", nullable=false, length=31)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADMIN_ID")
	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
