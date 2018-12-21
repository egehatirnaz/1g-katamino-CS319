import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class SolutionDatabase {

	//VARIABLES
	private String password;
	String rvr = "\'";
	Connection c;
	PreparedStatement ps;
	ResultSet rs;
		
		
	//CONSTRUCTOR
	public SolutionDatabase(String password){
		this.password = password;
			
		createConnection();
		try {
			String creationCode = "CREATE TABLE IF NOT EXISTS solutions" + 
						"( modename character varying(10)," + 
						"  level integer," + 
						"  solutionnumber integer," + 
						"  solution character varying[]," +
						"  UNIQUE(level, solutionnumber)," +
						"  PRIMARY KEY (solution) )";
			ps = c.prepareStatement(creationCode);
			ps.execute();		
		
			/*if(getBlockNames().size() == 0 )
			{
			String copyCode = " COPY blocks " + 
					"FROM 'D:\\Users\\Mert\\eclipse-workspace\\Katamino\\blocksDatabase' DELIMITER ',' CSV HEADER;";
			ps = c.prepareStatement(copyCode);
			ps.execute();				
			} */
				
		}catch (SQLException e) {
				e.printStackTrace();
		}

	}
	
	//HELPER 
	String arrayToString(String[] array)
	{
		if( array != null) {
		String str = Arrays.toString(array).replaceAll("\\s+","");
		str = str.replaceAll("\\[", "{");
		str = str.replaceAll("]", "}");
		return str;
		}
		else
			return "{}";
	}
	
	ArrayList<String> stringToArrayList(String str)
	{
		int fComma=0;
		int sComma=0;
		ArrayList<String> temp = new ArrayList<String>();
		if (!str.equals("")) {
		for(int a = 0; a < str.length(); a++)
		{			
			if(str.charAt(a) == ',')
			{
				sComma = a;
				temp.add( str.substring(fComma+1,sComma) );
				fComma = sComma;
			}
		}
		temp.add( str.substring( fComma+1,str.length()-1) );
		}
		return temp;
	}
	
	//DATABASE METHODS
	void createConnection()
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/",
			           "postgres", password);
			String controlDatabaseCode = "SELECT COUNT(*) FROM pg_catalog.pg_database WHERE datname = 'Katamino'";
			ps = c.prepareStatement(controlDatabaseCode);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(1) == 0)
				{
					String createDatabaseCode = "CREATE DATABASE \"Katamino\"";
					ps = c.prepareStatement(createDatabaseCode);
					rs = ps.executeQuery();
				}
			}
			closeDatabase();
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Katamino",
			           "postgres", password);
		} catch (SQLException e) {
			}
	}
	
	void closeDatabase()
	{
		try {
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void deleteDatabase()
	{	
		try {
			String deletionCode = "DROP TABLE [ IF EXISTS ] solutions";
			ps = c.prepareStatement(deletionCode);
			ps.execute();		

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	void clearDatabase()
	{	
		try {
			String clearCode = "DELETE FROM solutions";
			ps = c.prepareStatement(clearCode);
			ps.execute();		

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	void addSolution (String modeName, int level, int solutionNumber ,String[] solution)
	{
		try {
			String insertionCode = "INSERT INTO solutions (modename, level, solutionnumber, solution)"
					+ " VALUES (" + rvr + modeName + rvr + "," + rvr + String.valueOf(level) + rvr + "," + rvr + 
					String.valueOf(solutionNumber) + rvr + "," + rvr + arrayToString(solution) + rvr + ")";
			
			ps = c.prepareStatement(insertionCode);
			ps.execute();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	ArrayList<String> getSolution(String mode, int level, int solutionNumber)
	{
		String temp = "";
		try {
			String solutionCode = "SELECT solution FROM solutions WHERE modename = '" + mode + "' AND level = " + level + 
					" AND solutionnumber = " + solutionNumber;
			System.out.println(solutionCode);
			ps = c.prepareStatement(solutionCode);
			rs = ps.executeQuery();		
		if(temp.equals("")) {
		while(rs.next())
				temp = (rs.getString("solution"));
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stringToArrayList(temp);	
	}
	
	void deleteSolution(String modeName, int level, int solutionNumber)
	{
		try {
			String deletionCode = "DELETE FROM solutions WHERE modename = '" + modeName + "' AND level = " + String.valueOf(level) +
									" AND solutionnumber = " + String.valueOf(solutionNumber) + ";";
			ps = c.prepareStatement(deletionCode);
			ps.execute();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	int getSolutionNumber(String modeName, int level)
	{
		int number = 0;
		try {
			String solutionNumberCode = "SELECT COUNT(*) FROM solutions WHERE modename = '" + modeName +
					"' , level = '" + level;
			ps = c.prepareStatement(solutionNumberCode);
			rs = ps.executeQuery();
			while(rs.next())
				number = rs.getInt("count");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return number;
	}
	
}
