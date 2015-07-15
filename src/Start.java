import java.io.IOException;

public class Start {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		if(args.length == 1) {
			if(args[0].equals("-h") || args[0].equals("--help")) {
				String white = "\033[0;97m";
				String reset = "\033[0m";
				System.out.println(white+"Watermelon help\n"+reset);
				System.out.println("To start a server:");
				System.out.println("    "+white+"`watermelon <ip> <port>`"+reset+"\n");
				System.out.println("To start a client:");
				System.out.println("    "+white+"`watermelon`"+reset);
				System.exit(1);
			}
		}
		if(args.length > 0)
		{
			WatermelonClient client = new WatermelonClient(args[0],Integer.parseInt(args[1]));
		}
		else
		{
			WatermelonMultiServer mServer = new WatermelonMultiServer();
			WatermelonSingleServer sServer = new WatermelonSingleServer(mServer);
		}
	}
}
