package com.task7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	public void addEmployee(Employee emp) {
		String sql = "INSERT INTO employees (name, email, salary) VALUES (?, ?, ?)";

		try (Connection conn = DataBaseCon.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, emp.getName());
			stmt.setString(2, emp.getEmail());
			stmt.setDouble(3, emp.getSalary());

			stmt.executeUpdate();
			System.out.println("Employee added.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();
		String sql = "SELECT * FROM employees";

		try (Connection conn = DataBaseCon.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Employee emp = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getDouble("salary"));
				list.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public void updateEmployee(int id, String newEmail, double newSalary) {
		String sql = "UPDATE employees SET email = ?, salary = ? WHERE id = ?";

		try (Connection conn = DataBaseCon.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, newEmail);
			stmt.setDouble(2, newSalary);
			stmt.setInt(3, id);

			stmt.executeUpdate();
			System.out.println("Employee updated.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee(int id) {
		String sql = "DELETE FROM employees WHERE id = ?";

		try (Connection conn = DataBaseCon.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("Employee deleted.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

