import SampleCA1.*;

class SampleCA1Servant extends _HelloImplBase
{
    public String sayHello()
    {
		System.out.println("Received a call.");
		return "\nHello world !!\n";
    }
}
