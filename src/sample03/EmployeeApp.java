package sample03;

import java.sql.SQLException;
import java.util.List;

public class EmployeeApp {
	public static void main(String[] args) throws SQLException{
		
		EmployeeDao dao = new EmployeeDao();
		
		List<Employee> employees = dao.nvlSample();
		for(Employee employee : employees) {
			int id = employee.getId();
			String name = employee.getfName();
			int salary = employee.getSalary();
			double commission = employee.getCommission();
			double annualsalary = employee.getAnnualSalary();
			
			System.out.println(id);
			System.out.println(name);
			System.out.println(salary);
			System.out.println(annualsalary);
			
		}
	}
}
