import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PrimaryPanel extends JPanel // JPanel�� ��ӹ޴� PrimaryPanel Ŭ���� ����
{
	private JPanel gamePanel, menuPanel; // ���� ���Ӻκа� ������ �޴��κ� �г� 
	private JPanel timerPanel; // Ÿ�̸��� 'timer'��� ���ڿ� �ʽð踦 ���� �г� 
	private JLabel lblTitle; // Ÿ�̸��� 'timer'��� ���ڸ� ǥ���� ���̺� 
	private LabelThread lblTimer; // Ÿ�̸��� �ʽð踦 ������ LabelThread Ŭ���� ��ü
	private ViewPanel view; // ���Ӻκ��� �ǹ��ϴ� ViewPanel Ŭ���� ��ü
	
	private JPanel levelPanel, imagePanel; // ������ �̹����� ���� ���̺�� ��ư�� ���� �г�
	private JLabel lblLevel, lblImage; // ������ �̹����� ���� ���̺� (����)
	private JButton[] btnLevel, btnImage; // ������ �̹����� ������ ��ư�� ���� ��ư �迭 : �Ȱ����� �������� �־ �迭�� ���� 
	private int curLevel, curImage; // ���� ������ �̹��� ���� �����ϱ����� ������ ����
	
	private JPanel scorePanel; // �ְ����� �κ��� �����ֱ� ���� �г�
	private JLabel lblBest; // �ְ����� �κ��� �̹����� �����ֱ� ���� ���̺�
	private JLabel lblScore; // �ְ������κ� �ϴ��� �ְ����� ��, �ʸ� �����ֱ� ���� ���̺�
	private int bestMin[], bestSec[]; // �ְ����� ���� �����ϱ� ���� ��Ʈ�� �迭 : ���� �������� �����ϱ� ���� �迭�� ����
	
	private ImageButton btnRetry, btnExit; // �絵���� ������ ��ư�� ���� ImageButton Ŭ���� ��ü
	private JLabel lblRetry, lblExit; // �絵���� ������ ��ư�� �̹����� �����ֱ� ���� ���̺� 
	
	private boolean bPlay; // ������ ���۰� ���Ḧ Ȯ�� �ϱ� ���� ���� 
	
	//bgm entry	
	private ImageButton btnBgm; // ������� �Ѱ���� ����� ���� ImageButton Ŭ���� ��ü 
	private String bgmPath; //music path
	private int count; //music play check count if count%2==1 play else stop
	private BgmThread mThread; // ������� ������ ���� bgmThread Ŭ���� ��ü
	private boolean isBGMPlaying; // ������ ���� ����������� �Ǵ��ϱ� ���� boolean�� ����

	private BtnActionListener btnActionL; // ��ư�� ���� ������ Ȯ���ϴ� �׼Ǹ�����
	private BtnMouseListener btnMouseL;	 // ��ư�� ���� ���콺 ������ Ȯ���ϴ� ���콺 ������ 
	
	public PrimaryPanel() // constructor
	{
		setPreferredSize(new Dimension(650,500)); // PrimaryPanel�� ũ�⸦ ���μ��� 650*550���� ����
		setBackground(Color.black); // PrimaryPanel�� ����� ���������� ���� : �ȿ� ������ �гε��� �� �������� Ȯ���ϱ� ���� ��������
		setLayout(null); // PrimaryPanel�� ��ġ�����ڸ� ������� �������� ����
		
		bPlay = false; // ���� ����,���� Ȯ�� ������ ������·� ����
		
		btnActionL = new BtnActionListener(); // ��ư �׼� �����ʸ� ���� 
		btnMouseL = new BtnMouseListener(); // ��ư ���콺 �����ʸ� ����
	
	
		
		gamePanel = new JPanel(); // ���Ӻκ��� ���� gamePanel�� ����
		gamePanel.setBounds(0, 0, 430, 500); // ��ǥ�� ũ�� ���� 
		gamePanel.setBackground(MyConstants.YELLOW); // ���� ���� 
		gamePanel.setLayout(null); // ��ġ������ ������� ����
		add(gamePanel); // primaryPanel�� gamePanel�� add

		timerPanel = new JPanel(); // Ÿ�̸Ӹ� ���� timerPanel�� ����
		timerPanel.setBounds(25, 30, 380, 45); // ��ǥ�� ũ�� ���� 
		timerPanel.setBackground(MyConstants.YELLOW); // ���� ����
		timerPanel.setLayout(null); // ��ġ������ ������� ����
		gamePanel.add(timerPanel);// gamePanel�� timerPanel�� timerPanel�� add
		
		lblTitle = new JLabel("TIME"); // lblTitle�� "TIME"�� ���� JLabel�� ����
		lblTitle.setBounds(0,0,170,45); // ��ǥ�� ũ�� ���� 
		lblTitle.setForeground(MyConstants.DARK); // ����� ����
		lblTitle.setFont(MyConstants.TIME_FONT); // ��Ʈ ���� 
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT); // ������ ���� 
		timerPanel.add(lblTitle); // timerPanel�� lblTitle�� add
		
		lblTimer = new LabelThread("00:00"); // lblTimer�� "00:00"�� ���� JLabel�� ����
		lblTimer.setBounds(170,0,210,45); // ��ǥ�� ũ�� ���� 
		lblTimer.setForeground(MyConstants.RED); // ����� ���� 
		lblTimer.setFont(MyConstants.TIME_FONT); // ��Ʈ ���� 
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER); // ��� ���� 
		timerPanel.add(lblTimer); // timerPanel�� lblTimer�� add
		
		view = new ViewPanel(this); // View�� PrimaryPanel�� �Ķ���ͷ� �޴� ViewPanel�� ���� : PrimaryPanel�� ���� ���۰� ���Ḧ �� �� �ְ� �Ѵ� 
		view.setBounds(25, 95, 380, 380); // ��ǥ�� ũ�� ���� 
		gamePanel.add(view); // gamePanel�� view�� add
		
		curLevel = view.getLevel(); // ���� ���� ������ view�� get �޼ҵ带 �̿��� ������ 
		curImage = view.getImage(); // ���� �̹��� ������ view�� get �޼ҵ带 �̿��� ������ 
		




		//////////////////////////////



	
		menuPanel = new JPanel(); // �޴��κ��� ���� menuPanel ����
		menuPanel.setBounds(430, 0, 220, 500); // ��ǥ�� ũ�� ���� 
		menuPanel.setBackground(MyConstants.DARK); // ���� ���� 
		menuPanel.setLayout(null); // ��ġ������ ������� ���� 
		add(menuPanel); // primaryPanel�� menuPanel�� add 
		
		levelPanel = new JPanel(); // ������ ���� ���̺�� ��ư�� ���� �г��� ����
		levelPanel.setBounds(20, 65, 180, 16); // ��ǥ�� ũ�� ���� 
		levelPanel.setBackground(MyConstants.DARK); // ���� ����
		levelPanel.setLayout(null); // ��ġ������ ������� ���� 
		menuPanel.add(levelPanel); // menuPanel�� levelPanel�� add
		
		lblLevel = new JLabel("Level"); // lblLevel�� "Level"�� ���� JLabel�� ����
		lblLevel.setBounds(0, 0, 50, 16); // ��ǥ�� ũ�� ���� 
		lblLevel.setBackground(MyConstants.DARK); // ���� ���� 
		lblLevel.setForeground(Color.white); // ����� ���� 
		lblLevel.setFont(MyConstants.MENU_FONT); // ��Ʈ ���� 
		levelPanel.add(lblLevel); // levelPanel�� lblLevel�� add
		
		btnLevel = new JButton[MyConstants.LEVEL]; // btnLevel�� LEVEL���� JButton �迭�� ���� : LEVEL = 3;
		for (int i=0; i<MyConstants.LEVEL; i++) // LEVEL ������ŭ �ݺ�
		{
			btnLevel[i] = new JButton(MyConstants.BTN_LEVEL[i]); // �ε����� ���� �ȿ� �� �ؽ�Ʈ�� ���� : BTN_LEVEL = {"3x3", "4x4", "5x5"};
			btnLevel[i].setBounds(70+(i*38),0,32,16); // ��ǥ�� ũ�� ���� 
			btnLevel[i].setBackground(MyConstants.BROWN); // ���� ���� 
			btnLevel[i].setForeground(Color.white); // ����� ����
			btnLevel[i].setFont(MyConstants.MENU_FONT); // ��Ʈ ����
		
			if (curLevel == i) // ���� ���������� �ε����� ��ġ�ϴ� ��ư�� ������ RED�� ����
				btnLevel[i].setBackground(MyConstants.RED);
			
			btnLevel[i].setHorizontalAlignment(SwingConstants.CENTER); // ��� ����
			btnLevel[i].setBorderPainted(false); // ��ư ��輱 ����
			btnLevel[i].setFocusPainted(false); // ��Ŀ��(�����ߴ� ��ư ǥ��) ����
			btnLevel[i].setMargin(new Insets(0,0,0,0)); // �����¿� ���� 0���� ����
			
			btnLevel[i].addActionListener(btnActionL); // ��ư�� �׼Ǹ����� ����
			btnLevel[i].addMouseListener(btnMouseL); // ��ư�� ���콺������ ����
			levelPanel.add(btnLevel[i]); // levelPanel�� �ش� �ε����� btnLevel�� add
		}

		
		imagePanel = new JPanel(); // imagePanel�� JPanel�� ����
		imagePanel.setBounds(20, 90, 180, 16); // ��ǥ�� ũ�� ���� 
		imagePanel.setBackground(MyConstants.DARK); // ���� ����
		imagePanel.setLayout(null); // ��ġ������ ��� ���� 
		menuPanel.add(imagePanel); // menuPanel�� imagePanel�� add
		
		
		lblImage = new JLabel("Image");	// lblimage�� "Image"�� ���� JLabel�� ����
		lblImage.setBounds(0, 0, 50, 16); // ��ǥ�� ũ�� ���� 
		lblImage.setBackground(MyConstants.DARK); // ���� ����
		lblImage.setForeground(Color.white); // ����� ���� 
		lblImage.setFont(MyConstants.MENU_FONT); // ��Ʈ ���� 
		imagePanel.add(lblImage); // levelPanel�� lblImage�� add
		
		btnImage = new JButton[MyConstants.IMAGE]; // btnImage�� IMAGE���� JButton �迭�� ���� : IMAGE = 5;
		for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE ������ŭ �ݺ� 
		{
			btnImage[i] = new JButton(MyConstants.BTN_IMAGE[i]); // �ε����� ���� �ȿ� �� �ؽ�Ʈ�� ���� : BTN_IMAGE = {"1", "2", "3", "4", "5"};
			btnImage[i].setBounds(70+(i*23),0,16,16); // ��ǥ�� ũ�� ���� 
			btnImage[i].setBackground(MyConstants.BROWN); // ���� ����
			btnImage[i].setForeground(Color.white); // ����� ���� 
			btnImage[i].setFont(MyConstants.MENU_FONT); // ��Ʈ ���� 
			
			if (curImage == i) // ���� �̹��� ������ �ε����� ��ġ�ϴ� ��ư�� ������ RED�� ����
				btnImage[i].setBackground(MyConstants.RED);
			
			btnImage[i].setHorizontalAlignment(SwingConstants.CENTER); // ��� ����
			btnImage[i].setBorderPainted(false); // ��ư ��輱 ����
			btnImage[i].setFocusPainted(false); // ��Ŀ��(�����ߴ� ��ư ǥ��) ����
			btnImage[i].setMargin(new Insets(0,0,0,0)); // �����¿� ���� 0���� ����
			
			btnImage[i].setEnabled(true); // ��ư Ȱ��ȭ
			btnImage[i].addActionListener(btnActionL); // ��ư�� �׼� ������ ����
			btnImage[i].addMouseListener(btnMouseL); // ��ư�� ���콺 ������ ����
			imagePanel.add(btnImage[i]); // imagePanel�� �ش� �ε����� btnImage�� add
		}

		bestMin = new int[MyConstants.LEVEL]; // bestScore�� ������ ����
		bestSec = new int[MyConstants.LEVEL]; // bestScore�� ������ ����
		
		for(int i=0; i<MyConstants.LEVEL; i++)
			bestMin[i] = bestSec[i] = 99; // �ְ����� �а� �ð��� 99�� �ʱ�ȭ : �� ó�� ���� ������ ������ �� �� �ֵ���
		
		scorePanel = new JPanel();  // scorePanel�� JPanel�� ����
		scorePanel.setBounds(20,128,180,74); // ��ǥ�� ũ�� ���� 
		scorePanel.setBackground(MyConstants.DARK); // ���� ���� 
		scorePanel.setLayout(null); // ��ġ������ ��� ���� 
		menuPanel.add(scorePanel); // menuPanel�� scorePanel�� add
		
		lblScore = new JLabel("00:00"); // lblScore�� "00:00"�� ���� JLabel�� ����
		lblScore.setBounds(64, 35, 100, 24); // ��ǥ�� ũ�� ���� 
		lblScore.setForeground(Color.white); // ����� ����
		lblScore.setFont(MyConstants.SCORE_FONT); // ��Ʈ ���� 
		scorePanel.add(lblScore); // scorePanel�� lblScore�� add 
	    
	    lblBest = new JLabel(new ImageIcon("puzzle\\icon_bestscore.png")); // lblBest�� ImageIcon�� ���� JLabel�� ����
		lblBest.setBounds(0,0,180,74); // ��ǥ�� ũ�� ���� 
	    scorePanel.add(lblBest); // scorePanel�� lblBest�� add


	    btnRetry = new ImageButton(); // btnRetry�� ImageButton���� ����
	    btnRetry.setImageIcon(MyConstants.BTN_EXIT); // btnRetry�� �̹����� BTN_EXIT�� ���� : ��ư�� ��� �̹����� 
	    btnRetry.setBounds(20, 380, 180, 45); // ��ǥ�� ũ�� ���� 
	    btnRetry.setBackground(MyConstants.DARK); // ���� ����
	    btnRetry.setLayout(null); // ��ġ������ ��� ���� 
	    btnRetry.setHorizontalAlignment(SwingConstants.CENTER); // ��� ���� 
		btnRetry.setBorderPainted(false); // ��ư ��輱 ����
		btnRetry.setFocusPainted(false); // ��Ŀ��(�����ߴ� ��ư ǥ��) ����
		btnRetry.addActionListener(btnActionL); // �̹�����ư�� �׼Ǹ����� ���� 
		btnRetry.addMouseListener(btnMouseL); // �̹�����ư�� ���콺������ ����
	    menuPanel.add(btnRetry); // menuPanel�� btnRetry�� add
	    
	    lblRetry = new JLabel("", MyConstants.TXT_RETRY, SwingConstants.CENTER); // lblRetry�� ImageIcon�� ���� JLabel�� ����
	    lblRetry.setBounds(40, 10, 100, 25); // ��ǥ�� ũ�� ���� 
	    btnRetry.add(lblRetry); // btnRetry�� lblRetry�� add
	    
	    
	    btnExit = new ImageButton(); // btnExit�� ImageButton���� ����
	    btnExit.setImageIcon(MyConstants.BTN_EXIT); // btnExit�� �̹����� BTN_EXIT�� ���� : ��ư�� ��� �̹����� 
	    btnExit.setBounds(20, 435, 180, 45); // ��ǥ�� ũ�� ���� 
	    btnExit.setBackground(MyConstants.DARK); // ���� ����
	    btnExit.setLayout(null); // ��ġ������ ��� ���� 
	    btnExit.setHorizontalAlignment(SwingConstants.CENTER); // ��� ���� 
		btnExit.setBorderPainted(false); // ��ư ��輱 ����
		btnExit.setFocusPainted(false); // ��Ŀ��(�����ߴ� ��ư ǥ��) ���� 
		btnExit.addActionListener(btnActionL); // �̺�����ư�� �׼Ǹ����� ���� 
		btnExit.addMouseListener(btnMouseL); // �̺�����ư�� ���콺������ ����
	    menuPanel.add(btnExit); // menuPanel�� btnExit�� add 
	    
	    lblExit = new JLabel("", MyConstants.TXT_EXIT, SwingConstants.CENTER); // lblExit�� ImageIcon�� ���� JLabel�� ���� 
	    lblExit.setBounds(40, 10, 100, 25); // ��ǥ�� ũ�� ����
	    btnExit.add(lblExit); // btnExit�� lblExit�� add 
	    
	    
	    
	    
		//////////////////////////////
		
		
		
		
		bgmPath = new String("song/Reminiscence.mp3"); // bgmPath�� �ش� �ּҸ� ���� String���� ����
		count = 0; // count������ 0���� �ʱ�ȭ
		mThread = null; // mThread�� null�� �ʱ�ȭ 
		isBGMPlaying = false; //��������� ������� �ƴ����� �ʱ�ȭ 

		/*btnbgm setting*/
		btnBgm = new ImageButton(MyConstants.BGM_OFF); // btnBgm�� ImageIcon�� BGM_OFF�� ���� ImageButton���� ����
	    btnBgm.setImageIcon(MyConstants.BGM_OFF); // btnBgm�� �̹����� BGM_OFF�� ����
	    btnBgm.setBounds(20, 10, 180, 45); // ��ǥ�� ũ�� ���� 
	    btnBgm.setBackground(MyConstants.DARK); // ���� ����
	    btnBgm.setLayout(null); // ��ġ������ ��� ����
	    btnBgm.setHorizontalAlignment(SwingConstants.CENTER); // ��� ���� : ����
	    btnBgm.setVerticalAlignment(SwingConstants.CENTER); // ������� : ���� 
		btnBgm.setBorderPainted(false); // ��ư ��輱 ����
		btnBgm.setFocusPainted(false); // ��Ŀ��(�����ߴ� ��ư ǥ��) ����
		btnBgm.addActionListener(btnActionL); // �̹�����ư�� �׼� ������ ����
	    menuPanel.add(btnBgm); // menuPanel�� btnBgm�� add 

	    
	
	} // PrimaryPanel()	







	public boolean getPlay() { return bPlay; } // ���� �÷��� ���°� ��� �ٸ� Ŭ�������� ������ �� �ִ� �޼ҵ�
	public void setPlay(boolean bP) // �ٸ� Ŭ�������� PrimaryPanel�� bPlay���¸� �����ų �� �ִ� �޼ҵ�
	{
		bPlay = bP; // �޾ƿ� ���� bPlay�� ����
		
		if (bPlay == true) // ���� �÷��� ���°� ������ : ������ ���۵Ǹ�
		{
			lblTimer.setMin(0); // lblTimer�� �а��� 0���� ����
			lblTimer.setSec(0); // lblTiemr�� �ʰ��� 0���� ����
			lblTimer.start(); // lblTimer�� thread�� start
		}
		if (bPlay == false) // ���� �÷��� ���°� �ƴϸ� : ������ ����Ǹ�
		{
			lblTimer.stop(); // lblTimer�� thread�� stop
			bestScore(); // �ְ����� ����ϴ� �޼ҵ带 ȣ��
			for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE�� ������ŭ �ݺ� : IMAGE = 5;
			{
				btnImage[i].setEnabled(false); // ��ư ��Ȱ��ȭ 
				btnImage[i].setBackground(MyConstants.DARK_BROWN); // ���� ����
				btnImage[i].setForeground(MyConstants.LIGHT_BROWN); // ����� ���� 
			}
		}
	}
	
	public void playInit() //lblTimer�� thread�� �ʱ�ȭ ��Ű�� �޼ҵ�
	{
		lblTimer.setVisible(false); // lblTimer�� �Ⱥ��̰� ���� 
		lblTimer.interrupt(); // lblTimer�� thread�� interrupt
		
		lblTimer = new LabelThread("00:00"); // lblTimer�� "00:00"�� ���� LabelThread�� ���� ���� 
		lblTimer.setBounds(170,0,210,45); // ��ǥ�� ũ�� ���� 
		lblTimer.setForeground(MyConstants.RED); // ����� ���� 
		lblTimer.setFont(MyConstants.TIME_FONT); // ��Ʈ ���� 
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER); // ��� ���� 
		timerPanel.add(lblTimer); // timerPanel�� lblTimer�� add
		
		lblTimer.setMin(0); // lblTimer�� �а��� 0���� ����
		lblTimer.setSec(0); // lblTimer�� �ʰ��� 0���� ���� 
		bPlay = false; // ���� �÷��� ���¸� ������·� ���� 
		
		for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE�� ������ŭ �ݺ� : IMAGE = 5;
		{
			btnImage[i].setEnabled(true); // ��ư Ȱ��ȭ
			btnImage[i].setBackground(MyConstants.BROWN); // ���� ����
			btnImage[i].setForeground(Color.white); // ����� ���� 
			if (curImage == i) // ���� �̹��� ������ �ε����� ��ġ�ϴ� ��ư�� ������ RED�� ����
				btnImage[i].setBackground(MyConstants.RED);
		}

	}

	public void bestScore() // ������ ����Ǹ� �ְ������� ����ϴ� �޼ҵ�
	{
		int min, sec; // ��, �� ���� ���� ��Ʈ�� ����
		min = lblTimer.getMin(); // lblTimer���� �а��� �����´�
		sec = lblTimer.getSec(); // lblTimer���� �ʰ��� �����´� 

		if(bestMin[curLevel] > min) // ���� ���� ���� �ְ��������� ������
		{ // ���� ������ �ʴ� ���͵� ���� 
			bestMin[curLevel] = min; // ���� �ְ������� �а��� ���� 
		    bestSec[curLevel] = sec; // ���� �ְ������� �ʰ��� ����
		    lblScore.setText(String.format("%02d:%02d", min, sec)); // lblScore�� �ٽ� ����
		    repaint(); // ȭ���� �ٽ� �׸�
		}
	    else if (bestMin[curLevel] == min) // ���� ���� ���� �ְ������� ������ 
	    { // �̶��� �ʸ� ���ؾ���
		    if(bestSec[curLevel] >= sec) // ���� �ʰ� ���� �ְ��������� �۰ų� ������ 
		    {
		    	bestSec[curLevel] = sec; // ���� �ְ������� �ʰ��� ����
		    	lblScore.setText(String.format("%02d:%02d", min, sec)); // lblScore�� �ٽ� ����
		    	repaint(); // ȭ���� �ٽ� �׸�
		    }
	    }
    } // bestscore() -> �ϴ� ���� ���ٸ� �� �� �� ���ؼ� �� �ٲٱ�
     
     
    public void sound(int count) //�뷡 �÷��� �Լ�
	{
		if(mThread == null || mThread.getState() == Thread.State.TERMINATED)  //if mThread is null or mThread is Finished
			mThread = new BgmThread(bgmPath);//mThread is going to set bgmPath(song/Reminiscence.mp3)
		
		if(!isBGMPlaying)//if is BGMPlaying is True
			mThread.start();//mThread.start() is working
		else//if is BGMPlaying is False
			mThread.Stop();//mThread.Stop() is working
			
		//System.out.println("State: " + mThread.getState()); //checking the state
		isBGMPlaying = !isBGMPlaying;//changing the data
	} 
  
  
  
  
  
  
  
  
  
  
  
     
	private class BtnActionListener implements ActionListener // ��ư�� ���� ������ Ȯ���ϴ� �׼Ǹ�����
	{
		public void actionPerformed(ActionEvent event) // �Է��� ������ ������ �� 
		{
			Object obj = event.getSource(); // � ��ư�̳� ��� �׼��� �Էµƴ��� obj�� ���� : Object�� �ֻ����� ��� �ڷḦ ���� �� ���� 

			if(obj == btnBgm && count%2==0)
			{
				count++;//count++
				btnBgm.setImageIcon(MyConstants.BGM_ON); //change image on
				sound(count); //ī��Ʈ�� ¦���� ���
			}
			else if(obj == btnBgm && count%2==1)
			{
				count++;//count++
				btnBgm.setImageIcon(MyConstants.BGM_OFF);//change image off
				sound(count); //ī��Ʈ�� ¦���� ���
			}
			
			
			
			if (obj == btnRetry) // �絵�� ��ư�� ������ ���
			{
				view.setLevel(curLevel); // ���� ������ ���� ������ ���� 
				for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE ������ŭ �ݺ� : IMAGE = 5;
				{
					btnImage[i].setEnabled(true); // ��ư Ȱ��ȭ
					btnImage[i].setBackground(MyConstants.BROWN); // ���� ���� 
					btnImage[i].setForeground(Color.white); // ����� ����
					
					if (curImage == i) // ���� ���� ������ �ε����� ��ġ�ϴ� ��ư�� ������ RED�� ����
						btnImage[i].setBackground(MyConstants.RED);
				}
				playInit(); // lblTimer�� thread �ʱ�ȭ 
			}
			else if (obj == btnExit) // ������ ��ư�� ������ ��� 
				System.exit(0); // �ý��� ���� 
						
			
			for (int i=0; i<MyConstants.LEVEL; i++) // LEVEL ���� ��ŭ �ݺ� : LEVEL = 3;
			{
				if (obj == btnLevel[i]) // i��° ������ ������ ���
				{
					btnLevel[curLevel].setBackground(MyConstants.BROWN); // ���� �������̴� ���� ������ �ٽ� �������� �ٲ� 
					curLevel = i; // ���� ���������� i���� ���� 
					btnLevel[i].setBackground(MyConstants.RED); // �ٲ� ���� ������ ������ ���������� �ٲ�
					view.setLevel(i); // ���� ������ i������ ����
					playInit(); // lblTimer�� thread �ʱ�ȭ
					if(bestMin[curLevel]!=99 && bestSec[curLevel]!=99) 
						lblScore.setText(String.format("%02d:%02d", bestMin[curLevel], bestSec[curLevel])); // �ְ������� ���̺� ǥ��
					else // ���� ��,�ʰ� 99�̸� �ʱⰪ�̹Ƿ� 00:00���� ǥ�� 
						lblScore.setText("00:00"); 
					break;
				}
			}
			
			for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE ���� ��ŭ �ݺ� : IMAGE = 5;
			{
				if (obj == btnImage[i]) // i��° �̹����� ������ ��� 
				{
					btnImage[curImage].setBackground(MyConstants.BROWN); // ���� �������̴� ���� �̹����� �ٽ� �������� �ٲ� 
					curImage = i; // ���� �̹��� ������ i���� ���� 
					btnImage[i].setBackground(MyConstants.RED); // �ٲ� ���� �̹����� ������ ���������� �ٲ�
					view.setImage(i); // ���� �̹����� i������ ����
					break;
				}
			}
			
		} // actionPerformed()
	} // LevelListener class
	
	
	
	private class BtnMouseListener implements MouseListener // ��ư�� ���� ���콺 ������ Ȯ���ϴ� ���콺 ������ 
	{
		public void mouseClicked(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) // ���콺�� ������ ������ ���
		{
			Object obj = event.getSource(); // � ��ư�̳� ��� �׼��� �Էµƴ��� obj�� ���� : Object�� �ֻ����� ��� �ڷḦ ���� �� ���� 
			
			if (obj == btnRetry) // ���콺�� �絵�� ��ư ������ ������ ��� 
				btnRetry.setImageIcon(MyConstants.BTN_ENTER); // �絵�� ��ư�� �̹����� BTN_ENTER�� �ٲ� : ��ư�� ��� �̹����� 
			else if (obj == btnExit) // ���콺�� ������ ��ư ������ ������ ��� 
				btnExit.setImageIcon(MyConstants.BTN_ENTER); // ������ ��ư�� �̹����� BTN_ENTER�� �ٲ� : ��ư�� ��� �̹����� 
			
			for (int i=0; i<MyConstants.LEVEL; i++) // LEVEL ������ŭ �ݺ� : LEVEL = 3;
			{
				if (obj == btnLevel[i]) // i��° ���� �ȿ� ������ ���
				{
					btnLevel[i].setBackground(MyConstants.LIGHT_BROWN); // ��ư�� ������ ���������� �ٲ�
					
					if (curLevel == i) // ���� ���� �ȿ� ������ ���� ���� ���������� ����
						btnLevel[i].setBackground(MyConstants.LIGHT_RED);
					break;
				}
			}
			
			for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE ������ŭ �ݺ� : IMAGE = 5;
			{
				if (obj == btnImage[i] && bPlay == true) // ������ �������̸� i���� �̹��� �ȿ� ������ ��� 
				{
					btnImage[i].setBackground(MyConstants.LIGHT_BROWN); // ��ư�� ������ ���������� �ٲ� 
					
					if (curImage == i) // ���� �̹��� �ȿ� ������ ��쿡�� ���� ���������� ����
						btnImage[i].setBackground(MyConstants.LIGHT_RED);
					break;
				}
			}

		} // mouseEntered() // ���콺�� ������ ������ ��� 
		public void mouseExited(MouseEvent event)
		{
			Object obj = event.getSource(); // � ��ư�̳� ��� �׼��� �Էµƴ��� obj�� ���� : Object�� �ֻ����� ��� �ڷḦ ���� �� ���� 
			
			if (obj == btnRetry) // ���콺�� �絵�� ��ư ������ ������ ��� 
				btnRetry.setImageIcon(MyConstants.BTN_EXIT); // �絵�� ��ư�� �̹����� BTN_EXIT �ٲ� : ��ư�� ��� �̹����� 
			else if (obj == btnExit) // ���콺�� �絵�� ��ư ������ ������ ��� 
				btnExit.setImageIcon(MyConstants.BTN_EXIT); // ������ ��ư�� �̹����� BTN_EXIT �ٲ� : ��ư�� ��� �̹����� 
			
			for (int i=0; i<MyConstants.LEVEL; i++) // LEVEL ������ŭ �ݺ� : LEVEL = 3;
			{
				if (obj == btnLevel[i]) // i��° ���� ������ ������ ���
				{
					btnLevel[i].setBackground(MyConstants.BROWN); // ��ư�� ������ ���������� �ٲ�
					
					if (curLevel == i) // ���� ���� �ȿ� ������ ���� ���� ���������� ����
						btnLevel[i].setBackground(MyConstants.RED);
					break;
				}
			}
			
			for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE ������ŭ �ݺ� : IMAGE = 5;
			{
				if (obj == btnImage[i] && bPlay == true) // ������ �������̸� i���� �̹��� ������ ������ ���
				{
					btnImage[i].setBackground(MyConstants.BROWN); // ��ư�� ������ ���������� �ٲ�
					
					if (curImage == i) // ���� �̹��� �ȿ� ������ ��쿡�� ���� ���������� ����
						btnImage[i].setBackground(MyConstants.RED);
					break;
				}
			}
		} // mouseExited()
		
	} // BtnMouseListener class
	

	
} // PrimaryPanel class
