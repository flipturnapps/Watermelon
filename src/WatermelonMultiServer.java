import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class WatermelonMultiServer extends ServerSocket implements Runnable
{
	public static final int PORT = 12351;
	private boolean accept;
	private ArrayList<Socket> sockets = new ArrayList<Socket>();
	private WatermelonSingleServer wss;
	public WatermelonMultiServer() throws IOException 
	{
		super(PORT);
		accept = true;
		new Thread(this).run();
	}
	@Override
	public void run() 
	{
		while(accept)
		{
			Socket socket = null;
			try {
				socket = this.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(socket != null)
			{
				sockets.add(socket);
				wss.tellMakeNew();
			}
		}
	}
	public ArrayList<Socket> getSockets() {
		return sockets;
	}
	public void setSockets(ArrayList<Socket> sockets) {
		this.sockets = sockets;
	}
	public WatermelonSingleServer getWss() {
		return wss;
	}
	public void setWss(WatermelonSingleServer wss) {
		this.wss = wss;
	}

	

}
