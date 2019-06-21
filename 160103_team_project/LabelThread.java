import java.awt.*;
import javax.swing.*;

public class LabelThread extends JLabel implements Runnable
{
	private int nMin, nSec; // 분과 초값을 위한 인트형 변수 (그냥 간단하게 인트형으로 0~59로 생각해서 구현)
	private Thread timeThread; // 타이머를 위한 thread 변수 
	
	// constructor
	public LabelThread() // 아무 파라미터 없이 그냥 생성 
	{
		initData(); // 데이터 초기화 
	}
	
	public LabelThread(String str)
	{
		super(str); // JLabel을 받은 string으로 설정
		initData(); // 데이터 초기화 
	}
	
	public void initData() // 데이터를 초기화 시키는 변수
	{
		nMin = 0; // 분값을 0으로 저장
		nSec = 0; // 초값을 0으로 저장
		timeThread = null; // 타이머 thread를 null로 설정
	}
	
	public int getMin() { return nMin; } // 다른 클래스에서 min 값을 가져갈 수 있게 하는 메소드 
	public int getSec() { return nSec; } // 다른 클래스에서 sec 값을 가져갈 수 있게 하는 메소드 
	public void setMin(int m) { nMin = m; } // 다른 클래스에서 min 값을 설정할 수 있는 메소드
	public void setSec(int s) { nSec = s; } // 다른 클래스에서 sec 값을 설정할 수 있는 메소드 
	
	public void start()
	{
		if (timeThread == null) // thread가 아직 없는경우 
    		timeThread = new Thread(this); // 새로 생성
    	timeThread.start(); // thread를 start
	} // start()
	public void stop()
	{
		if (timeThread != null) // thread가 있는 경우
			timeThread.stop(); // thrdad를 stop
	} // stop()
	public void interrupt()
	{
		if (timeThread != null) // thread가 있는 경우 
		{
			nMin = 0; // 분값을 0으로 저장
			nSec = 0; // 초값을 0으로 저장 
			timeThread.interrupt(); // thread를 종료
		}
	} // interrupt()
	
	
	public void run()
	{
		// Exception Handling
		try
		{
			while(true)
			{
				nSec++; // sec를 1 증가
				if (nSec == 60) // 초가 60이 되면 
				{
					nMin++; // min을 1 증가 
            		nSec=0; // 다시 sec는 0으로 저장
				}
				setText(String.format("%02d:%02d", nMin, nSec)); // label의 텍스트를 바뀐 분,초로 다시 설정한다.
				timeThread.sleep(1000); // 1초간격으로 돌림
				if (nMin == 99) // 혹시 99분 넘어갈 까봐 막아놓음
					break;
			}
			
		}
		catch (Exception e) {}
	} // run()
	
} // LabelThread class