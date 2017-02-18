import java.io.*;
import org.omg.CORBA.*;
import SampleCA1.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.Properties;

public class SampleCA1Server{

	public static void main (String args[]) {
		try{
			Properties props = new Properties(); props.put("org.omg.CORBA.ORBInitialPort", "49000");
			ORB orb = ORB.init(args, props);

			NameComponent nc[] = new NameComponent[1];

			SampleCA1Servant helloRef = new SampleCA1Servant();

			//connecting the servant to the orb
			orb.connect(helloRef);
			System.out.println("Orb connected." + orb);

			//Locate Naming Service
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			System.out.println("Found NameService.");

			NamingContext rootCtx = NamingContextHelper.narrow(objRef);

      //Add context 1 to root
			nc[0] = new NameComponent("Context 1", "Context");
			NamingContext Ctx1 = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context 1' added to Name Space.");


      //Add Context 2 to root
      nc[0] = new NameComponent("Context 2", "Context");
			NamingContext Ctx2 = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context 2' added to Name Space.");

      //Add Object 2 to Context 2
			nc[0] = new NameComponent("Object 2", "Object");
			//NameComponent path[] = {nc};
			//Binding the name to an object that is stored in the Naming Context
			Ctx2.rebind(nc, helloRef);
			System.out.println("Object 'Object 2' added to Hello Context.");

      //Add context 2 to context 1
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

			// wait for invocations from clients
			orb.run();

		} catch (Exception e) {
			System.err.println("Error: "+e);
			e.printStackTrace(System.out);
		}

	}
}
