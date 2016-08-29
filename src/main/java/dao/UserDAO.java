/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import model.User;
import model.UserGroup;

@ApplicationScoped
@Transactional
public class UserDAO {

	@PersistenceContext(unitName = "more-of-everything")
	private EntityManager em;

	public User findById(int id) {
		System.out.println("finding user by id");
		User foundUser = em.find(User.class, id);
		System.out.println("Name: " + foundUser.getUsername());
		return foundUser;
	}

	public Integer count() {
		Query query = em.createQuery("SELECT COUNT (u.userid) FROM User u");
		return ((Long) query.getSingleResult()).intValue();
	}

	public List<User> findUsers(int startPosition, int maxResults, String sortFields, String sortDirections) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u ORDER BY u." + sortFields + " " + sortDirections,
				User.class);
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	public User findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> user = criteria.from(User.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(member).where(cb.equal(member.get(Member_.email),
		// email));
		criteria.select(user).where(cb.equal(user.get("email"), email));
		return em.createQuery(criteria).getSingleResult();
	}

	public List<User> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> user = criteria.from(User.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		criteria.select(user).orderBy(cb.asc(user.get("username")));
		return em.createQuery(criteria).getResultList();
	}

	public User updateUser(User userToUpdate) {
		return em.merge(userToUpdate);
	}

	public void removeUser(User userToDelete) {
		em.remove(em.merge(userToDelete));

	}

	public UserGroup findGroupById(int id) {
		UserGroup foundUserGroup = em.find(UserGroup.class, id);
		return foundUserGroup;
	}

	public void createUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		newUser.setUserGroup(user.getUserGroup());
		em.persist(newUser);

	}

	public List<UserGroup> findUserGroups() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserGroup> criteria = cb.createQuery(UserGroup.class);
		Root<UserGroup> userGroup = criteria.from(UserGroup.class);
		criteria.select(userGroup);
		return em.createQuery(criteria).getResultList();
	}

	public void removeUserGroup(UserGroup userGroupToDelete) {
		em.remove(em.merge(userGroupToDelete));
		
	}

	public void createUserGroup(UserGroup userGroup) {
		UserGroup newUserGroup = new UserGroup();
		newUserGroup.setName(userGroup.getName());
		em.persist(newUserGroup);
	}
}
