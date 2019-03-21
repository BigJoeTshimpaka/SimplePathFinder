import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LoadMap {
	/**
	 * the field that will store the map.
	 */
	private String map ="";
	
	/**
	 * Parameterized Constructor
	 */
	public LoadMap(String map)
	{
		this.map = map;
	}
	
	/**
	 * Open a file location and reads all the file content into a string.
	 * @param fileName
	 *     the full path of the map to be loaded
	 * @return string 
	 *      File payload
	 * @throws empty string
	 */
	public String getMap() {
		 String content; 
		 
		try {
			  File file = new File(map); 
			  
			  BufferedReader br = new BufferedReader(new FileReader(file)); 
			  
			  while ((content = br.readLine()) != null) {
			    System.out.println(content); 
			  }	
		}
		catch(IOException e) {
			content="";
			
		}
		 return content;
		
	}
	/**
	 * Open a file location and reads all the file content into a List on string.
	 * @param fileName
	 *    the path to the file location
	 * @return list of string in the file
	 * @throws empty list
	 */
	public  List<String> getMapAsList(String fileName) throws Exception 
	{ 

		List<String> lines = Collections.emptyList(); 
		try
		{ 
			lines = 
					Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
		} 

		catch (IOException e) 
		{ 
			System.out.println(e.toString()); 
		} 
		return lines; 
	}
	public void displayMap() throws Throwable
	{
		if (map.length()==0)
		{
			System.out.println("Your Map is empty or not existent!");
		}
		else
		{
			List<String> l = getMapAsList(map); 
			  
		    Iterator<String> itr = l.iterator(); 
		    while (itr.hasNext()) 
		      System.out.println(itr.next()); 
		}
		
	}
	/**
	 * @throws Exception 
	 * Open a file location and reads all the file content as a table.
	 * @param fileName
	 *     the full path of the map to be loaded
	 * @return string 
	 *      table containing all the map entries
	 * @throws 
	 */
	public String[][] getMapTable() {
		ArrayStructure s = new ArrayStructure(); 
		try
		{
			List<String> lines = getMapAsList(map);
			Integer row = 0;
			for(String items : lines)
			{
				String[] item = items.split("\\s+");
				
				for (Integer i=0 ; i <item.length; i++)
				{
					s.add(row  , i, item[i]);
				}
				row++;
			}
	
		}
		catch(Exception exp)
		{
			System.out.println(exp.getMessage());
		}
	 return s.toArray();
		
	}

}
