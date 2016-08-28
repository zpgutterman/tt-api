package model;
// Generated Aug 28, 2016 2:18:37 PM by Hibernate Tools 4.3.4.Final

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

/**
 * Ingredient generated by hbm2java
 */
@Entity
@Table(name = "ingredient")
public class Ingredient implements java.io.Serializable {

	private Integer id;
	private IngredientType ingredientType;
	private String name;
	private Set<RecipeStepIngredient> recipeStepIngredients = new HashSet<RecipeStepIngredient>(0);

	public Ingredient() {
	}

	public Ingredient(String name) {
		this.name = name;
	}

	public Ingredient(IngredientType ingredientType, String name, Set<RecipeStepIngredient> recipeStepIngredients) {
		this.ingredientType = ingredientType;
		this.name = name;
		this.recipeStepIngredients = recipeStepIngredients;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	public IngredientType getIngredientType() {
		return this.ingredientType;
	}

	public void setIngredientType(IngredientType ingredientType) {
		this.ingredientType = ingredientType;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ingredient")
	public Set<RecipeStepIngredient> getRecipeStepIngredients() {
		return this.recipeStepIngredients;
	}

	public void setRecipeStepIngredients(Set<RecipeStepIngredient> recipeStepIngredients) {
		this.recipeStepIngredients = recipeStepIngredients;
	}

}
