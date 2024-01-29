package com.nikil.RestDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

	Connection connection = null;

	public DataRepository() {
		// System.out.println("data repo const. is called");

		String url = "jdbc:mysql://localhost:3306/data";
		String username = "root";
		String password = "student123";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null) {
				System.out.println("connection made");
			}
		} catch (Exception e) {
			System.out.println("No connection is made");
		}
	}

	public List<Data> getList() {
		// System.out.println("data repo getlist is called");
		List<Data> list = new ArrayList<Data>();
		try {
			String searchForAllquery = "SELECT * FROM data_table ;";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(searchForAllquery);
			while (resultSet.next()) {
				Data data = new Data();
				data.setId(resultSet.getInt(1));
				data.setName(resultSet.getString(2));
				System.out.println(data);
				list.add(data);
			}
		} catch (Exception e) {
			System.out.println("Query Failed");
		}

		return list;
	}

	public Data getData(int id) {
		try {
			String searchForAllquery = "SELECT * FROM data_table where id =" + id + " ;";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(searchForAllquery);
			if (resultSet.next()) {
				Data data = new Data();
				data.setId(resultSet.getInt(1));
				data.setName(resultSet.getString(2));
				return data;
			}
			return null;
		} catch (Exception e) {
			System.out.println("Query Failed");
		}
		return null;
	}

	public void insertData(Data d) {
		try {
			String insert = "INSERT INTO `data_table`(`id`, `names`) VALUES (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, d.getId());
			statement.setString(2, d.getName());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Insertion Failed");
		}
	}

	public void UpdateData(Data d) {
		try {
			String update = "UPDATE `data_table` SET `names`=? WHERE id=? ;";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(2, d.getId());
			statement.setString(1, d.getName());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Updation Failed");
		}
	}

	public void DeleteData(int id) {
		try {
			String delete = "Delete from `data_table` WHERE id=? ;";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Updation Failed");
		}
	}
}
