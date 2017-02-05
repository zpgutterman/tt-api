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
package webService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.UserDAO;
import model.User;
import post.LoginRequest;


/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the users
 * table.
 */
@Path("/user")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {

	@Inject
	private Validator validator;

	@Inject
	private UserDAO userDao;

	private Integer countUsers() {
		return userDao.count();
	}

	@OPTIONS
	public Response options() {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "accept, content-type, x-auth-token, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.header("Access-Control-Allow-Credentials", true).build();
	}


	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public User lookupUserById(@PathParam("id") int id) {
		User user = userDao.findById(id);
		if (user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return user;
	}
	
	/**
	 * Checks for existing token for user. If found, user is already logged in. TODO implement
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User loggedInUser() {
		User user = null;
		if (user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return user;
	}

	/**
	 * Logs the user in by checking credentials. TODO encrypt/decrypt the passwords
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User loginUser(final LoginRequest request) {
		
		String password = request.password;
		String email = request.email;
		User foundUser;
		foundUser = userDao.findByEmail(email);
		if (foundUser != null){
			if (foundUser.getPassword().equals(password)){
				System.out.println("Found user, logging in...");
				String token = issueToken(foundUser);
				return foundUser;
			}else {
				//TODO handle incorrect password
				System.out.println("Password incorrect");
				return null;
			}
		} else {
			//TODO handle incorrect email
			System.out.println("Email not found");
			return null;
		}
	}


	private String issueToken(User foundUser) {
		String token = "";
		return token;
	}

	@DELETE
	@Path("{userid:[0-9][0-9]*}")
	public void deleteUser(@PathParam("userid") int id) {
		userDao.removeUser(lookupUserById(id));
	}

	
	
}
