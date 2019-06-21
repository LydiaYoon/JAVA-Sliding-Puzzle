import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class BgmThread extends Thread
{
	private String path;
	private Player p = null;
	private boolean isBGMPlaying = false;
	
	public BgmThread(String newPath)
	{
		path = newPath;
	}
	
	public void run()
	{
		if(!isBGMPlaying)
		{
			try
			{
				isBGMPlaying = true;
				if(p == null)
					p = new Player(new FileInputStream(path));

					
				
				System.out.println("p.play()");
				p.play();
				
				isBGMPlaying = false;
				System.out.println("job done: " + isBGMPlaying);
				Stop();
				
				if(p == null)
					System.out.println("p is null");
			}
			catch(Exception e){}
		}
	}
	
	public void Stop()
	{
		// �����ϰ� ���� ������ ���°� TERMINATED�� ó����
		if(p != null)
		{
			p.close();
			p = null;
		}
	}
}