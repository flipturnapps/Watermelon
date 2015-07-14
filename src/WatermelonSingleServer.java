import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class WatermelonSingleServer extends ServerSocket implements Runnable
{
	public static final int PORT = 12349;
	private boolean accept;
	private ArrayList<BridgePairPair> pairs = new ArrayList<BridgePairPair>();
	private WatermelonMultiServer mServer;
	private Socket mainSocket;
	private PrintWriter writer;	
	public WatermelonSingleServer(WatermelonMultiServer mServer) throws IOException 
	{
		super(PORT);
		new Thread(this).run();
		this.mServer = mServer;
		mServer.setWss(this);
	}
	@Override
	public void run() 
	{
		try {
			mainSocket = this.accept();
			writer = new PrintWriter(mainSocket.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(accept)
		{
			Socket socket = null;
			try {
				socket = this.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int size = pairs.size();
			BridgePairPair pair = new BridgePairPair(socket,mServer.getSockets().get(size));
			pairs.add(pair);
		}	
	}
	public void tellMakeNew() 
	{
		if(writer != null)
		{
			writer.println("add");
			writer.flush();
		}
	}

	

}