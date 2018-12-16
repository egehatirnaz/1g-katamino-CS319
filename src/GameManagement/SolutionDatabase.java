import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.io.*;

public class SolutionDatabase {
	String rvr = "\'";
	Connection c;
	PreparedStatement ps;
	ResultSet rs;
	String savedBlockNames = "";
	
	String arrayToString(int[] array)
	{
		String str = Arrays.toString(array).replaceAll("\\s+","");
		str = str.replaceAll("\\[", "{");
		str = str.replaceAll("]", "}");
		return str;
	}
	
	int[] stringToArray(String str)
	{
		int fComma=0;
		int sComma=0;
		int arrayIndex=0;
		int[] array = new int[4];
		for(int a = 0; a < str.length(); a++)
		{			
			if(str.charAt(a) == ',')
			{
				sComma = a;
				array[arrayIndex++] = Integer.parseInt(str.substring(fComma+1,sComma));
				fComma = sComma;
			}
		}
		array[3] = Integer.parseInt(str.substring(fComma+1,str.length()-1));
		return array;
	}
	
	void saveBlock(char blockName, int[][] directions)
	{
		arrayToString(directions[0]);
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Katamino",
			           "postgres", "13111997");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
		String controlCode = "SELECT blockname FROM blocks";
		ps = c.prepareStatement(controlCode);
		rs = ps.executeQuery();		
		while(rs.next())
		savedBlockNames = savedBlockNames + rs.getString("blockname");		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		boolean control = true;
		
		for(int a = 0; a < savedBlockNames.length(); a++)
			if (savedBlockNames.charAt(a) == blockName)
			{
				control=false;
				System.out.println("There is a block with this name.");
				a = savedBlockNames.length();
			}
		
		if(control) {
		try {
			String insertionCode = "INSERT INTO blocks (blockname,firstvertex,secondvertex,thirdvertex,fourtvertex)"
					+ " VALUES (" + rvr + blockName + rvr + "," + rvr + arrayToString(directions[0]) + rvr + "," 
					+ rvr + arrayToString(directions[1]) + rvr + ","
					+ rvr + arrayToString(directions[2]) + rvr + "," + rvr + arrayToString(directions[3]) + rvr + ")";
			ps = c.prepareStatement(insertionCode);
			ps.execute();	
			ps.close();
			c.close();
		} catch (SQLException e) {
		}
		}
		

	}
	
	int[][] getDirections(char blockName){
		int[][] directions = new int[4][0];
		
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Katamino",
				           "postgres", "13111997");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			String fv = "SELECT firstvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String sv = "SELECT secondvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String tv = "SELECT thirdvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";
			String fov = "SELECT fourthvertex FROM blocks WHERE blockname = " + "\'" + blockName + "\'";

			try {
				ps = c.prepareStatement(fv);
				ResultSet rs = ps.executeQuery();		
				while(rs.next())
				directions[0] = stringToArray(rs.getString("firstvertex"));
				
				ps = c.prepareStatement(sv);
				rs = ps.executeQuery();		
				while(rs.next())
				directions[1] = stringToArray(rs.getString("secondvertex"));
				
				ps = c.prepareStatement(tv);
				rs = ps.executeQuery();		
				while(rs.next())
				directions[2] = stringToArray(rs.getString("thirdvertex"));
				
				ps = c.prepareStatement(fov);
				rs = ps.executeQuery();		
				while(rs.next())
				directions[3] = stringToArray(rs.getString("fourthvertex"));
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
        return directions;
	}
}
