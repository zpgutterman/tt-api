package model;
// Generated Aug 28, 2016 2:18:37 PM by Hibernate Tools 4.3.4.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	private Integer userid;
	private UserGroup userGroup;
	private Date createTime;
	private String email;
	private String password;
	private String username;
	private Set<Dish> dishes = new HashSet<Dish>(0);

	public User() {
	}

	public User(UserGroup userGroup, String password, String username) {
		this.userGroup = userGroup;
		this.password = password;
		this.username = username;
	}

	public User(UserGroup userGroup, Date createTime, String email, String password, String username,
			Set<Dish> dishes) {
		this.userGroup = userGroup;
		this.createTime = createTime;
		this.email = email;
		this.password = password;
		this.username = username;
		this.dishes = dishes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "userid", unique = true, nullable = false)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group", nullable = false)
	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "username", nullable = false, length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//	public Set<Dish> getDishes() {
//		return this.dishes;
//	}
//
//	public void setDishes(Set<Dish> dishes) {
//		this.dishes = dishes;
//	}

}
