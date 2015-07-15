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
			this.mainSocket = this.accept();
			this.writer = new PrintWriter(mainSocket.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(this.accept)
		{
			Socket socket = null;
			try {
				socket = this.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pairs.add(new BridgePairPair(socket, this.mServer.getSockets().get(this.pairs.size())));
		}	
	}
	public void tellMakeNew() 
	{
		if(this.writer != null)
		{
			this.writer.println("add");
			this.writer.flush();
		}
	}
}
