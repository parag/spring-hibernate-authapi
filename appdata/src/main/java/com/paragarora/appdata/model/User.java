package com.paragarora.appdata.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = -5272195753237448298L;
	private long id;
	private String userUnique;
	private String fullName;
	private String email;
	private String password;
	private String salt;
	private String sessionid;
	private Date lastLogin;
	private Date createdAt;
	private Account account;
	
	public User() {
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

	@Column(name="USER_UNIQUE", nullable=false, unique=true, length=255)
	public String getUserUnique() {
		return userUnique;
	}

	public void setUserUnique(String userUnique) {
		this.userUnique = userUnique;
	}

	@Column(name="FULL_NAME", nullable=false, length=63)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name="EMAIL", nullable=false, unique=true, length=63)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	@Column(name="PASSWORD", nullable=false, length=255)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	@Column(name="SALT", nullable=false, length=255)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name="SESSIONID", nullable=false, length=1023)
	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_LOGIN", nullable=false, length=31)
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "User: [ID="+id+", FULL_NAME="+fullName+", EMAIL="+email+", CREATED_AT="+createdAt+"]";
	}
	
	public void cloneUser(User user) {
		this.setId(user.getId());
		this.setAccount(user.getAccount());
		this.setCreatedAt(user.getCreatedAt());
		this.setEmail(user.getEmail());
		this.setLastLogin(user.getLastLogin());
		this.setPassword(user.getPassword());
		this.setSalt(user.getSalt());
		this.setSessionid(user.getSessionid());
		this.setUserUnique(user.getUserUnique());
	}
	
}
