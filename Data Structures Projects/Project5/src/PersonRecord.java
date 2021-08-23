
public class PersonRecord {
	
	private long key;
	
	private int age;
	
	private int height;
	
	private String profession;
	
	private String fullName;
	
	
	public PersonRecord(long keyVal, int ageVal, int heightVal, String professionVal, String fullNameVal)
	{
	    key = keyVal;
	    age = ageVal;
	    height = heightVal;
	    profession = professionVal;
	    fullName = fullNameVal;
	}
	
	
	public long getKey()   
	{
	     return key;     
	}
	
	
	public void setKey(long newKey)   
	{
	     key = newKey;     
	}
	
}


