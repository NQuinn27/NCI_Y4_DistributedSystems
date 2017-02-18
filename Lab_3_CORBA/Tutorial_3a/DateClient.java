import DateApp.*;
import org.omg.CORBA.*;
import java.io.*;
import java.net.*;

public class DateClient{
  static String stringifiedObjectReference;

  public static void main(String args[]){
    try{
      
      // create and initialize the ORB

        
      //Read from file and convert the stringified object reference
      // to a generic CORBA object reference
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("IOR")) ;
        String ior = (String) in.readObject() ;
        in.close() ;

        org.omg.CORBA.Object genericObjRef ;
        
        //Convert the string read from the IOR file to an Object, using
        // string_to_object() method.

        //Cast, or narrow the generic object reference to a
        // true object reference.

      
        //Call the getDateMethod() and
        // print results




         }catch (Exception e) {
      System.out.println("ERROR : " + e) ;
      e.printStackTrace(System.out);
    }//end catch block
  }//end main() method

}//end DateClient class
