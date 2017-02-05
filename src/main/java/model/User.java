package model;
// Generated Jan 29, 2017 8:12:55 PM by Hibernate Tools 5.1.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", schema = "public")
public class User implements java.io.Serializable {

	private int userId;
	private String username;
	private String password;
	private int userClass;
	private String email;
	private Date createTime;

	public User() {
	}

	public User(int userId, String username, String password, int userClass, String email, Date createTime) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userClass = userClass;
		this.email = email;
		this.createTime = createTime;
	}

	@Id

	@Column(name = "user_id", unique = true, nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "username", nullable = false, length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "user_class", nullable = false)
	public int getUserClass() {
		return this.userClass;
	}

	public void setUserClass(int userClass) {
		this.userClass = userClass;
	}

	@Column(name = "email", nullable = false, length = 120)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "create_time", nullable = false, length = 21)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
