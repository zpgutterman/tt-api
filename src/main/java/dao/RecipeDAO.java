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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import model.Recipe;
import model.RecipeStep;

@ApplicationScoped
@Transactional
public class RecipeDAO {

	@PersistenceContext(unitName = "more-of-everything")
	private EntityManager em;

	public Integer count() {
		Query query = em.createQuery("SELECT COUNT (r.id) FROM Recipe r");
		return ((Long) query.getSingleResult()).intValue();
	}

	public List<Recipe> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteria = cb.createQuery(Recipe.class);
		Root<Recipe> recipe = criteria.from(Recipe.class);
		criteria.select(recipe).orderBy(cb.asc(recipe.get("name")));
		return em.createQuery(criteria).getResultList();
	}

	public Recipe findById(int id) {
		Recipe foundRecipe = em.find(Recipe.class, id);
		return foundRecipe;
	}

	public void createRecipe(Recipe recipe) {
		em.persist(recipe);
		
	}

	public void removeRecipe(Recipe recipe) {
		em.remove(em.merge(recipe));		
	}

	//Gets the steps for a recipe and sorts them in order based on a recipe id
	public List<RecipeStep> getRecipeStepsById(int recipeId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<RecipeStep> criteria = cb.createQuery(RecipeStep.class);
		Root<RecipeStep> recipeStep = criteria.from(RecipeStep.class);
		criteria.select(recipeStep).orderBy(cb.asc(recipeStep.get("step_number")));
		criteria.where(cb.equal(recipeStep.get("recipe_id"), recipeId));
		return em.createQuery(criteria).getResultList();
	}
	
}
