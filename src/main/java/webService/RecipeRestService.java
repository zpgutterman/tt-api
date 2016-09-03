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
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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

import dao.RecipeDAO;
import model.Recipe;
import model.RecipeStep;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the users
 * table.
 */
@Path("/recipes")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipeRestService {

	@Inject
	private RecipeDAO recipeDao;

	@OPTIONS
	public Response options() {
		return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.header("Access-Control-Allow-Credentials", true).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> listAllDishes() {
		return recipeDao.findAllOrderedByName();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Recipe lookupRecipeById(@PathParam("id") int id) {
		Recipe recipe = recipeDao.findById(id);
		if (recipe == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return recipe;
	}

	@GET
	@Path("/{id:[0-9][0-9]*}/steps")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RecipeStep> lookupRecipeSteps(@PathParam("id") int id) {
		Recipe recipe = recipeDao.findById(id);
		List<RecipeStep> foundSteps = null;
		if (recipe == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		} else {
			foundSteps = recipeDao.getRecipeStepsById(recipe.getId());
			return foundSteps;
		}

	}

	/**
	 * Creates a new user from the values provided. Performs validation, and
	 * will return a JAX-RS response with either 200 ok, or with a map of
	 * fields, and related errors.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRecipe(Recipe recipe) {

		Response.ResponseBuilder builder = null;

		try {
			// TODO Validate User

			// TODO Register User
			recipeDao.createRecipe(recipe);
			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	/**
	 * Creates a JAX-RS "Bad Request" response including a map of all violation
	 * fields, and their message. This can then be used by clients to show
	 * violations.
	 * 
	 * @param violations
	 *            A set of violations that needs to be reported
	 * @return JAX-RS response containing all violations
	 */
	private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {

		Map<String, String> responseObj = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}

		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}

	@DELETE
	@Path("{recipeid:[0-9][0-9]*}")
	public void deleteRecipe(@PathParam("recipeid") int id) {
		recipeDao.removeRecipe(lookupRecipeById(id));
	}
}
