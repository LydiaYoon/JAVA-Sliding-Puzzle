import java.awt.*;
import javax.swing.*;

public class LabelThread extends JLabel implements Runnable
{
	private int nMin, nSec;
	private Thread timeThread;
	
	
	public LabelThread() 
	{
		initData();
	}
	
	public LabelThread(String str)
	{
		super(str);
		initData();
	}
	
	public void initData()
	{
		nMin = 0;
		nSec = 0;
		timeThread = null;
	}
	
	public int getMin() { return nMin; }
	public int getSec() { return nSec; }
	public void setMin(int m) { nMin = m; }
	public void setSec(int s) { nSec = s; }
	
	public void start()
	{
		if (timeThread == null)
    		timeThread = new Thread(this);
    	timeThread.start();
	} // start()
	public void stop()
	{
		if (timeThread != null)
			timeThread.stop();
	} // stop()
	public void interrupt()
	{
		if (timeThread != null)
		{
			nMin = 0;
			nSec = 0;
			timeThread.interrupt();
		}
	} // interrupt()
	
	
	public void run()
	{
		// Exception Handling
		try
		{
			while(true)
			{
				nSec++;
				if (nSec == 60)
				{
					nMin++;
            		nSec=0;
				}
				setText(String.format("%02d:%02d", nMin, nSec));
				timeThread.sleep(1000);
				if (nMin == 99)
					break;
			}
			
		}
		catch (Exception e) {}
	} // run()
	
} // LabelThread class