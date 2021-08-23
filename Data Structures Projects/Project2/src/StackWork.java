public class StackWork
{
   private int[] S0;
   private int S0top;   
   private int S0length;
   
   private int[] S1;
   private int S1top;   
   private int S1length;
   
   private int[] S2;
   private int S2top;   
   private int S2length;
   
   
   // Precondition: S0size, S1size, and S2size are greater than 0.
   // Postcondition: The stacks S0, S1, and S2, are created, and their respective lengths are initialized.
   public StackWork(int S0size, int S1size, int S2size)
   {
      S0 = new int[S0size];
      S0length = S0size;
      S1 = new int[S1size];
      S1length = S1size;
      S2 = new int[S2size];
      S2length = S2size;
      S0top = -1;
      S1top = -1;
      S2top = -1;
   }

   // Precondition: StackSelector is equal to 0, 1, or 2.
   // Postcondition: If StackSelector is 0, then the new element x is inserted in stack S0. If StackSelector is 1, 
   // then the new element x is inserted in stack S1. If StackSelector is 2, then the new element x is inserted 
   // in stack S2.
   public void Push(int x, int StackSelector)
   {	 
      // Implement me.
      if(StackSelector == 0){
         S0top++;
         if(S0top >= S0length){
            System.out.println("Overflow detected");
            S0top--;
            return;
         }
         S0[S0top] = x;
      }else if(StackSelector == 1){
         S1top++;
         if(S1top >= S1length){
            System.out.println("Overflow detected");
            S1top--;
            return;
         }
         S1[S1top] = x;
      }else if(StackSelector ==2){
         S2top++;
         if(S2top >= S2length){
            System.out.println("Overflow detected");
            S2top--;
            return;
         }
         S2[S2top] = x;
      }else {
         System.out.println("Please select a different stack");
      }
   }
   

   // Precondition: StackSelector is equal to 0, 1, or 2.
   // Postcondition: If StackSelector is 0, then an element is taken out of stack S0 and is returned to the caller. 
   // If StackSelector is 1, then an element is taken out of stack S1 and is returned to the caller.
   // If StackSelector is 2, then an element is taken out of stack S2 and is returned to the caller.
 
   public int Pop(int StackSelector)
   {
      int element = -1;
      if(StackSelector == 0){
         if(S0top == -1){
            System.out.println("The stack is empty, there is nothign to remove");
            return -1;
         }
         element = S0[S0top];
         S0top--;
      } else if(StackSelector == 1){
         if(S1top == -1){
            System.out.println("The stack is empty, there is nothign to remove");
            return -1;
         }
         element = S1[S1top];
         S1top--;
      } else if(StackSelector == 2){
         if(S2top == -1){
            System.out.println("The stack is empty, there is nothign to remove");
            return -1;
         }
         element = S2[S2top];
         S2top--;
      } else {
         System.out.println("Please select a different stack");
      } 
      return element;
   }
   
   
   // Precondition: 
   // Postcondition: The elements of the stack S0 are displayed, followed by the elements of the stack S1, and in the end
   // the elements of the Stack S2.
   public void displayStackData()
   {
      System.out.println("Data in S0:");
      for(int i = 0; i <= S0top; i++){
         System.out.print(S0[i] + " ");
      }
      System.out.println("");
      System.out.println("Data in S1:");
      for(int i = 0; i <= S1top; i++){
         System.out.print(S1[i] + " ");
      }
      System.out.println("");
      System.out.println("Data in S2:");
      for(int i = 0; i <= S2top; i++){
         System.out.print(S2[i] + " ");
      }
      System.out.println("");
   }

      
   // Precondition: The StackWork object exists.
   // Postcondition: The instance variable S0top is returned.
   public int getS0top()   
   {
	   return S0top;
   }

   public int getValue(int x, int s){
      if(s == 0){
         return S0[x];
      } else if(s == 1){
         return S1[x];
      } else if(s == 2){
         return S2[x];
      } else {
         System.out.println("Please select a different stack");
         return -1;
      }
   }
   
}	