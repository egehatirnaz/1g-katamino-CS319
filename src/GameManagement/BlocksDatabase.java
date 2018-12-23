package GameManagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.io.*;

public class BlocksDatabase {
	
	//VARIABLES
	private String password;
	String rvr = "\'";
	Connection c;
	PreparedStatement ps;
	ResultSet rs;
		
	
	//CONSTRUCTOR
	public BlocksDatabase(String password){
		this.password = password;
		
		createConnection();
		try {
			String creationCode = "CREATE TABLE IF NOT EXISTS blocks" + 
					"( blockname character varying(10)," + 
					"  firstvertex integer[]," + 
					"  secondvertex integer[]," + 
					"  thirdvertex integer[]," + 
					"  fourthvertex integer[]," +
					"  fifthvertex integer[]," +
					"  sixthvertex integer[]," +
					"  color character varying(10)," +
					"  PRIMARY KEY (blockname) )";
			ps = c.prepareStatement(creationCode);
			ps.execute();
			
		}catch (SQLException e) {
				e.printStackTrace();
		}

		init();

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
	
	Color stringToColor(String color)
	{
		Color purple = new Color(102,0,153);
		switch (color) {
		case "blue": return Color.BLUE;
		case "red": return Color.RED;
		case "pink": return Color.PINK;
		case "yellow": return Color.YELLOW;
		case "green": return Color.GREEN;
		case "purple": return purple;
		case "gray": return Color.GRAY;
		case "orange": return Color.ORANGE;
		case "magenta": return Color.MAGENTA;
		case "cyan": return Color.CYAN;
		case "dark_gray": return Color.DARK_GRAY;
		case "light_gray": return Color.LIGHT_GRAY;
		default:
			return null;
		}
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
			String deletionCode = "DROP TABLE [ IF EXISTS ] blocks";
			ps = c.prepareStatement(deletionCode);
			ps.execute();		

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	void clearDatabase()
	{	
		try {
			String clearCode = "DELETE FROM blocks";
			ps = c.prepareStatement(clearCode);
			ps.execute();		

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	void updateBlockInformation(String blockname , Direction[] directions)
	{
		String updateCode = "UPDATE blocks SET firstvertex = " + rvr + directionToString(directions[0]) + rvr;
		if(directions.length > 1)
			updateCode = updateCode + ", secondvertex = " + rvr + directionToString(directions[1]) + rvr ;
		if(directions.length > 2)
			updateCode = updateCode + ", thirdvertex = " + rvr + directionToString(directions[2]) + rvr ;
		if(directions.length > 3)
			updateCode = updateCode + ", fourthvertex = " + rvr + directionToString(directions[3]) + rvr ;
		if(directions.length > 4)
			updateCode = updateCode + ", fifthvertex = " + rvr + directionToString(directions[4]) + rvr ;
		if(directions.length > 5)
			updateCode = updateCode + ", sixthvertex = " + rvr + directionToString(directions[5]) + rvr ;

		updateCode = updateCode + " WHERE blockname = '" + blockname + "';";
		if(!controlBlockName(blockname)) {

		try {
			ps = c.prepareStatement(updateCode);
			ps.execute();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	ArrayList<String> getBlockNames()
	{
		ArrayList<String> savedBlockNames = new ArrayList<String>();
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
	
	void saveBlock(String blockName, Direction[] directions, String color)
	{
		if(controlBlockName(blockName)) {
		try {
			String insertionCode = "INSERT INTO blocks (blockname,firstvertex,secondvertex,thirdvertex,fourthvertex,fifthvertex,sixthvertex,color)"
					+ " VALUES (" + rvr + blockName + rvr;
			for(int i = 0; i < directions.length; i++)
					insertionCode = insertionCode + "," + rvr + directionToString(directions[i]) + rvr;
			
			for(int i = directions.length; i < 6; i++)
				insertionCode = insertionCode + "," + rvr + "{}" + rvr;
			
			insertionCode = insertionCode + ", '" + color + "')";
			ps = c.prepareStatement(insertionCode);
			ps.execute();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	ArrayList<Direction> getDirections(String blockName){
		ArrayList<Direction> directions = new ArrayList<Direction>();

			if( !controlBlockName(blockName) ) {	
			String fv = "SELECT firstvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String sv = "SELECT secondvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String tv = "SELECT thirdvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String fov = "SELECT fourthvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String fiv = "SELECT fifthvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String siv = "SELECT sixthvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";

			
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
				
				ps = c.prepareStatement(fiv);
				rs = ps.executeQuery();		
				while(rs.next())
				{
					if(!rs.getString("fifthvertex").equals(""))
					directions.add( stringToDirection( rs.getString("fifthvertex") ) );			
				}
				
				ps = c.prepareStatement(siv);
				rs = ps.executeQuery();		
				while(rs.next())
				{
					if(!rs.getString("sixthvertex").equals(""))
					directions.add( stringToDirection( rs.getString("sixthvertex") ) );			
				}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
					
        return directions;
	}
	
	Color getColor(String blockName)
	{
		String color = "";
		try {
		String colorCode = "SELECT color FROM blocks WHERE blockname = '" + blockName + "'";
		ps = c.prepareStatement(colorCode);
		rs = ps.executeQuery();		
		while(rs.next())
		color = rs.getString("color");		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stringToColor(color);	
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

	public void init()
	{
		if(getBlockNames().size() == 0 )
		{
			Direction[] n1 = arraySetter();
			int n1Directions1[] = {0,1,0,0};
			int n1Directions2[] = {0,2,0,0};
			int n1Directions3[] = {0,3,0,0};
			int n1Directions4[] = {1,0,0,0};
			n1[0].setDistance( n1Directions1);
			n1[1].setDistance( n1Directions2);
			n1[2].setDistance( n1Directions3);
			n1[3].setDistance( n1Directions4);
			saveBlock( "n1", n1 , "blue");

			Direction[] n2 = arraySetter();
			int n2Directions1[] = {1,0,0,0};
			int n2Directions2[] = {1,1,0,0};
			int n2Directions3[] = {2,1,0,0};
			int n2Directions4[] = {2,2,0,0};
			n2[0].setDistance( n2Directions1);
			n2[1].setDistance( n2Directions2);
			n2[2].setDistance( n2Directions3);
			n2[3].setDistance( n2Directions4);
			saveBlock( "n2", n2 , "red");

			Direction[] n3 = arraySetter();
			int n3Directions1[] = {0,1,0,0};
			int n3Directions2[] = {1,0,0,0};
			int n3Directions3[] = {2,0,0,0};
			int n3Directions4[] = {2,1,0,0};
			n3[0].setDistance( n3Directions1);
			n3[1].setDistance( n3Directions2);
			n3[2].setDistance( n3Directions3);
			n3[3].setDistance( n3Directions4);
			saveBlock( "n3", n3 , "pink");

			Direction[] n4 = arraySetter();
			int n4Directions1[] = {1,0,0,0};
			int n4Directions2[] = {1,1,0,0};
			int n4Directions3[] = {2,1,0,0};
			int n4Directions4[] = {3,1,0,0};
			n4[0].setDistance( n4Directions1);
			n4[1].setDistance( n4Directions2);
			n4[2].setDistance( n4Directions3);
			n4[3].setDistance( n4Directions4);
			saveBlock( "n4", n4 , "yellow");

			Direction[] n5 = arraySetter();
			int n5Directions1[] = {1,0,0,0};
			int n5Directions2[] = {1,1,0,0};
			int n5Directions3[] = {1,2,0,0};
			int n5Directions4[] = {2,2,0,0};
			n5[0].setDistance( n5Directions1 );
			n5[1].setDistance( n5Directions2 );
			n5[2].setDistance( n5Directions3 );
			n5[3].setDistance( n5Directions4 );
			saveBlock( "n5", n5 ,"green");

			Direction[] n6 = arraySetter();
			int n6Directions1[] = {1,0,0,0};
			int n6Directions2[] = {1,1,0,0};
			int n6Directions3[] = {0,1,0,0};
			int n6Directions4[] = {0,1,1,0};
			n6[0].setDistance( n6Directions1 );
			n6[1].setDistance( n6Directions2 );
			n6[2].setDistance( n6Directions3 );
			n6[3].setDistance( n6Directions4 );
			saveBlock( "n6", n6 , "purple");

			Direction[] n7 = arraySetter();
			int n7Directions1[] = {1,0,0,0};
			int n7Directions2[] = {2,0,0,0};
			int n7Directions3[] = {1,1,0,0};
			int n7Directions4[] = {1,2,0,0};
			n7[0].setDistance( n7Directions1 );
			n7[1].setDistance( n7Directions2 );
			n7[2].setDistance( n7Directions3 );
			n7[3].setDistance( n7Directions4 );
			saveBlock( "n7", n7 , "gray");

			Direction[] n8 = arraySetter();
			int n8Directions1[] = {1,1,0,0};
			int n8Directions2[] = {0,1,0,0};
			int n8Directions3[] = {0,2,0,0};
			int n8Directions4[] = {0,0,1,0};
			n8[0].setDistance( n8Directions1 );
			n8[1].setDistance( n8Directions2 );
			n8[2].setDistance( n8Directions3 );
			n8[3].setDistance( n8Directions4 );
			saveBlock( "n8", n8 , "orange");

			Direction[] n9 = arraySetter();
			int n9Directions1[] = {1,0,0,0};
			int n9Directions2[] = {1,1,0,0};
			int n9Directions3[] = {2,0,0,0};
			int n9Directions4[] = {3,0,0,0};
			n9[0].setDistance( n9Directions1 );
			n9[1].setDistance( n9Directions2 );
			n9[2].setDistance( n9Directions3 );
			n9[3].setDistance( n9Directions4 );
			saveBlock( "n9", n9 , "magenta");

			Direction[] n10 = arraySetter();
			int n10Directions1[] = {1,0,0,0};
			int n10Directions2[] = {2,0,0,0};
			int n10Directions3[] = {3,0,0,0};
			int n10Directions4[] = {4,0,0,0};
			n10[0].setDistance( n10Directions1 );
			n10[1].setDistance( n10Directions2 );
			n10[2].setDistance( n10Directions3 );
			n10[3].setDistance( n10Directions4 );
			saveBlock( "n10", n10, "cyan" );

			Direction[] n11 = arraySetter();
			int n11Directions1[] = {0,1,0,0};
			int n11Directions2[] = {0,1,1,0};
			int n11Directions3[] = {0,2,0,0};
			int n11Directions4[] = {1,2,0,0};
			n11[0].setDistance( n11Directions1 );
			n11[1].setDistance( n11Directions2 );
			n11[2].setDistance( n11Directions3 );
			n11[3].setDistance( n11Directions4 );
			saveBlock( "n11", n11, "dark_gray" );

			Direction[] n12 = arraySetter();
			int n12Directions1[] = {1,0,0,0};
			int n12Directions2[] = {2,0,0,0};
			int n12Directions3[] = {2,1,0,0};
			int n12Directions4[] = {2,2,0,0};
			n12[0].setDistance( n12Directions1 );
			n12[1].setDistance( n12Directions2 );
			n12[2].setDistance( n12Directions3 );
			n12[3].setDistance( n12Directions4 );
			saveBlock( "n12", n12, "light_gray");

			Direction[] directions = arraySetter();
			int[] d11 = { 1, 0, 0, 0 };
			int[] d12 = { 1, 1, 0, 0 };
			int[] d13 = { 1, 2, 0, 0 };
			directions[0].setDistance(d11);
			directions[1].setDistance(d12);
			directions[2].setDistance(d13);
			saveBlock( "d1", directions, "red");

			directions = arraySetter();
			int[] d21 = {0,1,0,0};
			int[] d22 = {0,2,0,0};
			directions[0].setDistance(d21);
			directions[1].setDistance(d22);
			saveBlock( "d2", directions, "blue");

			directions = arraySetter();
			int[] d31 = { 0, 1, 0, 0};
			int[] d32 = { 0, 1, 1, 0};
			directions[0].setDistance(d31);
			directions[1].setDistance(d32);
			saveBlock("d3", directions, "green" );

			directions = arraySetter();
			int[] d41 = { 1, 0, 0, 0};
			int[] d42 = { 2, 0, 0, 0};
			int[] d43 = { 3, 0, 0, 0};
			int[] d44 = { 4, 0, 0, 0};
			int[] d45 = { 3, 1, 0, 0};
			int[] d46 = { 4, 1, 0, 0};
			directions[0].setDistance(d41);
			directions[1].setDistance(d42);
			directions[2].setDistance(d43);
			directions[3].setDistance(d44);
			directions[4].setDistance(d45);
			directions[5].setDistance(d46);
			saveBlock("d4", directions, "purple" );

			directions = arraySetter();
			int[] d51 = { 1, 0, 0, 0};
			int[] d52 = { 2, 0, 0, 0};
			int[] d53 = { 3, 0, 0, 0};
			int[] d54 = { 3, 1, 0, 0};
			int[] d55 = { 0, 1, 0, 0};
			int[] d56 = { 0, 2, 0, 0};
			directions[0].setDistance(d51);
			directions[1].setDistance(d52);
			directions[2].setDistance(d53);
			directions[3].setDistance(d54);
			directions[4].setDistance(d55);
			directions[5].setDistance(d56);
			saveBlock("d5", directions, "magenta" );
		}
	}

	public Direction[] arraySetter()
	{
		Direction[] directions = new Direction[6];
		for( int i = 0; i < 6 ; i++)
			directions[ i ] = new Direction();
		return directions;
	}
}
