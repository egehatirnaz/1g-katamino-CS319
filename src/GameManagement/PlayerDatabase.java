package GameManagement;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class PlayerDatabase {
	
	//VARIABLES
	private String password;
	private String rvr = "\'";
	private Connection c;
	private PreparedStatement ps;
	private ResultSet rs;
		
		
	//CONSTRUCTOR
	public PlayerDatabase(String password){
		this.password = password;
			
		createConnection();
		try {
			String creationCode = "CREATE TABLE IF NOT EXISTS players" + 
						"( nickname character varying(15)," + 
						"  time integer," + 
						"  PRIMARY KEY (nickname) )";
			ps = c.prepareStatement(creationCode);
			ps.execute();
				
		}catch (SQLException e) {
				e.printStackTrace();
		}

		init();
	}
	
	//HELPER
	//DATABASE METHODS
	public void createConnection()
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
	
	public void closeDatabase()
	{
		try {
			ps.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteDatabase()
	{	
		try {
			String deletionCode = "DROP TABLE  IF EXISTS  players";
			ps = c.prepareStatement(deletionCode);
			ps.execute();		

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void clearDatabase()
	{	
		try {
			String clearCode = "DELETE FROM players";
			ps = c.prepareStatement(clearCode);
			ps.execute();		

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void addPlayer (String nickName, int time)
	{
		if( controlNickName(nickName)) {
		try {
			String insertionCode = "INSERT INTO players (nickname, time)"
					+ " VALUES (" + rvr + nickName.toLowerCase() + rvr + ","  + rvr + String.valueOf(time) + rvr + ")";
			
			ps = c.prepareStatement(insertionCode);
			ps.execute();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("There is a player with this name.");
	}
	
	public void updatePlayerTime(String nickName , int time)
	{
		String updateCode = "UPDATE players SET time = " + rvr + String.valueOf(time) + rvr + " WHERE nickname = '" + 
							nickName.toLowerCase() + "';";
		
		if(!controlNickName(nickName)) {
		try {
			ps = c.prepareStatement(updateCode);
			ps.execute();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Return false if there is a player with this nickname.
	public boolean controlNickName(String nickName)
	{
		for(int a = 0; a < getNickNames().size(); a++)
			if (getNickNames().get(a).equals(nickName.toLowerCase()))
				return false;
		return true;
	}
	
	public ArrayList<String> getNickNames()
	{
		ArrayList<String> nickNames = new ArrayList<String>();
		try {
			String nicknameCode = "SELECT nickname FROM players";
			ps = c.prepareStatement(nicknameCode);
			rs = ps.executeQuery();		
		while(rs.next())
			nickNames.add(rs.getString("nickname"));		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nickNames;	
	}
		
	public ArrayList<Player> getSortedPlayers()
	{
		String sortedPlayersCode = "SELECT nickname, time FROM players ORDER BY time";
		ArrayList<Player> sortedPlayers = new ArrayList<Player>();
		ArrayList<String> nicknames = new ArrayList<String>();
		ArrayList<Integer> times = new ArrayList<Integer>();
		
		try {
			ps = c.prepareStatement(sortedPlayersCode);
			rs = ps.executeQuery();
			while (rs.next())
			{
				nicknames.add(rs.getString("nickname"));
				times.add(rs.getInt("time"));
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		for(int i = 0; i < nicknames.size(); i++)
		{		
			String name = nicknames.get(i);
			int time = times.get(i);
			Player newOne = new Player(name, time);
			sortedPlayers.add(newOne);
		}
		return sortedPlayers;
	}
	
	public String getLastNickname()
	{
		ArrayList<String> nickNames = getNickNames();
		return nickNames.get(nickNames.size()-1);
	}
	
	public int getTime(String nickName)
	{
		int time = 0;
		if( !controlNickName(nickName) ) {
		try {
			String timeCode = "SELECT time FROM players WHERE nickname = '" + nickName.toLowerCase() + "';";
			ps = c.prepareStatement(timeCode);
			rs = ps.executeQuery();		
		while(rs.next())
			time = rs.getInt("time");
		} catch (SQLException e) {
			e.printStackTrace();
			}
		}
		
		return time;	
	}

	public void init()
	{
		if(getNickNames().size() == 0 )
		{
			addPlayer("yusuf", 9050);
			addPlayer("mert", 9005);
			addPlayer("burak", 9075);
			addPlayer("fırat", 9178);
			addPlayer("ege", 9175);
			addPlayer("burak", 9195);
		}
	}

}
