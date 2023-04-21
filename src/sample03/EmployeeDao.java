package sample03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnUtils;

public class EmployeeDao  {
	
	public List<Employee> nvlSample() throws SQLException{ 
	String sql = "select employee_id, first_name, salary, " 
			+ "nvl(commission_pct, 0.0) commission "
			+ "from employees ";
	
	List<Employee> employees = new ArrayList<>();
	
	Connection con = ConnUtils.getConnection();
	PreparedStatement ptsmt = con.prepareStatement(sql);
	ResultSet rs = ptsmt.executeQuery();
	while(rs.next()) {
		
		Employee emp = new Employee();
		emp.setId(rs.getInt("employee_id"));
		emp.setfName(rs.getString("first_name"));
		emp.setSalary(rs.getInt("salary"));
		emp.setCommission(rs.getDouble("commission"));
		
		employees.add(emp);
	}
	
	return employees;
	}
	
}

class Employee{
	
	private int id;
	private String fName;
	private int salary;
	private double commission;
	
	public Employee() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	public double getAnnualSalary() {
		return salary*12 + salary*commission*12;
	}
}