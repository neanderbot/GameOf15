package persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class DBTest {
	String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	String url = "jdbc:derby:TESTDB;create=true";
	String user = "g15";
	String pass = "pass";
	String create = "CREATE TABLE game (game_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), player VARCHAR(20) NOT NULL, clicks integer NOT NULL, seconds integer NOT null, date_of_game timestamp NOT null)";
	String insert = "INSERT INTO game (player, clicks, seconds, date_of_game) values (?, ?, ?, ?)";
	public static void main(String[] args) throws SQLException {
		new DBTest().go();
		DBTest dbt = new DBTest();
		Properties properties = new Properties();
		properties.put("user", dbt.user);
		properties.put("password", dbt.pass);
		Connection conn = DriverManager.getConnection(dbt.url, properties);
		conn.close();
		
		try
        {
            // the shutdown=true attribute shuts down Derby
            DriverManager.getConnection("jdbc:derby:;shutdown=true");

            // To shut down a specific database only, but keep the
            // engine running (for example for connecting to other
            // databases), specify a database in the connection URL:
            //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
        }
        catch (SQLException se)
        {
            if (( (se.getErrorCode() == 50000)
                    && ("XJ015".equals(se.getSQLState()) ))) {
                // we got the expected exception
                System.out.println("Derby shut down normally");
                // Note that for single database shutdown, the expected
                // SQL state is "08006", and the error code is 45000.
            } else {
                // if the error code or SQLState is different, we have
                // an unexpected exception (shutdown failed)
                System.err.println("Derby did not shut down normally");
            }
        }
		
	}
	
	public void go() throws SQLException {
		Connection conn = DriverManager.getConnection(this.url, this.user, this.pass);
		Statement stm = conn.createStatement();
		DatabaseMetaData md = conn.getMetaData();
		ResultSet rs = md.getTables(null, null, null, null);
		Set<String> names = new HashSet<>();
		while(rs.next()) {
			
			names.add(rs.getString("TABLE_NAME").toLowerCase());
		}
		boolean tableExists = false;
		for(String name: names) {
			if(name.equals("game")) {
				tableExists = true;
				break;
			}
		}
		if (tableExists == false) {
			stm.execute(this.create);
		}
		
//		DriverManager.getConnection("jdbc:derby:;shutdown=true");
	}

}
