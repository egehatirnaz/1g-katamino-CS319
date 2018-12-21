import java.util.ArrayList;

public class SolutionDatabaseTest {

	public static void main(String[] args) {
		SolutionDatabase sd = new SolutionDatabase("13111997");
		String [] dyn1 = {"n6", "d1", "d2"};
		String [] dyn2= {"n9", "n7", "d1", "d2", "d3"};

		sd.addSolution("DynamicMode", 1, 1, dyn1 );
		sd.addSolution("DynamicMode", 2, 1, dyn2 );
		sd.closeDatabase();
	}

}
