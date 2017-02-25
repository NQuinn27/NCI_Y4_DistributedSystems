import java.io.*;
import org.omg.CORBA.*;
import CA1.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.Properties;

public class CA1Server {

  public static void main(String[] args) {
    try {

      Properties props = new Properties(); props.put("org.omg.CORBA.ORBInitialPort", "49000");
			ORB orb = ORB.init(args, props);

			NameComponent nc[] = new NameComponent[1];
			CA1Servant adderRef = new CA1Servant();

      //Connect the adder to the orb
      orb.connect(adderRef);
			System.out.println("Orb connected." + orb);

      //Locate Naming Service
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			System.out.println("Found NameService.");

			NamingContext rootCtx = NamingContextHelper.narrow(objRef);

      //Add context 1 to root
			nc[0] = new NameComponent("Context 1", "Context");
			NamingContext Ctx1 = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context 1' added to Name Space.");

      //Add context 2 to root
			nc[0] = new NameComponent("Context 2", "Context");
			NamingContext Ctx2 = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context 2' added to Name Space.");

      //Add context 3 to root
			nc[0] = new NameComponent("Context 3", "Context");
			NamingContext Ctx3 = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context 3' added to Name Space.");

      //Add Sub-Context 2 to Context 1
			nc[0] = new NameComponent("Sub - Context 2", "Context");
			NamingContext SubCtx2 = Ctx1.bind_new_context(nc);
			System.out.println("Context 'Sub - Context 2' added to Context 1.");

      //Add Object 1 to Context 1
      nc[0] = new NameComponent("Object1", "Object");
			Ctx1.rebind(nc, Ctx1);
			System.out.println("Object 'Object1' added to Context 1.");

      //Add object 2 to Sub Context 2
      SubCtx2.rebind(nc, SubCtx2);
			System.out.println("Object 'Object1' added to Sub Context 2.");

      //Add object 2 to Context 2
      nc[0] = new NameComponent("Object2", "Object");
			Ctx2.rebind(nc, Ctx2);
			System.out.println("Object 'Object2' added to Context 2.");

      //Add Sub-Context 1 to Context 2
			nc[0] = new NameComponent("Sub - Context 1", "Context");
			NamingContext SubCtx1 = Ctx2.bind_new_context(nc);
			System.out.println("Context 'Sub - Context 1' added to Context 2.");

      //Add object 3 to Sub-Context 1
      nc[0] = new NameComponent("Object3", "Object");
			SubCtx1.rebind(nc, SubCtx1);
			System.out.println("Object 'Object3' added to Sub-Context 2.");

      //Add object 4 to Context 2
      nc[0] = new NameComponent("Object4", "Object");
			// Ctx2.rebind(nc, Ctx2);
      //Bind the adderRef to Object 4
      Ctx2.rebind(nc, adderRef);
			System.out.println("Object 'Object4' added to Context 2.");


      //Add object 5 to Context 3
      nc[0] = new NameComponent("Object5", "Object");
      Ctx3.rebind(nc, Ctx3);
      System.out.println("Object 'Object5' added to Context 3.");

      //Run the orb
      orb.run();

    } catch (Exception e) {
			System.err.println("ERROR: "+e);
			e.printStackTrace(System.out);
		}
  }
}
