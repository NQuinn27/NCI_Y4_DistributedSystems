import CA1.*;

class CA1Servant extends _AddImplBase
{
    public int addTwoNumbers(int a, int b)
    {
		    System.out.println("Received a call.");
        int added = a + b;
		    return added;
    }
}
