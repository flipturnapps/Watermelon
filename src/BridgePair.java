import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BridgePair implements Runnable
{
	private static final int BUFFER_SIZE = 1024;
	private InputStream inStream;
	private OutputStream outStream;
	private boolean open;
	public BridgePair(InputStream inStream, OutputStream outStream) 
	{
		super();
		this.inStream = inStream;
		this.outStream = outStream;
		this.open = true;
		new Thread(this).start();
	}
	@Override
	public void run() 
	{
		byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		while(this.open)
		{
			try
			{
				read = this.inStream.read(buffer);
				if(read != -1)
					this.outStream.write(buffer, 0, read);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		try {
			this.inStream.close();
			this.outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
