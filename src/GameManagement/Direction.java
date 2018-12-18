package GameManagement;
public class Direction {
	private int[] distance;
	private int right;
	private int left;
	private int down;
	private int up;


	public Direction( int right, int down, int left, int up )
	{
		distance = new int[4];
		this.right = right;
		this.left = left;
		this.down = down;
		this.up = up;
		setDistance( right, down, left, up);
	}

	public Direction(){
		distance = new int[4];
	}

	public int[] getDistance()
	{
		return distance;
	}

	private void setDistance(int right, int down, int left, int up)
	{
		setRight( right );
		setLeft( left );
		setDown( down );
		setUp( up );
	}

	public void setRight( int right )
	{
		this.right = right;
		distance[ 0 ] = right;
	}

	public void setUp(int up) {
		this.up = up;
		distance[ 3 ] = up;
	}

	public void setDown(int down) {
		this.down = down;
		distance[ 1 ] = down;
	}

	public void setLeft( int left )
	{
		this.left = left;
		distance[ 2 ] = left;
	}

	public void setDistance(int[] distance) {
		this.distance = distance;
	}

	public int getUp() {
		return up;
	}

	public int getRight() {
		return right;
	}

	public int getDown() {
		return down;
	}

	public int getLeft() {
		return left;
	}
}
