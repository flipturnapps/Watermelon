import java.io.IOException;

public class Start {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
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
