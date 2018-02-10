/**
 * 
 */
package fi.hut.soberit.agilefant.model;

import java.io.Serializable;

/**
 * Business model to hold state of Expense at project level.
 *
 */
public class Expense implements Serializable {
	
	private Integer estimatedExpense;
	public Integer getEstimatedExpense() {
		return estimatedExpense;
	}

	public void setEstimatedExpense(Integer estimatedExpense) {
		this.estimatedExpense = estimatedExpense;
	}

	public Integer getActualExpense() {
		return actualExpense;
	}

	public void setActualExpense(Integer actualExpense) {
		this.actualExpense = actualExpense;
	}

	private Integer actualExpense;

	/**
	 * 
	 */
	public Expense() {
		// TODO Auto-generated constructor stub
	}

}
