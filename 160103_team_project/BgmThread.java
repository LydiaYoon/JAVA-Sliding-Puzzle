import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class BgmThread extends Thread
{
	private String path; //path 경로 설정
	private Player p = null; //Player p is null로 초기화 
	private boolean isBGMPlaying = false; //isBGMPlaying is False로 초기화 
	
	public BgmThread(String newPath)
	{
		path = newPath;
	}
	
	public void run()
	{
		if(!isBGMPlaying)//if bgmPlaying is True
		{
			try
			{
				isBGMPlaying = true; //if BGMPlaying is True
				if(p == null)//Player p is NULL
					p = new Player(new FileInputStream(path));//set location Player

				p.play();//set play
				
				isBGMPlaying = false;//isBGMPlay is false it is starts at play finished
				Stop();//Stop working
				
				if(p == null)//if p is null
					System.out.println("p is null");//print p is null
			}
			catch(Exception e){}
		}
	}
	
	public void Stop()
	{
		// 종료하고 나면 스레드 상태가 TERMINATED로 처리됨
		if(p != null)//if p is null
		{
			p.close(); //player p close
			p = null; // p is going to null
		}
	}
}