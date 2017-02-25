import CA1.*;

class CA1Servant extends _AddImplBase
{
    public int addTwoNumbers(int a, int b)
    {
      //Take in two integers and return the result of add operation
		    System.out.println("Received a call.");
        int added = a + b;
		    return added;
    }
}
