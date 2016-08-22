package model;
// Generated Aug 22, 2016 2:10:35 PM by Hibernate Tools 4.3.4.Final

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
import javax.persistence.UniqueConstraint;

/**
 * Unit generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "unit", uniqueConstraints = @UniqueConstraint(columnNames = "unit_value"))
public class Unit implements java.io.Serializable {

	private Integer unitId;
	private UnitType unitType;
	private String unitValue;
	private Set<RecipeStepIngredient> recipeStepIngredients = new HashSet<RecipeStepIngredient>(0);

	public Unit() {
	}

	public Unit(UnitType unitType, String unitValue) {
		this.unitType = unitType;
		this.unitValue = unitValue;
	}

	public Unit(UnitType unitType, String unitValue, Set<RecipeStepIngredient> recipeStepIngredients) {
		this.unitType = unitType;
		this.unitValue = unitValue;
		this.recipeStepIngredients = recipeStepIngredients;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "unit_id", unique = true, nullable = false)
	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_type", nullable = false)
	public UnitType getUnitType() {
		return this.unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	@Column(name = "unit_value", unique = true, nullable = false, length = 45)
	public String getUnitValue() {
		return this.unitValue;
	}

	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
	public Set<RecipeStepIngredient> getRecipeStepIngredients() {
		return this.recipeStepIngredients;
	}

	public void setRecipeStepIngredients(Set<RecipeStepIngredient> recipeStepIngredients) {
		this.recipeStepIngredients = recipeStepIngredients;
	}

}