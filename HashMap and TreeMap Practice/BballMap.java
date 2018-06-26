import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.Set;

public class BballMap
{
	public static void main(String[] args)
	{
		File name = new File("BasketballPlayerList.txt");
		HashMap<String, HashSet<Player>> map = new HashMap<String, HashSet<Player>>();
		HashMap<String, TreeSet<Player>> map2 = new HashMap<String, TreeSet<Player>>();
		
		Player player = new Player();
		
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));

			String text="";
			int count = 0;
			while( (text=input.readLine())!= null)
			{
				String[] arr = text.split("\t");

				if (count>0)
				{
					player = new Player();
					player.setName(arr[1]);
					player.setPosition(arr[2]);
					player.setAge(Integer.valueOf(arr[3]));
					player.setTeam(arr[8]);
					player.setUniform(Integer.valueOf(arr[0]));
					player.setUniversity(arr[6]);

					String height = arr[4];
					String[] splitHeight = height.split("-");
					player.setHeight(Integer.valueOf(splitHeight[0]), Integer.valueOf(splitHeight[1]));
					player.setHeight(arr[4]);
					
					player.setWeight(Integer.valueOf(arr[5]));
					
					String temp = arr[7];
					temp = temp.replaceAll(",","");
					temp = temp.replaceAll(" ", "");
					player.setSalary(Integer.valueOf(temp.substring(1)));
					player.setSalary(arr[7]);

					if (!map.containsKey(player.getTeam()))
					{
						map.put(player.getTeam(), new HashSet<Player>());
					}
					map.get(player.getTeam()).add(player);
					
					if (!map2.containsKey(player.getTeam()))
					{
						map2.put(player.getTeam(), new TreeSet<Player>());
					}
					map2.get(player.getTeam()).add(player);
				}
				
				count++;
			}
			
			//PRINT STATEMENTS FOR PARTS 1-3
			/*Set<Entry<String, HashSet<Player>>> entrySet = map.entrySet();
			for (Entry<String, HashSet<Player>> entry : entrySet) 
			{
				String key = entry.getKey();
				System.out.println(key.toUpperCase()+":");
				HashSet<Player> val = entry.getValue();

				Iterator it = val.iterator();
				while(it.hasNext())
				{
					Object tem = it.next();
					System.out.println(tem);
				}
				
				System.out.println();
				
      		}*/
      		
      		//PARTS 4 & 5
			Set<Entry<String, TreeSet<Player>>> entrySet = map2.entrySet();
			for (Entry<String, TreeSet<Player>> entry : entrySet) 
			{
				String key = entry.getKey();
				System.out.println(key.toUpperCase()+":");
				TreeSet<Player> val = entry.getValue();

				Iterator it = val.iterator();
				while(it.hasNext())
				{
					Object tem = it.next();
					System.out.println(tem);
				}

				System.out.println();
							
      		}

		}catch (IOException io)
		{
			System.err.println("File does not exist");
		}

		
	}
}