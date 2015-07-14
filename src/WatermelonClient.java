import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class WatermelonClient extends Socket implements Runnable
{
	private boolean shouldRead;
	private BufferedReader reader;
	private int fowardPort;
	private String ip;
	private ArrayList<BridgePairPair> pairs = new ArrayList<BridgePairPair>();
	public WatermelonClient(String ip, int fowardPort) throws UnknownHostException, IOException 
	{
		super(ip,  WatermelonSingleServer.PORT);
		this.ip = ip;
		this.shouldRead = true;
		this.fowardPort = fowardPort;
		new Thread(this).start();
	}

	public void readMessage(String message)
	{
		if(message.equals("add"))
		{
			try
			{
				Socket socket = new Socket(ip,WatermelonSingleServer.PORT);
				Socket lSocket = new Socket("localhost",fowardPort);
				pairs.add(new BridgePairPair(socket,lSocket));
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}			
		}
	}
	@Override
	public void run() 
	{
		try {
			this.reader = new BufferedReader(new InputStreamReader(this.getInputStream()));
			//writer = new PrintWriter(this.getOutputStream());

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(this.shouldRead)
		{
			String message = null;
			try {
				message = this.reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(message != null)
			{
				this.readMessage(message);
			}
		}		
		try {
			this.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
