import java.nio.channels.FileChannel.MapMode;
import java.util.*;

import javax.swing.tree.RowMapper;

public class FindPath {
    
	private  String[][] mapTable;  //a two dimensional array that stores a map
    private Integer maxRow =0;  // maximum number of rows starting at index 0
    private Integer maxColumn =0 ; // maximum number of columns starting at index 0 
    private  Boolean noEnd = false;
	
	private List<Tuple<Integer,Integer>> visitedPaths = new ArrayList<Tuple<Integer,Integer>>(); //All the visited points
	private List<Tuple<Integer,Integer>> travelPath = new ArrayList<Tuple<Integer,Integer>>(); // only the traveled path within the current iteration

	//the class will only have one constructor that will accept the map table 
	public FindPath( String[][] mapTable)
	{
		this.mapTable = mapTable;
		maxRow = mapTable.length;
		maxColumn = mapTable[0].length; //assuming that the loaded Map will have equal number of columns per row
	}
	// this with define the next direction that the command should to
	public static class MyEnum
	{
		
		public enum Direction {
		left,
		right,
		up,
		down

		}
		public Direction direction;
		}

	/**
	  
	 * Find the shortest possible that one could travel from the S to E
	 * Assuming that every map will contain an S and E
	 * @param 
	 *     
	 * @return List<Tuple<Integer,Integer>> 
	 *      A list of all that the points traveled from S to E  
	 * @throws 
	 */
	 public List<Tuple<Integer,Integer>> findShortestPath()
	 
	 {
		 Tuple<Integer,Integer> currentPosition = findStartPosition(); 
		 if(visitedPaths ==null)
		 {
			 visitedPaths =  new ArrayList<>();
		 }
		//visitedPaths.add(currentPosition);
		 Tuple<Integer,Integer> endPostion = findEndPosition(mapTable);
		 
		 MyEnum.Direction direction = MyEnum.Direction.right; 
		 //check what is in front of us and around us
		
		while(currentPosition != endPostion )
		{
		Integer col =currentPosition.x;
		Integer row = currentPosition.y;
		if(col < maxColumn - 1 && (mapTable[row ][col + 1 ].equals(".") || mapTable[row ][col + 1 ].equals("E")))
		 {
			    if(mapTable[row ][col + 1 ].equals("E"))
			    {
			    	currentPosition = endPostion;
			    }
			    else {
			 	direction =MyEnum.Direction.right;
				currentPosition = nextPosition(currentPosition, direction);
			    addToTravelledPath(currentPosition);
				mapTable[currentPosition.y][currentPosition.x]="\"";
				addToVisitedPath(currentPosition);
			    }
			
		 }
		
		 else if(row < maxRow -1 && (mapTable[row + 1][col].equals(".") || mapTable[row + 1][col].equals("E")))
		 {
			  if(mapTable[row + 1][col].equals("E"))
			    {
			    	currentPosition = endPostion;
			    }
			    else {
				direction =MyEnum.Direction.down;
				currentPosition = nextPosition(currentPosition, direction);
			    addToTravelledPath(currentPosition);
				mapTable[currentPosition.y][currentPosition.x]="\"";
				addToVisitedPath(currentPosition);
			    }
		 }
		 else if(row > 0 && (mapTable[row -1][col].equals(".") || mapTable[row -1][col].equals("E")))
		 {
			  if(mapTable[row -1][col].equals("E"))
			    {
			    	currentPosition = endPostion;
			    }
			    else {
			    direction =MyEnum.Direction.up;
			    currentPosition = nextPosition(currentPosition, direction);
			    addToTravelledPath(currentPosition);
				mapTable[currentPosition.y][currentPosition.x]="\"";
				addToVisitedPath(currentPosition);
			    }
		 }
		 else if(col >0 && mapTable[row][col- 1 ].equals("."))
		 {
			 if(mapTable[row ][col - 1 ].equals("E"))
			    {
			    	currentPosition = endPostion;
			    }
			    else {
			 	direction =MyEnum.Direction.left;
				currentPosition = nextPosition(currentPosition, direction);
			    addToTravelledPath(currentPosition);
				mapTable[currentPosition.y][currentPosition.x]="\"";
				addToVisitedPath(currentPosition);
			    }
			 
             				
		 }
		 
		 
		 else
		 {
			 noEnd =true;
		 }
			
			if(noEnd)
			{
				
				//find the last visited node and check that does not have wall and start from there
				for (Integer i = visitedPaths.size()-1; i >= 0   ; i--)
				{
				   Tuple<Integer,Integer> temp	=visitedPaths.get(i);
				   if(temp.x < maxColumn -1  && mapTable[temp.y][temp.x + 1].equals("."))
				   {
					   currentPosition = temp;
					   //notvisitedPaths.add(temp);
					   break;
				   }
				  
				   else if(temp.y < maxRow - 1 && mapTable[temp.y +  1][temp.x ].equals("."))
				   {
					   currentPosition =temp;// new Tuple<Integer,Integer>(temp.x, temp.y + 1);
					   //notvisitedPaths.add(temp);
					   break;
				   }
				   else if(temp.y > 0 && mapTable[temp.y - 1][temp.x].equals("."))
				   {
					   currentPosition = temp;new Tuple<Integer,Integer>(temp.x , temp.y-1);
					   //notvisitedPaths.add(temp);
					   break;
				   }

				   travelPath.remove(temp);
				   
				}
				
				noEnd = false;
			}
			 displayTableMap(mapTable);
		}
		for(Tuple<Integer,Integer> item: travelPath)
	       {
			mapTable[item.y][item.x]="*";
	       }
		displayTableMap(mapTable);

		return travelPath;
		 
	 }
	 //insure that there is no duplicate since a List is not hashed
	 private void addToVisitedPath(Tuple<Integer, Integer> item){
		 if(!visitedPaths.contains(item)){
			 visitedPaths.add(item);
		 }
	 }
	//insure that there is no duplicate since a List is not hashed
	 private void addToTravelledPath(Tuple<Integer, Integer> item){
		 if(!travelPath.contains(item)){
			 travelPath.add(item);
		 }
	 }
	// private void addToNotVisitedPath(Tuple<Integer, Integer> item){
	//	 if(!notvisitedPaths.contains(item)){
	//		 notvisitedPaths.add(item);
	//	 }
	// }
	 
	 
	 private Tuple<Integer, Integer> findEndPosition(String[][] mapTable) {
		// This method find the end point of the path
		 for (int row = mapTable.length -1 ; row >=0 ; row--)
	            for (int column = 0; column < mapTable[row].length; ++column) {
	            	
	                if(mapTable[row][column].equals("E"))
	                {
	                	Tuple<Integer,Integer> p = new Tuple<Integer,Integer>(row, column);
								return p;	
	                }
	            }
		return null;
	}
	private void displayTableMap(String[][] data)
		{
			for (int i = 0; i < data.length; ++i) {
		        for (int j = 0; j < data[i].length; ++j)
		            System.out.print(data[i][j] + " ");
		        System.out.println();
		    }
		}

