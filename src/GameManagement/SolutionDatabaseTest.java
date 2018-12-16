import java.util.Arrays;

public class SolutionDatabaseTest {

	public static void main(String[] args) {
		SolutionDatabase sd = new SolutionDatabase();
		int[][] dir = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		//sd.saveBlock('k',dir );
		for (int i = 0; i < 4; i++)
			for (int a = 0; a < 4 ; a++)
		System.out.println(sd.getDirections('k')[i][a]);

	}

}
