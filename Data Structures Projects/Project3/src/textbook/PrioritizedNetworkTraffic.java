package textbook;


public class PrioritizedNetworkTraffic {

	private int[] Q;

	private int head = -1;
	private int tail = 0;
	private int length;
	
	// Precondition: Priority queue Q is defined but not created.
	// Postcondition: Priority queue Q is created, and its size is set to "queueSize".
	//                All values of Q are initialized to -1.
	//                length is set to "queueSize".
	public PrioritizedNetworkTraffic(int queueSize)
	{
		// Implement me.
		Q = new int[queueSize];
		for(int i = 0; i < Q.length; i++){ //initialize Q value to -1
			Q[i] = -1;
		}
		length = queueSize;
	}
	
	
	// Precondition: Priority queue Q is existent.
	//               "tail" is already set to the index of Q where a new packet may be inserted. 
	//               "head" is already set to the index of Q where the packet with the highest priority is stored.
	// Postcondition: A new packet with priority "newElement" is inserted in the priority queue Q.
	//                "head" is set to the index of Q where the packet with the highest priority is stored.
	//                "tail" is set to the next index of Q that is available for storage of a new element.
	//                If Q is full, "tail" is to the index of Q where the packet with the lowest priority is stored.
	public void Enqueue(int newElement)
	{	   
		// Implement me.
		Q[tail] = newElement;
		if(head == -1 || Q[head] < newElement){
			head = tail;
		}
		if(tail + 1 >= length || Q[tail + 1] != -1){
			tail = LocateNextTail(0, length - 1);
		} else{
			tail++;
		}
	}

	
	// Precondition: Priority queue Q is existent.
	//               "head" is already set to the index of Q where the packet with the highest priority is stored.
	//               If Q is empty, "head" is -1.
	//               "tail" is already set to the index of Q where a new packet may be inserted.
	// Postcondition: The packet with index equal to "head" is removed from Q and is returned to the caller.
	//                -1 is inserted in its place, namely in the cell of Q where the removed packet was previously stored. 
    //                "head" is set to the index of Q where the packet with the highest priority is stored.
    //                "tail" is set to the next index of Q that is available for storage of a new element.
    //                If Q is empty, "head" is set to -1 and "tail" is set to 0. 
	
	public int Dequeue()
	{	   
		// Implement me.
		if(head == -1){
			System.out.println("The queue is empty");
			tail = 0;
			return -1;
		}
		int value = Q[head];
		Q[head] = -1;
		head = LocateNextHead(0, length - 1);
		tail = LocateNextTail(0, length - 1);
		return value;
	}
	
	// Precondition: Priority queue Q is existent.
	//               "startIndex" and "endIndex" are indices of Q that define the beginning and the end, respectively, of the subarray of Q that needs to be searched.
	//               "startIndex" and "endIndex" may refer to the entire Q. 
	// Postcondition: The index of Q, where the packet with the highest priority is stored, is found and is returned to the caller. 
	public int LocateNextHead(int startIndex, int endIndex)
	{	   
		// Implement me.
		//it would be simplest to just run through the array, but I'm guessing we're supposed to practice recursion here
		int index = 0;
        int distance = (endIndex - startIndex);


        if(distance == 0){
            index = startIndex;
        } else {
            int left = LocateNextHead(startIndex, startIndex + distance/2);
            int right = LocateNextHead(startIndex + distance/2 + 1, endIndex);
            if(Q[left] >= Q[right]){
                index = left;
            }else {
                index = right;
            }
        }
        return index;
	}
	
	
	// Precondition: Priority queue Q is existent.
	//               "startIndex" and "endIndex" are indices of Q that define the beginning and the end, respectively, of the subarray of Q that needs to be searched.
	//               "startIndex" and "endIndex" may refer to the entire Q. 
	// Postcondition: The index of Q, where the packet with the lowest priority is stored, is found and is returned to the caller. 
	public int LocateNextTail(int startIndex, int endIndex)
	{	   
		// Implement me.
		//it would be simplest to just run through the array, but I'm guessing we're supposed to practice recursion here
		int index = 0;
        int distance = (endIndex - startIndex);


        if(distance == 0){
            index = startIndex;
        } else {
            int left = LocateNextTail(startIndex, startIndex + distance/2);
            int right = LocateNextTail(startIndex + distance/2 + 1, endIndex);
            if(Q[left] <= Q[right]){
                index = left;
            }else {
                index = right;
            }
        }
        return index;
	}
	
	
	// Precondition: Priority queue Q is existent. 
	// Postcondition: The index and priority of each packet in Q are displayed on the monitor.
	public void DisplayPriorityQueue()
	{
		// Implement me.
		for(int i = 0; i < length; i++){
			System.out.println("Index: " + i + " Packet priority: " + Q[i]);
		}
		System.out.println("Current head: " + head);
		System.out.println("Current tail: " + tail);
	}
	
}

