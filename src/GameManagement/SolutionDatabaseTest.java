package GameManagement;
import java.util.ArrayList;
import java.util.Arrays;

public class SolutionDatabaseTest {

    public static void main( String args[]) {
        SolutionDatabase sd = new SolutionDatabase("yusuf123");
        String [] level4 = {"n1", "n4", "n5", "n12"};
        String [] level5 = {"n1", "n4", "n5", "n12", "n3"};
        String [] level6 = {"n1", "n4", "n5", "n12", "n3", "n7"};
        String [] level7 = {"n1", "n4", "n5", "n12", "n3", "n7", "n6"};
        String [] level8 = {"n1", "n4", "n5", "n12", "n3", "n7", "n6", "n8"};

        sd.addSolution("NormalMode", 4, 1,level4 );
        sd.addSolution("NormalMode", 5, 1,level5 );
        sd.addSolution("NormalMode", 6, 1,level6 );
        sd.addSolution("NormalMode", 7, 1,level7 );
        sd.addSolution("NormalMode", 8, 1,level8 );
    }

}
