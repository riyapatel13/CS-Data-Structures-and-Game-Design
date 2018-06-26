public class Player implements Comparable<Player>
{
	String name, position, team, tempHeight, university, tempSalary;
	int uniform, age, heightFt, heightIn, weight, salary;

	public Player()
	{
	}

	public void setName(String n){name = n;}
	public void setUniversity(String u){university = u;}
	public void setPosition(String p){position = p;}
	public void setTeam(String t){team = t;}
	public void setUniform(int u){uniform = u;}
	public void setAge(int a){age = a;}
	public void setHeight(int f, int i){
		heightFt = f;
		heightIn = i;
	}
	public void setHeight(String h){tempHeight = h;}
	public void setWeight(int w){weight = w;}
	public void setSalary(int s){salary = s;}
	public void setSalary(String s){tempSalary = s;}

	public String getName(){return name;}
	public String getUniversity(){return university;}
	public String getPosition(){return position;}
	public String getTeam(){return team;}
	public int getUniform(){return uniform;}
	public int getAge(){return age;}
	public int getHeightFt(){return heightFt;}
	public int getHeightIn(){return heightIn;}
	public int getWeight(){return weight;}
	public int getSalary(){return salary;}

	
	public int compareTo(Player otherPlayer)
	{
		if (position.compareTo(otherPlayer.getPosition())<0)
			return -1;
		else if (position.compareTo(otherPlayer.getPosition())>0)
			return 1;
		else
		{
			if (heightFt<otherPlayer.getHeightFt())
				return 1;
			else if (heightFt>otherPlayer.getHeightFt())
				return -1;
			else 
			{
					if (heightIn<otherPlayer.getHeightIn())
						return 1;
					else if (heightIn>otherPlayer.getHeightIn())
						return -1;
					else 
					{
						if (weight<otherPlayer.getWeight())
							return 1;
						else if (weight>otherPlayer.getWeight())
							return -1;
						else 
						{
							if (uniform<otherPlayer.getUniform())
								return -1;
							else if (uniform>otherPlayer.getUniform())
								return 1;
							else 
							{
								if (salary<otherPlayer.getSalary())
									return 1;
								else if (salary>otherPlayer.getSalary())
									return -1;
								else 
								{
									if (uniform<otherPlayer.getUniform())
										return -1;
									else if (uniform<otherPlayer.getUniform())
										return 1;
									else 
									{
										if (university.compareTo(otherPlayer.getUniversity())<0)
											return -1;
										else if (university.compareTo(otherPlayer.getUniversity())>0)
											return 1;
										else 
										{
											if (age<otherPlayer.getAge())
												return -1;
											else if (age>otherPlayer.getAge())
												return 1;
										}								
									}
								}			
							}
						}
					}
			}
		}
		return 0;
	}

	public String toString()
	{
		return name+"; "+position+"; "+tempHeight+"; "+weight+" lbs; Number: "+uniform+"; Salary: "+tempSalary+"; "+university+"; "+age+" years old";
	}
}