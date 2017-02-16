package data;

public class Fats {
	public String fatType;
	public String fatAmount;
	public int fatPercentage;
	
	public Fats(String type, String amount, int percent){
		fatType = type;
		fatAmount = amount;
		fatPercentage = percent;
	}
	
	public Fats(){
		this("","",0);
	}
	
	public String getFat(){
		return fatType;
	}
	
	public String getAmount(){
		return fatAmount;
	}
	
	public int getPercent(){
		return fatPercentage;
	}
	
	public void setFat(String fatName){
		fatType = fatName;
	}
	
	public void setAmount(String newAmount){
		fatAmount = newAmount;
	}
	
	public void setPercent(int percentage){
		fatPercentage = percentage;
	}
	
	public String toString(){
		return fatType + " " + fatAmount + " " + fatPercentage + "%";
	}
}
