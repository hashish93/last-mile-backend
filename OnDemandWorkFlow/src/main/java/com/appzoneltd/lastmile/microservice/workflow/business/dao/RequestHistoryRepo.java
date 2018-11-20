package com.appzoneltd.lastmile.microservice.workflow.business.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public class RequestHistoryRepo {

	private Connection connection = null;

	public RequestHistoryRepo() {

		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");



		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/lastmile?tcpKeepAlive=true", "postgres",
					"root");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}
	}
	public Long getPackagePickupId(Long packageId) {
		Statement st = null;
		ResultSet rs = null;
		Long xxx = 0L;
		try 
		{
			st = connection.createStatement();
			rs = st.executeQuery("SELECT DISTINCT request_id FROM lastmile_request.request_history WHERE  request_type='PICKUP' AND package_id="+packageId);

			if (rs.next()) {
				xxx = rs.getLong(1);

			}
			
		}
		catch (SQLException se) {
			System.err.println(se.getMessage());

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return xxx;
	}
}
