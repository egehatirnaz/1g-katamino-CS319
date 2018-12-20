package  GameManagement;
public class BlocksDatabaseTest {

	public static void main(String[] args) {
		BlocksDatabase bd = new BlocksDatabase("13111997");
		int[] a1 = {1,2,3,4};
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
		Direction[] dir2 = {d1,d2};
		Direction[] dir3 = {d1,d2, null,null};
		bd.saveBlock("ali",dir);
		bd.saveBlock("veli",dir2);
		bd.saveBlock("mert",dir3);
		
		for (int i = 0; i < 4; i++)
			for (int a = 0; a < 4; a++)
		System.out.println(bd.getDirections("veli").get(i).getDistance()[a]);
		//bd.clearDatabase();
		bd.updateBlockInformation("mert", dir2);
		bd.deleteBlock("ali");
		System.out.println(bd.controlBlockName("mert"));
		System.out.println(bd.controlBlockName("abdfas"));
		//System.out.println(bd.getBlockNames());
		bd.closeDatabase();
	}

}
