package com.appzoneltd.lastmile.microservice.workflow.listener;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TriggerNotifier extends Thread {

	
	private Connection connection;

	public TriggerNotifier(Connection connection) throws SQLException {
		this.connection = connection;
	}

	public void run() {
		//while (true) {
			try {
				Statement stmt = connection.createStatement();
				stmt.execute("NOTIFY urlwork");
				stmt.close();
				Thread.sleep(2000);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		//}
	}

}
