public class packetSelector {
	
	// Define packets, and initialize it to null.
	
	public static void main(String[] args) {
		
		System.out.println("Packet selector is now running...\n");
		
		StackWork packets = new StackWork(10, 10, 10);
		
		packets.Push(95, 0);
		packets.Push(103, 0);
		packets.Push(81, 0);
		packets.Push(120, 0);
		packets.Push(74, 0);
		packets.Push(134, 0);
		packets.Push(62, 0);
		packets.Push(101, 0);
		packets.Push(59, 0);
		packets.Push(148, 0);
		
		packets.displayStackData();
		
		reorganizeNetworkTraffic(packets);
		
		packets.displayStackData();
		
	}
	
	
	// Precondition: The StackWork object packets is different than null.
	// Postcondition: The elements of the stack S0 that are equal to, or less than 100, are inserted in the stack S1.
	// The elements of the stack S0 that are greater than 100 are inserted in the stack S2.
	public static void reorganizeNetworkTraffic(StackWork S)   
	{
	   for(int i = S.getS0top(); i >= 0; i--){
		if(S.getValue(i, 0) <= 100){
			S.Push(S.getValue(i, 0), 1);
		} else {
			S.Push(S.getValue(i, 0), 2);
		}
		S.Pop(0);
	   }
	} 
	
}       

