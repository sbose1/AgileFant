/**
 * 
 */
package fi.hut.soberit.agilefant.db;

import java.util.HashMap;

import fi.hut.soberit.agilefant.model.Expense;

/**
 * Repository interface class to facilitate ORM operations for Variance Report.
 *
 */

public interface VarianceReportDao { 

	 public  HashMap<String,Expense> getDetails();
	 
}