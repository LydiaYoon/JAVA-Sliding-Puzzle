import java.awt.*;
import javax.swing.*;

public class LabelThread extends JLabel implements Runnable
{
	private int nMin, nSec; // �а� �ʰ��� ���� ��Ʈ�� ���� (�׳� �����ϰ� ��Ʈ������ 0~59�� �����ؼ� ����)
	private Thread timeThread; // Ÿ�̸Ӹ� ���� thread ���� 
	
	// constructor
	public LabelThread() // �ƹ� �Ķ���� ���� �׳� ���� 
	{
		initData(); // ������ �ʱ�ȭ 
	}
	
	public LabelThread(String str)
	{
		super(str); // JLabel�� ���� string���� ����
		initData(); // ������ �ʱ�ȭ 
	}
	
	public void initData() // �����͸� �ʱ�ȭ ��Ű�� ����
	{
		nMin = 0; // �а��� 0���� ����
		nSec = 0; // �ʰ��� 0���� ����
		timeThread = null; // Ÿ�̸� thread�� null�� ����
	}
	
	public int getMin() { return nMin; } // �ٸ� Ŭ�������� min ���� ������ �� �ְ� �ϴ� �޼ҵ� 
	public int getSec() { return nSec; } // �ٸ� Ŭ�������� sec ���� ������ �� �ְ� �ϴ� �޼ҵ� 
	public void setMin(int m) { nMin = m; } // �ٸ� Ŭ�������� min ���� ������ �� �ִ� �޼ҵ�
	public void setSec(int s) { nSec = s; } // �ٸ� Ŭ�������� sec ���� ������ �� �ִ� �޼ҵ� 
	
	public void start()
	{
		if (timeThread == null) // thread�� ���� ���°�� 
    		timeThread = new Thread(this); // ���� ����
    	timeThread.start(); // thread�� start
	} // start()
	public void stop()
	{
		if (timeThread != null) // thread�� �ִ� ���
			timeThread.stop(); // thrdad�� stop
	} // stop()
	public void interrupt()
	{
		if (timeThread != null) // thread�� �ִ� ��� 
		{
			nMin = 0; // �а��� 0���� ����
			nSec = 0; // �ʰ��� 0���� ���� 
			timeThread.interrupt(); // thread�� ����
		}
	} // interrupt()
	
	
	public void run()
	{
		// Exception Handling
		try
		{
			while(true)
			{
				nSec++; // sec�� 1 ����
				if (nSec == 60) // �ʰ� 60�� �Ǹ� 
				{
					nMin++; // min�� 1 ���� 
            		nSec=0; // �ٽ� sec�� 0���� ����
				}
				setText(String.format("%02d:%02d", nMin, nSec)); // label�� �ؽ�Ʈ�� �ٲ� ��,�ʷ� �ٽ� �����Ѵ�.
				timeThread.sleep(1000); // 1�ʰ������� ����
				if (nMin == 99) // Ȥ�� 99�� �Ѿ ��� ���Ƴ���
					break;
			}
			
		}
		catch (Exception e) {}
	} // run()
	
} // LabelThread class