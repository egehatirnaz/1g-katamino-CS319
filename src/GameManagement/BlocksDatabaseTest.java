package GameManagement;

public class BlocksDatabaseTest {

	public static void main(String[] args) {
		BlocksDatabase database = new BlocksDatabase("yusuf123");

		/*Direction[] n1 = arraySetter();

		int n1Directions1[] = {0,1,0,0};
		int n1Directions2[] = {0,2,0,0};
		int n1Directions3[] = {0,3,0,0};
		int n1Directions4[] = {1,0,0,0};
		n1[0].setDistance( n1Directions1);
		n1[1].setDistance( n1Directions2);
		n1[2].setDistance( n1Directions3);
		n1[3].setDistance( n1Directions4);
		database.saveBlock( "n1", n1 , "blue");

		Direction[] n2 = arraySetter();
		int n2Directions1[] = {1,0,0,0};
		int n2Directions2[] = {1,1,0,0};
		int n2Directions3[] = {2,1,0,0};
		int n2Directions4[] = {2,2,0,0};
		n2[0].setDistance( n2Directions1);
		n2[1].setDistance( n2Directions2);
		n2[2].setDistance( n2Directions3);
		n2[3].setDistance( n2Directions4);
		database.saveBlock( "n2", n2 , "red");

		Direction[] n3 = arraySetter();

		int n3Directions1[] = {0,1,0,0};
		int n3Directions2[] = {1,0,0,0};
		int n3Directions3[] = {2,0,0,0};
		int n3Directions4[] = {2,1,0,0};
		n3[0].setDistance( n3Directions1);
		n3[1].setDistance( n3Directions2);
		n3[2].setDistance( n3Directions3);
		n3[3].setDistance( n3Directions4);
		database.saveBlock( "n3", n3 , "pink");

		Direction[] n4 = arraySetter();

		int n4Directions1[] = {1,0,0,0};
		int n4Directions2[] = {1,1,0,0};
		int n4Directions3[] = {2,1,0,0};
		int n4Directions4[] = {3,1,0,0};
		n4[0].setDistance( n4Directions1);
		n4[1].setDistance( n4Directions2);
		n4[2].setDistance( n4Directions3);
		n4[3].setDistance( n4Directions4);
		database.saveBlock( "n4", n4 , "yellow");


		Direction[] n5 = arraySetter();
		int n5Directions1[] = {1,0,0,0};
		int n5Directions2[] = {1,1,0,0};
		int n5Directions3[] = {1,2,0,0};
		int n5Directions4[] = {2,2,0,0};
		n5[0].setDistance( n5Directions1 );
		n5[1].setDistance( n5Directions2 );
		n5[2].setDistance( n5Directions3 );
		n5[3].setDistance( n5Directions4 );
		database.saveBlock( "n5", n5 ,"green");

		Direction[] n6 = arraySetter();
		int n6Directions1[] = {1,0,0,0};
		int n6Directions2[] = {1,1,0,0};
		int n6Directions3[] = {0,1,0,0};
		int n6Directions4[] = {0,1,1,0};
		n6[0].setDistance( n6Directions1 );
		n6[1].setDistance( n6Directions2 );
		n6[2].setDistance( n6Directions3 );
		n6[3].setDistance( n6Directions4 );
		database.saveBlock( "n6", n6 , "purple");


		Direction[] n7 = arraySetter();
		int n7Directions1[] = {1,0,0,0};
		int n7Directions2[] = {2,0,0,0};
		int n7Directions3[] = {1,1,0,0};
		int n7Directions4[] = {1,2,0,0};
		n7[0].setDistance( n7Directions1 );
		n7[1].setDistance( n7Directions2 );
		n7[2].setDistance( n7Directions3 );
		n7[3].setDistance( n7Directions4 );
		database.saveBlock( "n7", n7 , "gray");

		Direction[] n8 = arraySetter();
		int n8Directions1[] = {1,1,0,0};
		int n8Directions2[] = {0,1,0,0};
		int n8Directions3[] = {0,2,0,0};
		int n8Directions4[] = {0,0,1,0};
		n8[0].setDistance( n8Directions1 );
		n8[1].setDistance( n8Directions2 );
		n8[2].setDistance( n8Directions3 );
		n8[3].setDistance( n8Directions4 );
		database.saveBlock( "n8", n8 , "orange");

		Direction[] n9 = arraySetter();
		int n9Directions1[] = {1,0,0,0};
		int n9Directions2[] = {1,1,0,0};
		int n9Directions3[] = {2,0,0,0};
		int n9Directions4[] = {3,0,0,0};
		n9[0].setDistance( n9Directions1 );
		n9[1].setDistance( n9Directions2 );
		n9[2].setDistance( n9Directions3 );
		n9[3].setDistance( n9Directions4 );
		database.saveBlock( "n9", n9 , "magenta");

		Direction[] n10 = arraySetter();
		int n10Directions1[] = {1,0,0,0};
		int n10Directions2[] = {2,0,0,0};
		int n10Directions3[] = {3,0,0,0};
		int n10Directions4[] = {4,0,0,0};
		n10[0].setDistance( n10Directions1 );
		n10[1].setDistance( n10Directions2 );
		n10[2].setDistance( n10Directions3 );
		n10[3].setDistance( n10Directions4 );
		database.saveBlock( "n10", n10, "cyan" );



		Direction[] n11 = arraySetter();
		int n11Directions1[] = {0,1,0,0};
		int n11Directions2[] = {0,1,1,0};
		int n11Directions3[] = {0,2,0,0};
		int n11Directions4[] = {1,2,0,0};
		n11[0].setDistance( n11Directions1 );
		n11[1].setDistance( n11Directions2 );
		n11[2].setDistance( n11Directions3 );
		n11[3].setDistance( n11Directions4 );
		database.saveBlock( "n11", n11, "dark_gray" );


		Direction[] n12 = arraySetter();
		int n12Directions1[] = {1,0,0,0};
		int n12Directions2[] = {2,0,0,0};
		int n12Directions3[] = {2,1,0,0};
		int n12Directions4[] = {2,2,0,0};
		n12[0].setDistance( n12Directions1 );
		n12[1].setDistance( n12Directions2 );
		n12[2].setDistance( n12Directions3 );
		n12[3].setDistance( n12Directions4 );
		database.saveBlock( "n12", n12, "light_gray");


		Direction[] directions = arraySetter();
		int[] d11 = { 1, 0, 0, 0 };
		int[] d12 = { 1, 1, 0, 0 };
		int[] d13 = { 1, 2, 0, 0 };
		directions[0].setDistance(d11);
		directions[1].setDistance(d12);
		directions[2].setDistance(d13);

		database.saveBlock( "d1", directions, "red");

		directions = arraySetter();
		int[] d21 = {0,1,0,0};
		int[] d22 = {0,2,0,0};
		directions[0].setDistance(d21);
		directions[1].setDistance(d22);
		database.saveBlock( "d2", directions, "blue");

		directions = arraySetter();
		int[] d31 = { 0, 1, 0, 0};
		int[] d32 = { 0, 1, 1, 0};
		directions[0].setDistance(d31);
		directions[1].setDistance(d32);
		database.saveBlock("d3", directions, "green" );
		
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
		database.saveBlock("d4", directions, "purple" );

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
		database.saveBlock("d5", directions, "magenta" );
				
		database.closeDatabase();
	}
	
	static Direction[] arraySetter()
	{
		Direction[] directions = new Direction[6];
		for( int i = 0; i < 6 ; i++)
			directions[ i ] = new Direction();
		return directions;
	}*/
	}
}
