
public class BlocksDatabaseTest {

	public static void main(String[] args) {
		BlocksDatabase database = new BlocksDatabase("13111997");

		Direction[] n1 = new Direction[4];
		for( int i = 0; i < 4; i++)
		{
			n1[i] = new Direction();
		}
		int n1Directions1[] = {0,1,0,0};
		int n1Directions2[] = {0,2,0,0};
		int n1Directions3[] = {0,3,0,0};
		int n1Directions4[] = {1,0,0,0};
		n1[0].setDistance( n1Directions1);
		n1[1].setDistance( n1Directions2);
		n1[2].setDistance( n1Directions3);
		n1[3].setDistance( n1Directions4);
		database.saveBlock( "n1", n1 , "blue");

		Direction[] n2 = new Direction[4];
		for( int i = 0; i < 4; i++)
		{
			n2[i] = new Direction();
		}
		int n2Directions1[] = {1,0,0,0};
		int n2Directions2[] = {1,0,0,1};
		int n2Directions3[] = {2,0,0,1};
		int n2Directions4[] = {2,0,0,2};
		n2[0].setDistance( n2Directions1);
		n2[1].setDistance( n2Directions2);
		n2[2].setDistance( n2Directions3);
		n2[3].setDistance( n2Directions4);
		database.saveBlock( "n2", n2 , "red");

		Direction[] n3 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n3[i] = new Direction();
		}

		int n3Directions1[] = {0,1,0,0};
		int n3Directions2[] = {1,0,0,0};
		int n3Directions3[] = {2,0,0,0};
		int n3Directions4[] = {2,1,0,0};
		n3[0].setDistance( n3Directions1);
		n3[1].setDistance( n3Directions2);
		n3[2].setDistance( n3Directions3);
		n3[3].setDistance( n3Directions4);
		database.saveBlock( "n3", n3 , "pink");

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
		database.saveBlock( "n4", n4 , "yellow");


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
		database.saveBlock( "n5", n5 ,"green");

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
		database.saveBlock( "n6", n6 , "purple");


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
		database.saveBlock( "n7", n7 , "gray");

		Direction[] n8 = new Direction[4];
		for ( int i = 0; i < 4; i++)
		{
			n8[i] = new Direction();
		}
		int n8Directions1[] = {1,0,0,0};
		int n8Directions2[] = {0,0,1,0};
		int n8Directions3[] = {0,0,0,1};
		int n8Directions4[] = {0,1,0,0};
		n8[0].setDistance( n8Directions1 );
		n8[1].setDistance( n8Directions2 );
		n8[2].setDistance( n8Directions3 );
		n8[3].setDistance( n8Directions4 );
		database.saveBlock( "n8", n8 , "orange");

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
		database.saveBlock( "n9", n9 , "magenta");

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
		database.saveBlock( "n10", n10, "cyan" );



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
		database.saveBlock( "n11", n11, "dark_gray" );


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
				
		database.closeDatabase();
	}
	
	static Direction[] arraySetter()
	{

		Direction[] directions = new Direction[4];
		for( int i = 0; i < 4 ; i++)
			directions[ i ] = new Direction();
		return directions;
	}

}
