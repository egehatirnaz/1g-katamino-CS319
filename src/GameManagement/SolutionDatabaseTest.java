package GameManagement;
import java.util.Arrays;

public class SolutionDatabaseTest {

	public static void main(String[] args) {
		SolutionDatabase database = new SolutionDatabase("yusuf123");
		Direction[] n3 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n3[i] = new Direction();
		}

		int n1Directions1[] = {0,1,0,0};
		int n1Directions2[] = {1,0,0,0};
		int n1Directions3[] = {2,0,0,0};
		int n1Directions4[] = {2,1,0,0};
		n3[0].setDistance( n1Directions1);
		n3[1].setDistance( n1Directions2);
		n3[2].setDistance( n1Directions3);
		n3[3].setDistance( n1Directions4);
		database.saveBlock( "n3", n3 );

		Direction[] n4 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n4[i] = new Direction();
		}

		int n4Directions1[] = {1,0,0,0};
		int n4Directions2[] = {1,0,0,1};
		int n4Directions3[] = {2,0,0,1};
		int n4Directions4[] = {3,0,0,1};
		n4[0].setDistance( n4Directions1);
		n4[1].setDistance( n4Directions2);
		n4[2].setDistance( n4Directions3);
		n4[3].setDistance( n4Directions4);
		database.saveBlock( "n4", n4 );


		Direction[] n5 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n5[i] = new Direction();
		}
		int n5Directions1[] = {1,0,0,0};
		int n5Directions2[] = {1,1,0,0};
		int n5Directions3[] = {1,2,0,0};
		int n5Directions4[] = {2,2,0,0};
		n5[0].setDistance( n5Directions1 );
		n5[1].setDistance( n5Directions2 );
		n5[2].setDistance( n5Directions3 );
		n5[3].setDistance( n5Directions4 );
		database.saveBlock( "n5", n5 );

		Direction[] n6 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n6[i] = new Direction();
		}
		int n6Directions1[] = {1,0,0,0};
		int n6Directions2[] = {1,1,0,0};
		int n6Directions3[] = {0,1,0,0};
		int n6Directions4[] = {0,1,1,0};
		n6[0].setDistance( n6Directions1 );
		n6[1].setDistance( n6Directions2 );
		n6[2].setDistance( n6Directions3 );
		n6[3].setDistance( n6Directions4 );
		database.saveBlock( "n6", n6 );


		Direction[] n7 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n7[i] = new Direction();
		}
		int n7Directions1[] = {1,0,0,0};
		int n7Directions2[] = {2,0,0,0};
		int n7Directions3[] = {1,1,0,0};
		int n7Directions4[] = {1,2,0,0};
		n7[0].setDistance( n7Directions1 );
		n7[1].setDistance( n7Directions2 );
		n7[2].setDistance( n7Directions3 );
		n7[3].setDistance( n7Directions4 );
		database.saveBlock( "n7", n7 );

		Direction[] n8 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n8[i] = new Direction();
		}
		int n8Directions1[] = {1,0,0,0};
		int n8Directions2[] = {1,0,0,1};
		int n8Directions3[] = {1,1,0,0};
		int n8Directions4[] = {2,0,1,0};
		n8[0].setDistance( n8Directions1 );
		n8[1].setDistance( n8Directions2 );
		n8[2].setDistance( n8Directions3 );
		n8[3].setDistance( n8Directions4 );
		database.saveBlock( "n8", n8 );

		Direction[] n9 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n9[i] = new Direction();
		}
		int n9Directions1[] = {1,0,0,0};
		int n9Directions2[] = {1,0,0,1};
		int n9Directions3[] = {2,0,0,0};
		int n9Directions4[] = {3,0,0,0};
		n9[0].setDistance( n9Directions1 );
		n9[1].setDistance( n9Directions2 );
		n9[2].setDistance( n9Directions3 );
		n9[3].setDistance( n9Directions4 );
		database.saveBlock( "n9", n9 );

		Direction[] n10 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n10[i] = new Direction();
		}
		int n10Directions1[] = {1,0,0,0};
		int n10Directions2[] = {2,0,0,0};
		int n10Directions3[] = {3,0,0,0};
		int n10Directions4[] = {4,0,0,0};
		n10[0].setDistance( n10Directions1 );
		n10[1].setDistance( n10Directions2 );
		n10[2].setDistance( n10Directions3 );
		n10[3].setDistance( n10Directions4 );
		database.saveBlock( "n10", n10 );



		Direction[] n11 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n11[i] = new Direction();
		}
		int n11Directions1[] = {1,0,0,1};
		int n11Directions2[] = {1,0,0,0};
		int n11Directions3[] = {1,1,0,0};
		int n11Directions4[] = {2,1,0,0};
		n11[0].setDistance( n11Directions1 );
		n11[1].setDistance( n11Directions2 );
		n11[2].setDistance( n11Directions3 );
		n11[3].setDistance( n11Directions4 );
		database.saveBlock( "n11", n11 );


		Direction[] n12 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n12[i] = new Direction();
		}
		int n12Directions1[] = {1,0,0,0};
		int n12Directions2[] = {2,0,0,0};
		int n12Directions3[] = {2,1,0,0};
		int n12Directions4[] = {2,2,0,0};
		n12[0].setDistance( n12Directions1 );
		n12[1].setDistance( n12Directions2 );
		n12[2].setDistance( n12Directions3 );
		n12[3].setDistance( n12Directions4 );
		database.saveBlock( "n12", n12 );


		//database.updateBlockInformation( "n1" , n1);
/*		int right = 0;
		int down = 1;
		int left = 0;
		int up = 0;
		int[] directions = {right,down,left,up};
		for (int x:directions
			 ) {

			System.out.println( x );
		}
		n1[0].setDistance(directions);
		n1[0].setDown(2);
		n1[1].setDistance( n1[0].getDistance() );
		n1[0].setDown(3);
		n1[2].setDistance( n1[0].getDistance() );
		n1[0].setDown(0);
		n1[0].setRight(1);
		n1[3].setDistance( n1[0].getDistance() );
		database.saveBlock( "n1", n1 );
 		/*int[] a1 = {1,0,0,0};
		int[] a2 = {5,6,7,8};
		int[] a3 = {9,10,11,12};
		int[] a4 = {13,14,15,16};
		Direction d1 = new Direction();
		d1.setDistance( a1 );	
		Direction d2 = new Direction();
		d2.setDistance( a2 );
		Direction d3 = new Direction();
		d3.setDistance( a3 );
		Direction d4 = new Direction();
		d4.setDistance( a4 );
		Direction[] dir = {d1,d2,d3,d4};
		Direction[] dir2 = {d1,d2,d3, null};
		Direction[] dir3 = {d1,d2, null,null};
		sd.saveBlock("ali",dir);
		sd.saveBlock("veli",dir2);
		sd.saveBlock("mert",dir3);
		
		for (int i = 0; i < 4; i++)
			for (int a = 0; a < 4; a++)
		System.out.println(sd.getDirections("veli").get(i).getDistance()[a]);
		//sd.clearDatabase();
		sd.updateBlockInformation("mert", dir2);
		sd.deleteBlock("ali");
		//System.out.println(sd.getBlockNames());
		database.deleteBlock("veli");
		database.deleteBlock("mert");*/

	}

}
