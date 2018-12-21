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
        String [] dyn1 = {"n6", "d1", "d2"};
        String [] dyn2= {"n9", "n7", "d1", "d2", "d3"};
        String [] level9 = {"n1", "n4", "n5", "n12", "n3", "n7", "n6", "n8", "n11"};
        String [] level10 = {"n1", "n4", "n5", "n12", "n3", "n7", "n6", "n8", "n11", "n10"};
        String [] level11 = {"n1", "n4", "n5", "n12", "n3", "n7", "n6", "n8", "n11", "n10", "n2"};

        sd.addSolution("NormalMode", 4, 1,level4 );
        sd.addSolution("NormalMode", 5, 1,level5 );
        sd.addSolution("NormalMode", 6, 1,level6 );
        sd.addSolution("NormalMode", 7, 1,level7 );
        sd.addSolution("NormalMode", 8, 1,level8 );
        sd.addSolution("NormalMode", 9, 1,level9 );
        sd.addSolution("NormalMode", 10, 1,level10 );
        sd.addSolution("NormalMode", 11, 1,level11 );

        String [] level4_2 = {"n1", "n9", "n3", "n11"};
        String [] level5_2 = {"n1", "n9", "n3", "n11", "n5"};
        String [] level6_2 = {"n1", "n9", "n3", "n11", "n5", "n12"};
        String [] level7_2 = {"n1", "n9", "n3", "n11", "n5", "n12", "n8"};
        String [] level8_2 = {"n1", "n9", "n3", "n11", "n5", "n12", "n8", "n6"};
        String [] level9_2 = {"n1", "n9", "n3", "n11", "n5", "n12", "n8", "n6", "n10"};
        String [] level10_2 = {"n1", "n9", "n3", "n11", "n5", "n12", "n8", "n6", "n10", "n4"};
        String [] level11_2 = {"n1", "n9", "n3", "n11", "n5", "n12", "n8", "n6", "n10", "n4", "n7"};

        sd.addSolution("NormalMode", 4, 2,level4_2 );
        sd.addSolution("NormalMode", 5, 2,level5_2 );
        sd.addSolution("NormalMode", 6, 2,level6_2 );
        sd.addSolution("NormalMode", 7, 2,level7_2 );
        sd.addSolution("NormalMode", 8, 2,level8_2 );
        sd.addSolution("NormalMode", 9, 2,level9_2 );
        sd.addSolution("NormalMode", 10, 2,level10_2 );
        sd.addSolution("NormalMode", 11, 2,level11_2 );


        String [] level4_3 = {"n1", "n9", "n12", "n6"};
        String [] level5_3 = {"n1", "n9", "n12", "n6", "n11"};
        String [] level6_3 = {"n1", "n9", "n12", "n6", "n11", "n2"};
        String [] level7_3 = {"n1", "n9", "n12", "n6", "n11", "n2", "n4"};
        String [] level8_3 = {"n1", "n9", "n12", "n6", "n11", "n2", "n4", "n3"};
        String [] level9_3 = {"n1", "n9", "n12", "n6", "n11", "n2", "n4", "n3", "n5"};
        String [] level10_3 = {"n1", "n9", "n12", "n6", "n11", "n2", "n4", "n3", "n5", "n7"};
        String [] level11_3 = {"n1", "n9", "n12", "n6", "n11", "n2", "n4", "n3", "n5", "n7", "n10"};

        sd.addSolution("NormalMode", 4, 3,level4_3 );
        sd.addSolution("NormalMode", 5, 3,level5_3 );
        sd.addSolution("NormalMode", 6, 3,level6_3 );
        sd.addSolution("NormalMode", 7, 3,level7_3 );
        sd.addSolution("NormalMode", 8, 3,level8_3 );
        sd.addSolution("NormalMode", 9, 3,level9_3 );
        sd.addSolution("NormalMode", 10, 3,level10_3 );
        sd.addSolution("NormalMode", 11, 3,level11_3 );

        sd.addSolution("DynamicMode", 1, 1, dyn1 );
        sd.addSolution("DynamicMode", 2, 1, dyn2 );
    }

}
