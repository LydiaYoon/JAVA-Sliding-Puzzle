import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PrimaryPanel extends JPanel
{
	private JPanel gamePanel, menuPanel;
	private JPanel timerPanel;
	private JLabel lblTitle;
	private LabelThread lblTimer;
	private ViewPanel view;
	
	private JPanel levelPanel, imagePanel;
	private JLabel lblLevel, lblImage;
	private JButton[] btnLevel, btnImage;
	private int curLevel, curImage;
	
	private JPanel scorePanel;
	private JLabel lblBest, lblScore;
	private int bestMin, bestSec;
	
	private ImageButton btnRetry, btnExit;
	private JLabel lblRetry, lblExit;

	private boolean bPlay; // 게임의 시작과 종료를 확인 하기 위한 변수 

	private BtnActionListener btnActionL;
	private BtnMouseListener btnMouseL;
	
	
	
	public PrimaryPanel()
	{
		setPreferredSize(new Dimension(650,500));
		setBackground(Color.black);
		setLayout(null);
		
		bPlay = false;
		
		btnActionL = new BtnActionListener();
		btnMouseL = new BtnMouseListener();
	
		gamePanel = new JPanel();
		gamePanel.setBounds(0, 0, 430, 500);
		gamePanel.setBackground(MyConstants.YELLOW);
		gamePanel.setLayout(null);
		add(gamePanel);

		timerPanel = new JPanel();
		timerPanel.setBounds(25, 30, 380, 45);
		timerPanel.setBackground(MyConstants.YELLOW);
		timerPanel.setLayout(null);
		gamePanel.add(timerPanel);
		
		lblTitle = new JLabel("TIME");
		lblTitle.setBounds(0,0,170,45);
		lblTitle.setFont(MyConstants.TIME_FONT);
		lblTitle.setForeground(MyConstants.DARK);
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		timerPanel.add(lblTitle);
		
		lblTimer = new LabelThread("00:00");
		lblTimer.setBounds(170,0,210,45);
		lblTimer.setFont(MyConstants.TIME_FONT);
		lblTimer.setForeground(MyConstants.RED);
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		timerPanel.add(lblTimer);
		
		view = new ViewPanel(this, lblTimer);
		view.setBounds(25, 95, 380, 380);
		gamePanel.add(view);
		
		curLevel = view.getLevel();
		curImage = view.getImage();

		//////////
	
		menuPanel = new JPanel();
		menuPanel.setBounds(430, 0, 220, 500);
		menuPanel.setBackground(MyConstants.DARK);
		menuPanel.setLayout(null);
		add(menuPanel);
		
		levelPanel = new JPanel();
		levelPanel.setBounds(20, 65, 180, 16);
		levelPanel.setBackground(MyConstants.DARK);
		levelPanel.setLayout(null);
		menuPanel.add(levelPanel);
		
		lblLevel = new JLabel("Level");
		lblLevel.setBounds(0, 0, 50, 16);
		lblLevel.setFont(MyConstants.MENU_FONT);
		lblLevel.setOpaque(true);
		lblLevel.setForeground(Color.white);
		lblLevel.setBackground(MyConstants.DARK);
		levelPanel.add(lblLevel);
		
		btnLevel = new JButton[MyConstants.LEVEL];
		for (int i=0; i<MyConstants.LEVEL; i++)
		{
			btnLevel[i] = new JButton(MyConstants.BTN_LEVEL[i]);
			btnLevel[i].setBounds(70+(i*38),0,32,16);
			btnLevel[i].setFont(MyConstants.MENU_FONT);
			btnLevel[i].setForeground(Color.white);
			btnLevel[i].setBackground(MyConstants.BROWN);
			
			if (curLevel == i)
				btnLevel[i].setBackground(MyConstants.RED);
			
			btnLevel[i].setHorizontalAlignment(SwingConstants.CENTER);
			btnLevel[i].setBorderPainted(false);
			btnLevel[i].setFocusPainted(false);
			btnLevel[i].setMargin(new Insets(0,0,0,0));
			
			btnLevel[i].addActionListener(btnActionL);
			btnLevel[i].addMouseListener(btnMouseL);
			levelPanel.add(btnLevel[i]);
		}

		
		imagePanel = new JPanel();
		imagePanel.setBounds(20, 90, 180, 16);
		imagePanel.setBackground(MyConstants.DARK);
		imagePanel.setLayout(null);
		menuPanel.add(imagePanel);
		
		lblImage = new JLabel("Image");
		lblImage.setBounds(0, 0, 50, 16);
		lblImage.setFont(MyConstants.MENU_FONT);
		lblImage.setOpaque(true);
		lblImage.setForeground(Color.white);
		lblImage.setBackground(MyConstants.DARK);
		imagePanel.add(lblImage);
		
		btnImage = new JButton[MyConstants.IMAGE];
		for (int i=0; i<MyConstants.IMAGE; i++)
		{
			btnImage[i] = new JButton(MyConstants.BTN_IMAGE[i]);
			btnImage[i].setBounds(70+(i*23),0,16,16);
			btnImage[i].setFont(MyConstants.MENU_FONT);
			btnImage[i].setForeground(Color.white);
			btnImage[i].setBackground(MyConstants.BROWN);
			
			if (curImage == i)
				btnImage[i].setBackground(MyConstants.RED);
			
			btnImage[i].setHorizontalAlignment(SwingConstants.CENTER);
			btnImage[i].setBorderPainted(false);
			btnImage[i].setFocusPainted(false);
			btnImage[i].setMargin(new Insets(0,0,0,0));
			btnImage[i].setEnabled(true);
			btnImage[i].addActionListener(btnActionL);
			btnImage[i].addMouseListener(btnMouseL);
			imagePanel.add(btnImage[i]);
		}
		
		
		
		//////////////////////////////
		
		bestMin = bestSec = 99;
		
		scorePanel = new JPanel();
		scorePanel.setBounds(20,128,180,74);
		scorePanel.setBackground(MyConstants.DARK);
		scorePanel.setLayout(null);
		menuPanel.add(scorePanel);
		
		lblScore = new JLabel("00:00");
		lblScore.setBounds(64, 35, 100, 24);
		lblScore.setFont(MyConstants.SCORE_FONT);
		lblScore.setForeground(Color.white);
		scorePanel.add(lblScore);
	    
	    lblBest = new JLabel(new ImageIcon("puzzle\\icon_bestscore.png")); // 그림
		lblBest.setBounds(0,0,180,74);
	    scorePanel.add(lblBest);
	    
	    //////////////////////////////
	    
	    btnRetry = new ImageButton();
	    btnRetry.setImageIcon(MyConstants.BTN_EXIT);
	    btnRetry.setBounds(20, 380, 180, 45);
	    btnRetry.setBackground(MyConstants.DARK);
	    btnRetry.setLayout(null);
	    btnRetry.setHorizontalAlignment(SwingConstants.CENTER);
		btnRetry.setBorderPainted(false);
		btnRetry.setFocusPainted(false);
		btnRetry.addActionListener(btnActionL);
		btnRetry.addMouseListener(btnMouseL);
	    menuPanel.add(btnRetry);
	    
	    lblRetry = new JLabel("", MyConstants.TXT_RETRY, SwingConstants.CENTER);
	    lblRetry.setBounds(40, 10, 100, 25);
	    btnRetry.add(lblRetry);
	    
	    
	    btnExit = new ImageButton();
	    btnExit.setImageIcon(MyConstants.BTN_EXIT);
	    btnExit.setBounds(20, 435, 180, 45);
	    btnExit.setBackground(MyConstants.DARK);
	    btnExit.setLayout(null);
	    btnExit.setHorizontalAlignment(SwingConstants.CENTER);
		btnExit.setBorderPainted(false);
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(btnActionL);
		btnExit.addMouseListener(btnMouseL);
	    menuPanel.add(btnExit);
	    
	    lblExit = new JLabel("", MyConstants.TXT_EXIT, SwingConstants.CENTER);
	    lblExit.setBounds(40, 10, 100, 25);
	    btnExit.add(lblExit);
	    
	    
		
	} // PrimaryPanel()	







	public boolean getPlay() { return bPlay; }
	public void setPlay(boolean bP)
	{
		bPlay = bP;
		
		if (bPlay == true)
		{
			lblTimer.setMin(0);
			lblTimer.setSec(0);
			lblTimer.start();
		}
		if (bPlay == false)
		{
			lblTimer.stop();
			bestScore();
			for (int i=0; i<MyConstants.IMAGE; i++)
			{
				btnImage[i].setEnabled(false);
				btnImage[i].setForeground(MyConstants.LIGHT_BROWN);
				btnImage[i].setBackground(MyConstants.DARK_BROWN);
			}
		}
	}
	
	public void playInit()
	{
		lblTimer.setVisible(false);
		lblTimer.interrupt();
		
		lblTimer = new LabelThread("00:00");
		lblTimer.setBounds(170,0,210,45);
		lblTimer.setFont(MyConstants.TIME_FONT);
		lblTimer.setForeground(MyConstants.RED);
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		timerPanel.add(lblTimer);
		
		lblTimer.setMin(0);
		lblTimer.setSec(0);
		//lblTimer.start();
		
		for (int i=0; i<MyConstants.IMAGE; i++)
		{
			btnImage[i].setEnabled(true);
			btnImage[i].setForeground(Color.white);
			btnImage[i].setBackground(MyConstants.BROWN);
			if (curImage == i)
				btnImage[i].setBackground(MyConstants.RED);
		}

	}


	public void bestScore()
	{
		int min, sec;
		min = lblTimer.getMin();
		sec = lblTimer.getSec();
		//System.out.println(""+String.format("%02d:%02d", min, sec));
		
		
		if(bestMin >= min)
		{
			bestMin = min;
		    if(bestSec > sec)
		    {
		    	bestSec = sec;
		    	lblScore.setText(String.format("%02d:%02d", min, sec));
		    	repaint();
		    }
	    }
    } // bestscore() -> 일단 분이 적다면 그 때 초 비교해서 초 바꾸기
     







	private class BtnActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object obj = event.getSource();
			
			
			if (obj == btnRetry)
			{
				view.setLevel(curLevel);
				for (int i=0; i<MyConstants.IMAGE; i++)
				{
					btnImage[i].setEnabled(true);
					btnImage[i].setForeground(Color.white);
					btnImage[i].setBackground(MyConstants.BROWN);
					if (curImage == i)
						btnImage[i].setBackground(MyConstants.RED);
				}
				playInit();
			}
			else if (obj == btnExit)
				System.exit(0);
						
			
			for (int i=0; i<MyConstants.LEVEL; i++)
			{
				if (obj == btnLevel[i])
				{
					btnLevel[curLevel].setBackground(MyConstants.BROWN);
					curLevel = i;
					btnLevel[i].setBackground(MyConstants.RED);
					view.setLevel(i);
					playInit();
					break;
				}
			}
			
			for (int i=0; i<MyConstants.IMAGE; i++)
			{
				if (obj == btnImage[i])
				{
					btnImage[curImage].setBackground(MyConstants.BROWN);
					curImage = i;
					btnImage[i].setBackground(MyConstants.RED);
					view.setImage(i);
					break;
				}
			}
			
		} // actionPerformed()
	} // LevelListener class
	
	
	
	private class BtnMouseListener implements MouseListener
	{
		public void mouseClicked(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
		public void mouseEntered(MouseEvent event)
		{
			Object obj = event.getSource();
			
			if (obj == btnRetry)
				btnRetry.setImageIcon(MyConstants.BTN_ENTER);
			else if (obj == btnExit)
				btnExit.setImageIcon(MyConstants.BTN_ENTER);
			
			for (int i=0; i<MyConstants.LEVEL; i++)
			{
				if (obj == btnLevel[i])
				{
					btnLevel[i].setBackground(MyConstants.LIGHT_BROWN);
					
					if (curLevel == i)
						btnLevel[i].setBackground(MyConstants.LIGHT_RED);
					break;
				}
			}
			
			for (int i=0; i<MyConstants.IMAGE; i++)
			{
				if (obj == btnImage[i] && bPlay == true)
				{
					btnImage[i].setBackground(MyConstants.LIGHT_BROWN);
					
					if (curImage == i)
						btnImage[i].setBackground(MyConstants.LIGHT_RED);
					break;
				}
			}

		} // mouseEntered()
		public void mouseExited(MouseEvent event)
		{
			Object obj = event.getSource();
			
			if (obj == btnRetry)
				btnRetry.setImageIcon(MyConstants.BTN_EXIT);
			else if (obj == btnExit)
				btnExit.setImageIcon(MyConstants.BTN_EXIT);
			
			for (int i=0; i<MyConstants.LEVEL; i++)
			{
				if (obj == btnLevel[i])
				{
					btnLevel[i].setBackground(MyConstants.BROWN);
					
					if (curLevel == i)
						btnLevel[i].setBackground(MyConstants.RED);
					break;
				}
			}
			
			for (int i=0; i<MyConstants.IMAGE; i++)
			{
				if (obj == btnImage[i] && bPlay == true)
				{
					btnImage[i].setBackground(MyConstants.BROWN);
					
					if (curImage == i)
						btnImage[i].setBackground(MyConstants.RED);
					break;
				}
			}
		} // mouseExited()
		
	} // BtnMouseListener class
	
} // PrimaryPanel class
