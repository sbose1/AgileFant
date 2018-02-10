/**
 * 
 */
package fi.hut.soberit.agilefant.db.hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;

import fi.hut.soberit.agilefant.config.DBConnectivity;
import fi.hut.soberit.agilefant.db.ProjectDAO;
import fi.hut.soberit.agilefant.db.VarianceReportDao;
import fi.hut.soberit.agilefant.model.BacklogHistoryEntry;
import fi.hut.soberit.agilefant.model.Expense;
import fi.hut.soberit.agilefant.model.Project;
import fi.hut.soberit.agilefant.model.Story;
import fi.hut.soberit.agilefant.model.StoryState;
import fi.hut.soberit.agilefant.model.User;
import fi.hut.soberit.agilefant.model.VarReport;
import fi.hut.soberit.agilefant.transfer.ProjectMetrics;

/**
 * Repository class to facilitate ORM operations for Variance Report.
 *
 */
@Repository("VarianceReportDAO")
public class VarianceReportDaoImpl implements VarianceReportDao {

	public HashMap<String, Expense> getDetails() {

		Connection conn = null;
		Statement stmt = null;

		ResultSet rs = null;
		conn = DBConnectivity.dataBaseConnect();

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Query database to fetch tasks mapped to stories and Projects:
		String sql = "select story.id, task.id, task.name, he.minutesSpent, task.originalestimate, user.cost, he.date, project.id, project.name from hourentries he join users user on user.id = he.user_id join tasks task on task.id = he.task_id join stories story on story.id=task.story_id join backlogs project on project.id=story.backlog_id;";

		try {
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<VarReport> data = new ArrayList<VarReport>();
		HashMap<String, Expense> projectMap = new HashMap<String, Expense>();

		try {
			while (rs.next()) {

				/*
				 * Date date = rs.getDate("date"); //int month= date.getMonth();
				 * 
				 * 
				 * String date3= date.toString(); String
				 * month2=date3.substring(5,7);
				 * 
				 * 
				 * int month=0; DateFormat inDF= new
				 * SimpleDateFormat("yyyy-mm-dd"); Date date2; try { date2 =
				 * inDF.parse(date3); Calendar cal=Calendar.getInstance();
				 * cal.setTime(date2); month= cal.get(Calendar.MONTH);
				 * 
				 * } catch (ParseException e) { // TODO Auto-generated catch
				 * block e.printStackTrace(); }
				 */

				VarReport varReport = new VarReport();
				varReport.setTaskName((rs.getString(3)));
				varReport.setProjectName((rs.getString(9)));
				varReport.setProjectID((rs.getString(8)));
				varReport.setMinutesSpent(Integer.parseInt(rs.getString(4)) / 60);
				// varReport.setMinutesSpent(y);
				// varReport.setStoryName(rs.getString("story.name"));
				varReport.setOriginalEstimate(
						Integer.parseInt(rs.getString(5)) / 60 * (Integer.parseInt(rs.getString(6))));
				varReport.setActualExpense(
						(Integer.parseInt((rs.getString(4))) / 60) * (Integer.parseInt(rs.getString(6))));
				// varReport.setActualExpense(y*(Integer.parseInt(rs.getString("cost"))));
				Expense expense = new Expense();

				// Conditional checks to map Projects to respective stories,
				// tasks and generating costs incurred:
				if (projectMap.get(varReport.getProjectName()) != null) {
					int oldEE = projectMap.get(varReport.getProjectName()).getEstimatedExpense();

					projectMap.get(varReport.getProjectName())
							.setEstimatedExpense(oldEE + varReport.getOriginalEstimate());

					int oldAE = projectMap.get(varReport.getProjectName()).getActualExpense();

					projectMap.get(varReport.getProjectName()).setActualExpense(oldAE + varReport.getActualExpense());

				} else {
					expense.setEstimatedExpense(varReport.getOriginalEstimate());
					expense.setActualExpense(varReport.getActualExpense());
					projectMap.put(varReport.getProjectName(), expense);

				}

				data.add(varReport);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Return Report data to Service layer;
		return projectMap;

	}

}
