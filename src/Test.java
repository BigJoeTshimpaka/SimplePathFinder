
public class Test {

	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
       LoadMap map = new LoadMap("C:\\Developments\\Projects\\Java\\SimplePathFinding\\map.txt");
       //map.displayMap();
       displayTableMap(map.getMapTable());
       FindPath findPath =  new FindPath(map.getMapTable());
      findPath.findShortestPath();
       
       
	}
	
	public static void displayTableMap(String[][] data)
	{
		for (int i = 0; i < data.length; ++i) {
	        for (int j = 0; j < data[i].length; ++j)
	            System.out.print(data[i][j] + " ");
	        System.out.println();
	    }
	}

}
