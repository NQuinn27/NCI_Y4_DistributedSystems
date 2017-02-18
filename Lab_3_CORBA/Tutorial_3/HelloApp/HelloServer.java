import org.omg.CORBA.*;
import java.io.*;
import HelloApp.*;

public class HelloServer {
  public static void main(String args[]) {
    try {
      // Initialize the ORB
      ORB orb = ORB.init(args, null);
      // Instantiate the HelloServant on the server
      HelloServant helloRef = new HelloServant();
      // Connect the HelloServant to the orb
      orb.connect(helloRef);
      //Store an object Reference to the HelloServant in a
      // String format
      String ior = orb.object_to_string(helloRef);
      //Write the object reference to the helloServant to a //file called HelloIOR
      FileOutputStream fos = new FileOutputStream("HelloIOR"); PrintStream ps = new PrintStream(fos);
      ps.print(ior);
      ps.close();
      //Run the orb so that it waits for requests from the
      // client
      orb.run();
    } catch(Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out); }
    }
}
