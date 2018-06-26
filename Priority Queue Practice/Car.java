public class Car implements Comparable<Car>
{
	String carID, mpg, engineSize, hp, weight, acceleration, countryOfOrigin, cylinders;

	public Car()
	{
	}


	public void setCarID(String carID)
	{
		this.carID = carID;
	}

	public void setMpg(String mpg)
	{
		this.mpg = mpg;
	}

	public void setEngineSize(String engineSize)
	{
		this.engineSize = engineSize;
	}

	public void setHp(String hp)
	{
		this.hp = hp;
	}

	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	public void setAcceleration(String acceleration)
	{
		this.acceleration = acceleration;
	}

	public void setCountry(String countryOfOrigin)
	{
		this.countryOfOrigin = countryOfOrigin;
	}

	public void setCylinders(String cylinders)
	{
		this.cylinders = cylinders;
	}


	public String getCarID()
	{
		return carID;
	}

	public String getMpg()
	{
		return mpg;
	}

	public String getEngineSize()
	{
		return engineSize;
	}

	public String getHp()
	{
		return hp;
	}

	public String getWeight()
	{
		return weight;
	}

	public String getAcceleration()
	{
		return acceleration;
	}

	public String getCountry()
	{
		return countryOfOrigin;
	}

	public String getCylinders()
	{
		return cylinders;
	}


	public int compareTo (Car otherCar)
	{
		if (Integer.valueOf(acceleration) > Integer.valueOf(otherCar.getAcceleration()))
			return -1;
		else if (Integer.valueOf(acceleration) < Integer.valueOf(otherCar.getAcceleration()))
			return 1;
		else
		{
			if (Integer.valueOf(mpg) > Integer.valueOf(otherCar.getMpg()))
				return -1;
			else if (Integer.valueOf(mpg) < Integer.valueOf(otherCar.getMpg()))
				return 1;
			else
			{
				if (Integer.valueOf(hp) > Integer.valueOf(otherCar.getHp()))
					return -1;
				else if (Integer.valueOf(hp) < Integer.valueOf(otherCar.getHp()))
					return 1;
				else
				{
					if (Integer.valueOf(engineSize) > Integer.valueOf(otherCar.getEngineSize()))
						return -1;
					else if (Integer.valueOf(engineSize) < Integer.valueOf(otherCar.getEngineSize()))
						return 1;
					else
					{
						if (Integer.valueOf(weight) > Integer.valueOf(otherCar.getWeight()))
							return -1;
						else if (Integer.valueOf(weight) < Integer.valueOf(otherCar.getWeight()))
							return 1;
						else
						{
							if (Integer.valueOf(cylinders) > Integer.valueOf(otherCar.getCylinders()))
								return -1;
							else if (Integer.valueOf(cylinders) < Integer.valueOf(otherCar.getCylinders()))
								return 1;
							else
							{
								if (Integer.valueOf(carID) > Integer.valueOf(otherCar.getCarID()))
									return -1;
								else if (Integer.valueOf(carID) < Integer.valueOf(otherCar.getCarID()))
									return 1;
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
		return "CAR "+carID+ ": acceleration - "+acceleration+ "mpg - "+mpg+ "hp - "+hp;
	}

}