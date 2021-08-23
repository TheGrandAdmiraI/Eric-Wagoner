
public class Person {

	private PersonRecord[] HT;
	private int size;

	public Person(int arraySize)
	{
	    HT = new PersonRecord[arraySize];
	    
	    for (int i=0; i < arraySize; i++)
	    {
	        HT[i] = null;
		}
		size = arraySize;
	}
	
	
	public void InsertRecord(PersonRecord newRecord)
	{
		long key = newRecord.getKey();
		
		int index = hash(key);
		
		while (HT[index] != null)
			index++;
		
		HT[index] = newRecord;
	}
	
	
	public PersonRecord SearchRecord(long searchKey)
	{
		int index = hash(searchKey);
		
		PersonRecord candidateRecord = null;
		long candidateKey = 0;
		
		while (index < size)
		{	
			candidateRecord = HT[index];
			
			if (candidateRecord != null)
			{ 	
			   candidateKey = candidateRecord.getKey();	
			   
			   if (candidateKey == searchKey)
				   break;
			   else
				   candidateRecord = null;
			}
			else
		        index++;
		}	
			
		return candidateRecord;
	}

	//from doing some quick research online, apparently a good hash function for integer values is the "mid-square method"
	//since we are working with long int values of length 10, we want to take the middle 10 values from the squared number and use that as the hash value
	//however, since the square of a 10 digit number is 20 digits, the long value isn't large enough (only goes up to 9,223,372,036,854,775,807, 19 digits)
	//Therefore I'll adjust the function slightly. I'll divide by 10 to get a 9 digit number, square that, then extract the middle 9 digits.
	//Also, to make things easier and to give us a smaller array, I'm then going to take that value mod 701(the size of the array) so that the rest of the code doesn't have to be changed as much
	public int hash(long key){
		//comment
		System.out.println("key = " + key);
		//first downsize to 9 digits
		long hashed = key/10;
		System.out.println("hashed = " + hashed);
		//then square the value
		hashed = hashed * hashed;
		System.out.println("hashed = " + hashed);
		//now that the number is squared we need to take the middle 9 values (currenlty 18)
		hashed = hashed / 10000; //remove last 4
		System.out.println("hashed = " + hashed);
		hashed = hashed % 1000000000; //remove first 5
		System.out.println("hashed = " + hashed);
		//now we do mod 701 because our array is size 701
		int result = (int) (hashed % size);
		System.out.println("result = " + result);
		return result;

	}

}