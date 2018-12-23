package  GameManagement;
import java.io.File;
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
						"( modename character varying(20)," +
						"  level integer," + 
						"  solutionnumber integer," + 
						"  solution character varying[]," +
						"  UNIQUE(modename, level, solutionnumber)," +
						"  PRIMARY KEY (solution) )";
			ps = c.prepareStatement(creationCode);
			ps.execute();

		}catch (SQLException e) {
				e.printStackTrace();
		}
		init();
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
			String deletionCode = "DROP TABLE  IF EXISTS  solutions";
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

	public void init(){
		if(getSolution("ChallangeMode", 4,1).size() == 0 )
		{
			String [] level4 = {"n1", "n4", "n5", "n12"};
			String [] level5 = {"n1", "n4", "n5", "n12", "n3"};
			String [] level6 = {"n1", "n4", "n5", "n12", "n3", "n7"};
			String [] level7 = {"n1", "n4", "n5", "n12", "n3", "n7", "n6"};
			String [] level8 = {"n1", "n4", "n5", "n12", "n3", "n7", "n6", "n8"};
			String [] level9 = {"n1", "n4", "n5", "n12", "n3", "n7", "n6", "n8", "n11"};
			String [] level10 = {"n1", "n4", "n5", "n12", "n3", "n7", "n6", "n8", "n11", "n10"};
			String [] level11 = {"n1", "n4", "n5", "n12", "n3", "n7", "n6", "n8", "n11", "n10", "n2"};

			addSolution("ChallangeMode", 4, 1,level4 );
			addSolution("ChallangeMode", 5, 1,level5 );
			addSolution("ChallangeMode", 6, 1,level6 );
			addSolution("ChallangeMode", 7, 1,level7 );
			addSolution("ChallangeMode", 8, 1,level8 );
			addSolution("ChallangeMode", 9, 1,level9 );
			addSolution("ChallangeMode", 10, 1,level10 );
			addSolution("ChallangeMode", 11, 1,level11 );

			String [] level4_2 = {"n1", "n9", "n3", "n11"};
			String [] level5_2 = {"n1", "n9", "n3", "n11", "n5"};
			String [] level6_2 = {"n1", "n9", "n3", "n11", "n5", "n12"};
			String [] level7_2 = {"n1", "n9", "n3", "n11", "n5", "n12", "n8"};
			String [] level8_2 = {"n1", "n9", "n3", "n11", "n5", "n12", "n8", "n6"};
			String [] level9_2 = {"n1", "n9", "n3", "n11", "n5", "n12", "n8", "n6", "n10"};
			String [] level10_2 = {"n1", "n9", "n3", "n11", "n5", "n12", "n8", "n6", "n10", "n4"};
			String [] level11_2 = {"n1", "n9", "n3", "n11", "n5", "n12", "n8", "n6", "n10", "n4", "n7"};

			addSolution("ChallangeMode", 4, 2,level4_2 );
			addSolution("ChallangeMode", 5, 2,level5_2 );
			addSolution("ChallangeMode", 6, 2,level6_2 );
			addSolution("ChallangeMode", 7, 2,level7_2 );
			addSolution("ChallangeMode", 8, 2,level8_2 );
			addSolution("ChallangeMode", 9, 2,level9_2 );
			addSolution("ChallangeMode", 10, 2,level10_2 );
			addSolution("ChallangeMode", 11, 2,level11_2 );


			String [] level4_3 = {"n1", "n9", "n12", "n6"};
			String [] level5_3 = {"n1", "n9", "n12", "n6", "n11"};
			String [] level6_3 = {"n1", "n9", "n12", "n6", "n11", "n2"};
			String [] level7_3 = {"n1", "n9", "n12", "n6", "n11", "n2", "n4"};
			String [] level8_3 = {"n1", "n9", "n12", "n6", "n11", "n2", "n4", "n3"};
			String [] level9_3 = {"n1", "n9", "n12", "n6", "n11", "n2", "n4", "n3", "n5"};
			String [] level10_3 = {"n1", "n9", "n12", "n6", "n11", "n2", "n4", "n3", "n5", "n7"};
			String [] level11_3 = {"n1", "n9", "n12", "n6", "n11", "n2", "n4", "n3", "n5", "n7", "n10"};

			addSolution("ChallangeMode", 4, 3,level4_3 );
			addSolution("ChallangeMode", 5, 3,level5_3 );
			addSolution("ChallangeMode", 6, 3,level6_3 );
			addSolution("ChallangeMode", 7, 3,level7_3 );
			addSolution("ChallangeMode", 8, 3,level8_3 );
			addSolution("ChallangeMode", 9, 3,level9_3 );
			addSolution("ChallangeMode", 10, 3,level10_3 );
			addSolution("ChallangeMode", 11, 3,level11_3 );

			String [] level3_4 = {"n1", "n9" , "n7"};
			String [] level4_4 = {"n1", "n9" , "n7", "n6"};
			String [] level5_4 = {"n1", "n9" , "n7", "n6", "n2"};
			String [] level6_4 = {"n1", "n9" , "n7", "n6", "n2", "n5"};
			String [] level7_4 = {"n1", "n9" , "n7", "n6", "n2", "n5", "n12"};
			String [] level8_4 = {"n1", "n9" , "n7", "n6", "n2", "n5", "n12", "n4"};
			addSolution("NormalMode", 3, 1,level3_4 );
			addSolution("NormalMode", 4, 1,level4_4 );
			addSolution("NormalMode", 5, 1,level5_4 );
			addSolution("NormalMode", 6, 1,level6_4 );
			addSolution("NormalMode", 7, 1,level7_4 );
			addSolution("NormalMode", 8, 1,level8_4 );

			String [] dyn1 = {"n6", "d1", "d2"};
			String [] dyn2 = {"n9", "n7", "d1", "d2", "d3"};
			String [] dyn3 = { "d1", "d2", "d4", "d5", "n4", "n6", "n9" };
		    addSolution("DynamicMode", 1, 1, dyn1 );
		    addSolution("DynamicMode", 2, 1, dyn2 );
			addSolution("DynamicMode", 3,1, dyn3 );
		}
	}
	
}