	private Tuple<Integer, Integer> nextPosition(Tuple<Integer,Integer> currentPosition, MyEnum.Direction direction)
	 {
		
		 Tuple<Integer, Integer> pos = currentPosition;
		 switch(direction)
		 {
		    case right:
		    	if(currentPosition.y < maxColumn )
		    	pos = new Tuple<Integer, Integer>( currentPosition.x + 1,currentPosition.y);
		    	else
		    		noEnd =true;
		    	break;
		    case left:
		    	if(currentPosition.x > 0)
		    	pos = new Tuple<Integer, Integer>(currentPosition.x - 1 , currentPosition.y );
		    	else
		    		noEnd =true;
		    	break;
		    case up:
		    	if(currentPosition.y > 0 )
		    	pos = new Tuple<Integer, Integer>(currentPosition.x  , currentPosition.y - 1 );
		    	break;
		    case down:
		    	if(currentPosition.y < maxColumn)
		    	pos = new Tuple<Integer, Integer>(currentPosition.x , currentPosition.y + 1);
		    	else
		    		noEnd =true;
		    	break;
		 }
		 return pos;
	 }

	 private Tuple<Integer, Integer> findStartPosition()
	 {
		 
		 for(Integer i = maxRow - 1 ; i >=0; i--)
		 {
			 if(mapTable[i][0].equals("S"))
			 {
				 Tuple<Integer, Integer> startPos = new Tuple<Integer, Integer>(0, i );
			     return startPos;
			 }
		 }
		 
		 return null;
	 }
	 
}
