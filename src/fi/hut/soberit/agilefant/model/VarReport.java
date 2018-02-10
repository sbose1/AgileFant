/**
 * 
 */
package fi.hut.soberit.agilefant.model;

import java.io.Serializable;

/**
 * Business model to hold state of Variance Report tasks mapped at project level.
 *
 */
public class VarReport implements Serializable {
	
	//private String taskID;
	//private String taskName;
	private Integer minutesSpent;
	private Integer originalEstimate;
	private Integer actualExpense;
	
	private String taskName;
	private String projectName;
	private String projectID;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String storyName) {
		this.taskName = storyName;
	}

	/*public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}*/

	public Integer getMinutesSpent() {
		return minutesSpent;
	}

	public void setMinutesSpent(Integer minutesSpent) {
		this.minutesSpent = minutesSpent;
	}

	public Integer getOriginalEstimate() {
		return originalEstimate;
	}

	public void setOriginalEstimate(Integer originalEstimate) {
		this.originalEstimate = originalEstimate;
	}

	public Integer getActualExpense() {
		return actualExpense;
	}

	public void setActualExpense(Integer actualExpense) {
		this.actualExpense = actualExpense;
	}

	/**
	 * Model for Variance Report
	 */
	public VarReport() {
		// TODO Auto-generated constructor stub
	}

}
