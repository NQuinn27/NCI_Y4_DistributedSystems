import java.util.Properties;
import java.io.*;
import org.omg.CORBA.*;
import CA1.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;

public class CA1Client {
  //The root context
  public static NamingContextExt rootCtx;

  public static void list(NamingContext n, String indent) {
    try {
      final int batchSize = 1;
      BindingListHolder bList = new BindingListHolder() ;
      BindingIteratorHolder bIterator = new BindingIteratorHolder();
      n.list(batchSize, bList, bIterator) ;
      if (bList.value.length != (int) 0)
      for (int i = 0; i < bList.value.length; i++) {
        BindingHolder bh = new BindingHolder(bList.value[0]);
        do {
          String stringName = rootCtx.to_string(bh.value.binding_name);
          System.out.println(indent + stringName) ;
          if (bh.value.binding_type.value() == BindingType._ncontext) {
            String _indent = "...." + indent;

            NameComponent [] name = rootCtx.to_name(stringName);
            NamingContext sub_context = NamingContextHelper.narrow(n.resolve(name));
            list(sub_context, _indent);
          }
        } while (bIterator.value.next_one(bh));
      }
      else
      System.out.println(indent + "Context is finished.") ;
    }
    catch (Exception e) {
      System.out.println("An exception occurred in list. " + e) ;
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    try{

      NameComponent nc[]= new NameComponent[2];

      Properties props = new Properties(); props.put("org.omg.CORBA.ORBInitialPort", "49000");
      ORB orb = ORB.init(args, props);
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      rootCtx = NamingContextExtHelper.narrow(objRef);

      // Call the list function to iterate through the Name Space
      list(rootCtx, "---->");
      System.out.println("Printing Done");

      //Search the Name Space for our HelloWorld Object
      nc[0] = new NameComponent("Context 2", "Context");
      nc[1] = new NameComponent("Object4", "Object");
      // NameComponent path[] = {nc};
      org.omg.CORBA.Object objRefAdder = rootCtx.resolve(nc);
      Add adderRef = AddHelper.narrow(objRefAdder);

      //grab the inputs from args
      if (args.length < 2) {
        System.out.println("Not enough arguments specified. Must be 2 numbers");
        return;
      }

      int a,b;
      try {
        a = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        System.out.println("Argument 1 not a number");
        return;
      }
      try {
        b = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        System.out.println("Argument 2 not a number");
        return;
      }

      //Add the numbers
      System.out.println("\nInvoking our object method..");
      try {
        int result = adderRef.addTwoNumbers(a, b);
        System.out.println("The input numbers were: " + a + " + " + b + ", the result is: " + result);
      } catch (Exception e) {
        System.out.println("ERROR adding : " + e) ;
      }

    } catch (Exception e) {
      System.out.println("ERROR main : " + e) ;
      e.printStackTrace(System.out);
    }
  }
}
