package persistence;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DBHandler {

	private static DBHandler dbHandler;
	private Connection connection;
	private String url = "jdbc:derby:recordsdb;create=true;user=g15;password=pass";
	private static final String CREATE = "CREATE TABLE stats (game_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), clicks INTEGER NOT NULL, seconds INTEGER NOT null, date_of_game TIMESTAMP NOT NULL, score INTEGER NOT NULL)";
	private static final String INSERT = "INSERT INTO stats (clicks, seconds, date_of_game, score) values (?, ?, ?, ?)";
	private static final String SELECT_ALL = "SELECT * FROM stats";

	private DBHandler() {
		try {
			connection = DriverManager.getConnection(url);
			createTable();

		} catch (SQLException e) {
			if (e.getSQLState().equals("XJ040")) {

				JOptionPane.showMessageDialog(null,
						"There's been an error with the database connection... If you have another game running, please close the window",
						"Connection error", JOptionPane.ERROR_MESSAGE, null);
				System.exit(1);

			} else {

				JOptionPane.showMessageDialog(null, "There's been an error with the database connection",
						"Database error", JOptionPane.ERROR_MESSAGE, null);

				e.printStackTrace();
			}
		}
	}

	public static DBHandler getHandler() {
		if (dbHandler == null) {
			dbHandler = new DBHandler();
		}
		return dbHandler;
	}

	private void createTable() throws SQLException {
		PreparedStatement stm = connection.prepareStatement(CREATE);
		try {
			stm.executeUpdate();

		} catch (SQLException e) {
			// avoid throwing exception if table exists
			if (!e.getSQLState().equals("X0Y32")) {
				throw e;
			}
		}
	}

	public int updateTable(int clicks, int seconds) {
		PreparedStatement stm;
		LocalDateTime now = LocalDateTime.now();

		String timeStamp = now.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
		int rowsAffected = 0;
		int score = calculateScore(clicks, seconds);
		try {
			stm = connection.prepareStatement(INSERT);
			stm.setInt(1, clicks);
			stm.setInt(2, seconds);
			stm.setString(3, timeStamp);
			stm.setInt(4, score);

			rowsAffected = stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowsAffected;
	}

	private int calculateScore(int clicks, int seconds) {

		return 100_000 / (clicks * seconds);
	}

	public List<Result> getAllResults() {
		List<Result> results = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				int clicks = resultSet.getInt("clicks");
				int seconds = resultSet.getInt("seconds");
				Timestamp timeStamp = resultSet.getTimestamp("date_of_game");
				results.add(new Result(clicks, seconds, timeStamp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}

}
