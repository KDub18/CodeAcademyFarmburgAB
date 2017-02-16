public class Nutrient{
	public String name;
	public String amount;
	//public String unit;
	public int percentage;
	
	public Nutrient(String nutrientName, String nutriAmount, /*String unitName*/ int percent){
		name = nutrientName;
		amount = nutriAmount;
		//unit = unitName;
		percentage = percent;
	}
	public Nutrient(){
		this("","", 0);
	}
	/*****Returns nutrient name ****/
	public String getNutrient(){
		return name;
	}
	
	/****Returns amount with unit***/
	public String getAmount(){
		return amount;
	}
	
	/*public String getUnit(){
		return unit;
	}*/
	/****Returns percentage****/
	public int getPercent(){
		return percentage;
	}
	
	/****Sets the nutrient name ***/
	public void setName(String newName){
		name = newName;
	}
	
	/****Sets the amount of the nutrient with units***/
	public void setAmount(String newValue){
		amount = newValue;
	}
	
	/*public void setUnit(String newUnit){
		unit = newUnit;
	}*/
	/****Sets the percentage****/
	public void setPercent(int newPercent){
		percentage = newPercent;
	}
	
	/****Returns all the nutrient information in a string***/
	public String toString(){
		return name + " " + amount + " " + percentage + "%";
	}
	public int convertGtoMG(int gram){
		int mg;
		mg = gram * 1000;
		return mg;
	}
	public double convertMGtoG(double mg){
		double g;
		g= mg * 0.001;
		return g;
		
	}
}