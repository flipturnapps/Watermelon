import java.net.Socket;

public class BridgePairPair
{
	private BridgePair pair1;
	private BridgePair pair2;
	private Socket s1;
	private Socket s2;
	public BridgePairPair(Socket s1, Socket s2) 
	{
		super();
		this.s1 = s1;
		this.s2 = s2;
		try
		{
			setPair1(new BridgePair(s1.getInputStream(),s2.getOutputStream()));
			setPair2(new BridgePair(s2.getInputStream(),s1.getOutputStream()));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public BridgePair getPair1() {
		return pair1;
	}
	public void setPair1(BridgePair pair1) {
		this.pair1 = pair1;
	}
	public BridgePair getPair2() {
		return pair2;
	}
	public void setPair2(BridgePair pair2) {
		this.pair2 = pair2;
	}
	public Socket getS1() {
		return s1;
	}

	public Socket getS2() {
		return s2;
	}




}
