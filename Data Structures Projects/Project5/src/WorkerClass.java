
public class WorkerClass {

	public static void main(String[] args) {
		
		System.out.println("Working with a hash table data structure.\n");
			
		Person people = new Person(701);
		
		PersonRecord Bob = new PersonRecord(5894876512L, 25, 6, "Student", "Bob Smith");
		
		PersonRecord Alice = new PersonRecord(2482248756L, 24, 6, "Student", "Alice Hudson");
		
		PersonRecord resultOfSearch;
		
		people.InsertRecord(Bob);
		
		people.InsertRecord(Alice);
		
		resultOfSearch = people.SearchRecord(5894876512L);
		
		if (resultOfSearch != null)
		{
			System.out.println("Record found.\n");
		
			 // access the other data about the person modeled by the record that you found.
		}
	}

}