import HelloApp.*;
import org.omg.CORBA.*;
import java.io.*;

public class HelloClient {
  public static void main(String args[]) {
    try {
      ORB orb = ORB.init(args, null);
      BufferedReader br = new BufferedReader(new FileReader("HelloIOR"));
      String ior = br.readLine();org.omg.CORBA.Object obj = orb.string_to_object(ior);
      Hello helloRef = HelloHelper.narrow(obj);
      String hello = helloRef.sayHello(); System.out.println(hello);
    } catch(Exception e) {
      System.out.println("ERROR : " + e);
      e.printStackTrace(System.out); }
    }
  }
