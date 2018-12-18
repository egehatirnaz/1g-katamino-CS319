import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

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
			String creationCode = "CREATE TABLE IF NOT EXISTS blocks" + 
					"( blockname character varying(10)," + 
					"  firstvertex integer[]," + 
					"  secondvertex integer[]," + 
					"  thirdvertex integer[]," + 
					"  fourthvertex integer[]," + 
					"  CONSTRAINT persons_pkey PRIMARY KEY (blockname) )";
			ps = c.prepareStatement(creationCode);
			ps.execute();		
	
			if(getBlockNames().size() == 0 )
			{
			//createConnection();
			String copyCode = " COPY blocks " + 
					"FROM 'D:\\Users\\Mert\\eclipse-workspace\\Katamino\\blocksDatabase' DELIMITER ',' CSV HEADER;";
			ps = c.prepareStatement(copyCode);
			ps.execute();				
			} 
			
		}catch (SQLException e) {
				e.printStackTrace();
		}

	}
	
	//HELPER METHODS
	String directionToString(Direction dir)
	{
		if( dir != null) {
		String str = Arrays.toString(dir.getDistance()).replaceAll("\\s+","");
		str = str.replaceAll("\\[", "{");
		str = str.replaceAll("]", "}");
		return str;
		}
		else
			return "{}";
	}
	
	Direction stringToDirection(String str)
	{
		int fComma=0;
		int sComma=0;
		int arrayIndex=0;
		int[] array = new int[4];
		Direction dir = new Direction();
		if (!str.equals("{}")) {
		for(int a = 0; a < str.length(); a++)
		{			
			if(str.charAt(a) == ',')
			{
				sComma = a;
				array[arrayIndex++] = Integer.parseInt( str.substring(fComma+1,sComma) );
				fComma = sComma;
			}
		}
		array[3] = Integer.parseInt(str.substring(fComma+1,str.length()-1));
		}
		
		dir.setDistance(array);	
		return dir;
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
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Katamino",
				           "postgres", password);
			}
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
		//createConnection();
		
		try {
			String deletionCode = "DROP TABLE [ IF EXISTS ] blocks";
			ps = c.prepareStatement(deletionCode);
			ps.execute();		

			} catch (SQLException e) {
				e.printStackTrace();
			}
		//closeDatabase();
	}
	
	void clearDatabase()
	{
		//createConnection();
		
		try {
			String clearCode = "DELETE FROM blocks";
			ps = c.prepareStatement(clearCode);
			ps.execute();		

			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		//closeDatabase();
	}
	
	void updateBlockInformation(String blockname , Direction[] directions)
	{
		String updateCode = "UPDATE blocks SET firstvertex = " + rvr + directionToString(directions[0]) + rvr +
							", secondvertex = " + rvr + directionToString(directions[1]) + rvr +
							", thirdvertex = " + rvr + directionToString(directions[2]) + rvr +
							", fourthvertex = " + rvr + directionToString(directions[3]) + rvr +
							" WHERE blockname = '" + blockname + "';";
		System.out.println(updateCode);
		if(!controlBlockName(blockname)) {
		//createConnection();	
		try {
			ps = c.prepareStatement(updateCode);
			ps.execute();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		//closeDatabase();
		}
	}
	ArrayList<String> getBlockNames()
	{
		ArrayList<String> savedBlockNames = new ArrayList<String>();
		//createConnection();
		try {
		String blocknameCode = "SELECT blockname FROM blocks";
		ps = c.prepareStatement(blocknameCode);
		rs = ps.executeQuery();		
		while(rs.next())
		savedBlockNames.add(rs.getString("blockname"));		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return savedBlockNames;	
	}
	
	//Return false if there is a block with this name.
	boolean controlBlockName(String blockName)
	{
		for(int a = 0; a < getBlockNames().size(); a++)
			if (getBlockNames().get(a).equals(blockName))
				return false;
		return true;
	}
	
	void saveBlock(String blockName, Direction[] directions)
	{
		directionToString(directions[0]);

		if(controlBlockName(blockName)) {
			//createConnection();
		try {
			String insertionCode = "INSERT INTO blocks (blockname,firstvertex,secondvertex,thirdvertex,fourthvertex)"
					+ " VALUES (" + rvr + blockName + rvr;
			for(int i = 0; i < directions.length; i++)
					insertionCode = insertionCode + "," + rvr + directionToString(directions[i]) + rvr;
			
			for(int i = directions.length; i < 4; i++)
				insertionCode = insertionCode + "," + rvr + "{}" + rvr;
			
			insertionCode = insertionCode + ")";
					
			ps = c.prepareStatement(insertionCode);
			ps.execute();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//closeDatabase();
		}
	}
	
	ArrayList<Direction> getDirections(String blockName){
		ArrayList<Direction> directions = new ArrayList<Direction>();

			if( !controlBlockName(blockName) ) {	
				//createConnection();
			String fv = "SELECT firstvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String sv = "SELECT secondvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String tv = "SELECT thirdvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String fov = "SELECT fourthvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";

			
			try {
				ps = c.prepareStatement(fv);
				ResultSet rs = ps.executeQuery();		
				while(rs.next()) {
					if(!rs.getString("firstvertex").equals(""))
						directions.add( stringToDirection( rs.getString("firstvertex") ) );
				}
				
				ps = c.prepareStatement(sv);
				rs = ps.executeQuery();		
				while(rs.next()) {
					if(!rs.getString("secondvertex").equals(""))
						directions.add( stringToDirection( rs.getString("secondvertex") ) );
				}
				
				ps = c.prepareStatement(tv);
				rs = ps.executeQuery();		
				while(rs.next())
				{
					if(!rs.getString("thirdvertex").equals(""))
						directions.add( stringToDirection( rs.getString("thirdvertex") ) );
				}
				
				ps = c.prepareStatement(fov);
				rs = ps.executeQuery();		
				while(rs.next())
				{
					if(!rs.getString("fourthvertex").equals(""))
					directions.add( stringToDirection( rs.getString("fourthvertex") ) );			
				}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
					
        return directions;
	}
	
	void deleteBlock(String blockName)
	{
		if (!controlBlockName(blockName) ) {
			try {
				String blocknameCode = "DELETE FROM blocks WHERE blockname = '" + blockName + "';";
				ps = c.prepareStatement(blocknameCode);
				ps.execute();		
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
  
}

