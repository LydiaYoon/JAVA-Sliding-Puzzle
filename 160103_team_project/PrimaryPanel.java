import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PrimaryPanel extends JPanel // JPanel을 상속받는 PrimaryPanel 클래스 생성
{
	private JPanel gamePanel, menuPanel; // 왼쪽 게임부분과 오른쪽 메뉴부분 패널 
	private JPanel timerPanel; // 타이머의 'timer'라는 글자와 초시계를 담을 패널 
	private JLabel lblTitle; // 타이머의 'timer'라는 글자를 표시할 레이블 
	private LabelThread lblTimer; // 타이머의 초시계를 보여줄 LabelThread 클래스 객체
	private ViewPanel view; // 게임부분을 의미하는 ViewPanel 클래스 객체
	
	private JPanel levelPanel, imagePanel; // 레벨과 이미지의 왼쪽 레이블과 버튼을 담을 패널
	private JLabel lblLevel, lblImage; // 레벨과 이미지의 왼쪽 레이블 (글자)
	private JButton[] btnLevel, btnImage; // 레벨과 이미지의 오른쪽 버튼을 위한 버튼 배열 : 똑같은게 여러개가 있어서 배열로 선언 
	private int curLevel, curImage; // 현재 레벨과 이미지 값을 저장하기위한 정수형 변수
	
	private JPanel scorePanel; // 최고점수 부분을 보여주기 위한 패널
	private JLabel lblBest; // 최고점수 부분의 이미지를 보여주기 위한 레이블
	private JLabel lblScore; // 최고점수부분 하단의 최고점수 분, 초를 보여주기 위한 레이블
	private int bestMin[], bestSec[]; // 최고점수 값을 저장하기 위한 인트형 배열 : 값을 레벨별로 저장하기 위해 배열로 선언
	
	private ImageButton btnRetry, btnExit; // 재도전과 나가기 버튼을 위한 ImageButton 클래스 객체
	private JLabel lblRetry, lblExit; // 재도전과 나가기 버튼의 이미지를 보여주기 위한 레이블 
	
	private boolean bPlay; // 게임의 시작과 종료를 확인 하기 위한 변수 
	
	//bgm entry	
	private ImageButton btnBgm; // 배경음악 켜고끄는 기능을 위한 ImageButton 클래스 객체 
	private String bgmPath; //music path
	private int count; //music play check count if count%2==1 play else stop
	private BgmThread mThread; // 배경음악 진행을 위한 bgmThread 클래스 객체
	private boolean isBGMPlaying; // 음악이 현재 재생중인지를 판단하기 위한 boolean형 변수

	private BtnActionListener btnActionL; // 버튼을 위해 응답을 확인하는 액션리스너
	private BtnMouseListener btnMouseL;	 // 버튼을 위해 마우스 동작을 확인하는 마우스 리스너 
	
	public PrimaryPanel() // constructor
	{
		setPreferredSize(new Dimension(650,500)); // PrimaryPanel의 크기를 가로세로 650*550으로 설정
		setBackground(Color.black); // PrimaryPanel의 배경을 검은색으로 설정 : 안에 생성될 패널들이 잘 나오는지 확인하기 위해 설정했음
		setLayout(null); // PrimaryPanel의 배치관리자를 사용하지 않음으로 설정
		
		bPlay = false; // 게임 시작,종료 확인 변수를 종료상태로 생성
		
		btnActionL = new BtnActionListener(); // 버튼 액션 리스너를 생성 
		btnMouseL = new BtnMouseListener(); // 버튼 마우스 리스너를 생성
	
	
		
		gamePanel = new JPanel(); // 게임부분을 담을 gamePanel을 생성
		gamePanel.setBounds(0, 0, 430, 500); // 좌표와 크기 설정 
		gamePanel.setBackground(MyConstants.YELLOW); // 배경색 설정 
		gamePanel.setLayout(null); // 배치관리자 사용하지 않음
		add(gamePanel); // primaryPanel에 gamePanel을 add

		timerPanel = new JPanel(); // 타이머를 담을 timerPanel을 생성
		timerPanel.setBounds(25, 30, 380, 45); // 좌표와 크기 설정 
		timerPanel.setBackground(MyConstants.YELLOW); // 배경색 설정
		timerPanel.setLayout(null); // 배치관리자 사용하지 않음
		gamePanel.add(timerPanel);// gamePanel에 timerPanel을 timerPanel을 add
		
		lblTitle = new JLabel("TIME"); // lblTitle을 "TIME"을 갖는 JLabel로 생성
		lblTitle.setBounds(0,0,170,45); // 좌표와 크기 설정 
		lblTitle.setForeground(MyConstants.DARK); // 전경색 설정
		lblTitle.setFont(MyConstants.TIME_FONT); // 폰트 설정 
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT); // 오른쪽 정렬 
		timerPanel.add(lblTitle); // timerPanel에 lblTitle을 add
		
		lblTimer = new LabelThread("00:00"); // lblTimer를 "00:00"을 갖는 JLabel로 생성
		lblTimer.setBounds(170,0,210,45); // 좌표와 크기 설정 
		lblTimer.setForeground(MyConstants.RED); // 전경색 설정 
		lblTimer.setFont(MyConstants.TIME_FONT); // 폰트 설정 
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬 
		timerPanel.add(lblTimer); // timerPanel에 lblTimer를 add
		
		view = new ViewPanel(this); // View를 PrimaryPanel을 파라미터로 받는 ViewPanel로 생성 : PrimaryPanel이 게임 시작과 종료를 알 수 있게 한다 
		view.setBounds(25, 95, 380, 380); // 좌표와 크기 설정 
		gamePanel.add(view); // gamePanel에 view를 add
		
		curLevel = view.getLevel(); // 현재 레벨 정보를 view의 get 메소드를 이용해 가져옴 
		curImage = view.getImage(); // 현재 이미지 정보를 view의 get 메소드를 이용해 가져옴 
		




		//////////////////////////////



	
		menuPanel = new JPanel(); // 메뉴부분을 담을 menuPanel 생성
		menuPanel.setBounds(430, 0, 220, 500); // 좌표와 크기 설정 
		menuPanel.setBackground(MyConstants.DARK); // 배경색 설정 
		menuPanel.setLayout(null); // 배치관리자 사용하지 않음 
		add(menuPanel); // primaryPanel에 menuPanel을 add 
		
		levelPanel = new JPanel(); // 레벨의 왼쪽 레이블과 버튼을 담을 패널을 생성
		levelPanel.setBounds(20, 65, 180, 16); // 좌표와 크기 설정 
		levelPanel.setBackground(MyConstants.DARK); // 배경색 설정
		levelPanel.setLayout(null); // 배치관리자 사용하지 않음 
		menuPanel.add(levelPanel); // menuPanel에 levelPanel을 add
		
		lblLevel = new JLabel("Level"); // lblLevel을 "Level"을 갖는 JLabel로 생성
		lblLevel.setBounds(0, 0, 50, 16); // 좌표와 크기 설정 
		lblLevel.setBackground(MyConstants.DARK); // 배경색 설정 
		lblLevel.setForeground(Color.white); // 전경색 설정 
		lblLevel.setFont(MyConstants.MENU_FONT); // 폰트 설정 
		levelPanel.add(lblLevel); // levelPanel에 lblLevel을 add
		
		btnLevel = new JButton[MyConstants.LEVEL]; // btnLevel을 LEVEL개의 JButton 배열로 생성 : LEVEL = 3;
		for (int i=0; i<MyConstants.LEVEL; i++) // LEVEL 갯수만큼 반복
		{
			btnLevel[i] = new JButton(MyConstants.BTN_LEVEL[i]); // 인덱스에 따라 안에 들어갈 텍스트를 설정 : BTN_LEVEL = {"3x3", "4x4", "5x5"};
			btnLevel[i].setBounds(70+(i*38),0,32,16); // 좌표와 크기 설정 
			btnLevel[i].setBackground(MyConstants.BROWN); // 배경색 설정 
			btnLevel[i].setForeground(Color.white); // 전경색 설정
			btnLevel[i].setFont(MyConstants.MENU_FONT); // 폰트 설정
		
			if (curLevel == i) // 현재 레벨정보와 인덱스가 일치하는 버튼은 배경색을 RED로 설정
				btnLevel[i].setBackground(MyConstants.RED);
			
			btnLevel[i].setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬
			btnLevel[i].setBorderPainted(false); // 버튼 경계선 제거
			btnLevel[i].setFocusPainted(false); // 포커스(선택했던 버튼 표시) 제거
			btnLevel[i].setMargin(new Insets(0,0,0,0)); // 상하좌우 여백 0으로 설정
			
			btnLevel[i].addActionListener(btnActionL); // 버튼에 액션리스너 설정
			btnLevel[i].addMouseListener(btnMouseL); // 버튼에 마우스리스너 설정
			levelPanel.add(btnLevel[i]); // levelPanel에 해당 인덱스의 btnLevel을 add
		}

		
		imagePanel = new JPanel(); // imagePanel을 JPanel로 생성
		imagePanel.setBounds(20, 90, 180, 16); // 좌표와 크기 설정 
		imagePanel.setBackground(MyConstants.DARK); // 배경색 설정
		imagePanel.setLayout(null); // 배치관리자 사용 안함 
		menuPanel.add(imagePanel); // menuPanel에 imagePanel을 add
		
		
		lblImage = new JLabel("Image");	// lblimage를 "Image"를 갖는 JLabel로 생성
		lblImage.setBounds(0, 0, 50, 16); // 좌표와 크기 설정 
		lblImage.setBackground(MyConstants.DARK); // 배경색 설정
		lblImage.setForeground(Color.white); // 전경색 설정 
		lblImage.setFont(MyConstants.MENU_FONT); // 폰트 설정 
		imagePanel.add(lblImage); // levelPanel에 lblImage를 add
		
		btnImage = new JButton[MyConstants.IMAGE]; // btnImage를 IMAGE개의 JButton 배열로 생성 : IMAGE = 5;
		for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE 갯수만큼 반복 
		{
			btnImage[i] = new JButton(MyConstants.BTN_IMAGE[i]); // 인덱스에 따라 안에 들어갈 텍스트를 설정 : BTN_IMAGE = {"1", "2", "3", "4", "5"};
			btnImage[i].setBounds(70+(i*23),0,16,16); // 좌표와 크기 설정 
			btnImage[i].setBackground(MyConstants.BROWN); // 배경색 설정
			btnImage[i].setForeground(Color.white); // 전경색 설정 
			btnImage[i].setFont(MyConstants.MENU_FONT); // 폰트 설정 
			
			if (curImage == i) // 현재 이미지 정보와 인덱스가 일치하는 버튼은 배경색을 RED로 설정
				btnImage[i].setBackground(MyConstants.RED);
			
			btnImage[i].setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬
			btnImage[i].setBorderPainted(false); // 버튼 경계선 제거
			btnImage[i].setFocusPainted(false); // 포커스(선택했던 버튼 표시) 제거
			btnImage[i].setMargin(new Insets(0,0,0,0)); // 상하좌우 여백 0으로 설정
			
			btnImage[i].setEnabled(true); // 버튼 활성화
			btnImage[i].addActionListener(btnActionL); // 버튼에 액션 리스너 설정
			btnImage[i].addMouseListener(btnMouseL); // 버튼에 마우스 리스너 설정
			imagePanel.add(btnImage[i]); // imagePanel에 해당 인덱스의 btnImage를 add
		}

		bestMin = new int[MyConstants.LEVEL]; // bestScore의 변수를 생성
		bestSec = new int[MyConstants.LEVEL]; // bestScore의 변수를 생성
		
		for(int i=0; i<MyConstants.LEVEL; i++)
			bestMin[i] = bestSec[i] = 99; // 최고점수 분과 시간을 99로 초기화 : 맨 처음 값이 들어오면 무조건 들어갈 수 있도록
		
		scorePanel = new JPanel();  // scorePanel을 JPanel로 생성
		scorePanel.setBounds(20,128,180,74); // 좌표와 크기 설정 
		scorePanel.setBackground(MyConstants.DARK); // 배경색 설정 
		scorePanel.setLayout(null); // 배치관리자 사용 안함 
		menuPanel.add(scorePanel); // menuPanel에 scorePanel을 add
		
		lblScore = new JLabel("00:00"); // lblScore를 "00:00"을 갖는 JLabel로 생성
		lblScore.setBounds(64, 35, 100, 24); // 좌표와 크기 설정 
		lblScore.setForeground(Color.white); // 전경색 설정
		lblScore.setFont(MyConstants.SCORE_FONT); // 폰트 설정 
		scorePanel.add(lblScore); // scorePanel에 lblScore를 add 
	    
	    lblBest = new JLabel(new ImageIcon("puzzle\\icon_bestscore.png")); // lblBest를 ImageIcon을 갖는 JLabel로 생성
		lblBest.setBounds(0,0,180,74); // 좌표와 크기 설정 
	    scorePanel.add(lblBest); // scorePanel에 lblBest를 add


	    btnRetry = new ImageButton(); // btnRetry를 ImageButton으로 생성
	    btnRetry.setImageIcon(MyConstants.BTN_EXIT); // btnRetry의 이미지를 BTN_EXIT로 설정 : 버튼의 배경 이미지임 
	    btnRetry.setBounds(20, 380, 180, 45); // 좌표와 크기 설정 
	    btnRetry.setBackground(MyConstants.DARK); // 배경색 설정
	    btnRetry.setLayout(null); // 배치관리자 사용 안함 
	    btnRetry.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬 
		btnRetry.setBorderPainted(false); // 버튼 경계선 제거
		btnRetry.setFocusPainted(false); // 포커스(선택했던 버튼 표시) 제거
		btnRetry.addActionListener(btnActionL); // 이미지버튼에 액션리스너 설정 
		btnRetry.addMouseListener(btnMouseL); // 이미지버튼에 마우스리스너 설정
	    menuPanel.add(btnRetry); // menuPanel에 btnRetry를 add
	    
	    lblRetry = new JLabel("", MyConstants.TXT_RETRY, SwingConstants.CENTER); // lblRetry를 ImageIcon을 갖는 JLabel로 생성
	    lblRetry.setBounds(40, 10, 100, 25); // 좌표와 크기 설정 
	    btnRetry.add(lblRetry); // btnRetry에 lblRetry를 add
	    
	    
	    btnExit = new ImageButton(); // btnExit를 ImageButton으로 생성
	    btnExit.setImageIcon(MyConstants.BTN_EXIT); // btnExit의 이미지를 BTN_EXIT로 설정 : 버튼의 배경 이미지임 
	    btnExit.setBounds(20, 435, 180, 45); // 좌표와 크기 설정 
	    btnExit.setBackground(MyConstants.DARK); // 배경색 설정
	    btnExit.setLayout(null); // 배치관리자 사용 안함 
	    btnExit.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬 
		btnExit.setBorderPainted(false); // 버튼 경계선 제거
		btnExit.setFocusPainted(false); // 포커스(선택했던 버튼 표시) 제거 
		btnExit.addActionListener(btnActionL); // 이비지버튼에 액션리스너 설정 
		btnExit.addMouseListener(btnMouseL); // 이비지버튼에 마우스리스너 설정
	    menuPanel.add(btnExit); // menuPanel에 btnExit를 add 
	    
	    lblExit = new JLabel("", MyConstants.TXT_EXIT, SwingConstants.CENTER); // lblExit를 ImageIcon을 갖는 JLabel로 생성 
	    lblExit.setBounds(40, 10, 100, 25); // 좌표와 크기 설정
	    btnExit.add(lblExit); // btnExit에 lblExit를 add 
	    
	    
	    
	    
		//////////////////////////////
		
		
		
		
		bgmPath = new String("song/Reminiscence.mp3"); // bgmPath를 해당 주소를 갖는 String으로 생성
		count = 0; // count변수를 0으로 초기화
		mThread = null; // mThread를 null로 초기화 
		isBGMPlaying = false; //배경음악이 재생중이 아님으로 초기화 

		/*btnbgm setting*/
		btnBgm = new ImageButton(MyConstants.BGM_OFF); // btnBgm을 ImageIcon인 BGM_OFF를 갖는 ImageButton으로 생성
	    btnBgm.setImageIcon(MyConstants.BGM_OFF); // btnBgm의 이미지를 BGM_OFF로 설정
	    btnBgm.setBounds(20, 10, 180, 45); // 좌표와 크기 설정 
	    btnBgm.setBackground(MyConstants.DARK); // 배경색 설정
	    btnBgm.setLayout(null); // 배치관리자 사용 안함
	    btnBgm.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬 : 세로
	    btnBgm.setVerticalAlignment(SwingConstants.CENTER); // 가운데정렬 : 가로 
		btnBgm.setBorderPainted(false); // 버튼 경계선 제거
		btnBgm.setFocusPainted(false); // 포커스(선택했던 버튼 표시) 제거
		btnBgm.addActionListener(btnActionL); // 이미지버튼에 액션 리스너 설정
	    menuPanel.add(btnBgm); // menuPanel에 btnBgm을 add 

	    
	
	} // PrimaryPanel()	







	public boolean getPlay() { return bPlay; } // 게임 플레이 상태가 어떤지 다른 클래스에서 가져갈 수 있는 메소드
	public void setPlay(boolean bP) // 다른 클래스에서 PrimaryPanel의 bPlay상태를 변경시킬 수 있는 메소드
	{
		bPlay = bP; // 받아온 값을 bPlay로 설정
		
		if (bPlay == true) // 게임 플레이 상태가 맞으면 : 게임이 시작되면
		{
			lblTimer.setMin(0); // lblTimer의 분값을 0으로 설정
			lblTimer.setSec(0); // lblTiemr의 초값을 0으로 설정
			lblTimer.start(); // lblTimer의 thread를 start
		}
		if (bPlay == false) // 게임 플레이 상태가 아니면 : 게임이 종료되면
		{
			lblTimer.stop(); // lblTimer의 thread를 stop
			bestScore(); // 최고점수 계산하는 메소드를 호출
			for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE의 갯수만큼 반복 : IMAGE = 5;
			{
				btnImage[i].setEnabled(false); // 버튼 비활성화 
				btnImage[i].setBackground(MyConstants.DARK_BROWN); // 배경색 설정
				btnImage[i].setForeground(MyConstants.LIGHT_BROWN); // 전경색 설정 
			}
		}
	}
	
	public void playInit() //lblTimer의 thread를 초기화 시키는 메소드
	{
		lblTimer.setVisible(false); // lblTimer를 안보이게 설정 
		lblTimer.interrupt(); // lblTimer의 thread를 interrupt
		
		lblTimer = new LabelThread("00:00"); // lblTimer를 "00:00"을 갖는 LabelThread로 새로 생성 
		lblTimer.setBounds(170,0,210,45); // 좌표와 크기 설정 
		lblTimer.setForeground(MyConstants.RED); // 전경색 설정 
		lblTimer.setFont(MyConstants.TIME_FONT); // 폰트 설정 
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬 
		timerPanel.add(lblTimer); // timerPanel에 lblTimer를 add
		
		lblTimer.setMin(0); // lblTimer의 분값을 0으로 설정
		lblTimer.setSec(0); // lblTimer의 초값을 0으로 설정 
		bPlay = false; // 게임 플레이 상태를 종료상태로 설정 
		
		for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE의 갯수만큼 반복 : IMAGE = 5;
		{
			btnImage[i].setEnabled(true); // 버튼 활성화
			btnImage[i].setBackground(MyConstants.BROWN); // 배경색 설정
			btnImage[i].setForeground(Color.white); // 전경색 설정 
			if (curImage == i) // 현재 이미지 정보와 인덱스가 일치하는 버튼은 배경색을 RED로 설정
				btnImage[i].setBackground(MyConstants.RED);
		}

	}

	public void bestScore() // 게임이 종료되면 최고점수를 계산하는 메소드
	{
		int min, sec; // 분, 초 값을 갖는 인트형 변수
		min = lblTimer.getMin(); // lblTimer에서 분값을 가져온다
		sec = lblTimer.getSec(); // lblTimer에서 초값을 가져온다 

		if(bestMin[curLevel] > min) // 만약 분이 현재 최고점수보다 작으면
		{ // 분이 작으면 초는 볼것도 없음 
			bestMin[curLevel] = min; // 현재 최고점수에 분값을 저장 
		    bestSec[curLevel] = sec; // 현재 최고점수에 초값을 저장
		    lblScore.setText(String.format("%02d:%02d", min, sec)); // lblScore를 다시 설정
		    repaint(); // 화면을 다시 그림
		}
	    else if (bestMin[curLevel] == min) // 만약 분이 현재 최고점수랑 같으면 
	    { // 이때는 초를 비교해야함
		    if(bestSec[curLevel] >= sec) // 만약 초가 현재 최고점수보다 작거나 같으면 
		    {
		    	bestSec[curLevel] = sec; // 현재 최고점수에 초값을 저장
		    	lblScore.setText(String.format("%02d:%02d", min, sec)); // lblScore를 다시 설정
		    	repaint(); // 화면을 다시 그림
		    }
	    }
    } // bestscore() -> 일단 분이 적다면 그 때 초 비교해서 초 바꾸기
     
     
    public void sound(int count) //노래 플레이 함수
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
  
  
  
  
  
  
  
  
  
  
  
     
	private class BtnActionListener implements ActionListener // 버튼을 위해 응답을 확인하는 액션리스너
	{
		public void actionPerformed(ActionEvent event) // 입력을 받으면 들어오는 곳 
		{
			Object obj = event.getSource(); // 어떤 버튼이나 어디서 액션이 입력됐는지 obj에 저장 : Object가 최상위라 모든 자료를 받을 수 있음 

			if(obj == btnBgm && count%2==0)
			{
				count++;//count++
				btnBgm.setImageIcon(MyConstants.BGM_ON); //change image on
				sound(count); //카운트가 짝수인 경우
			}
			else if(obj == btnBgm && count%2==1)
			{
				count++;//count++
				btnBgm.setImageIcon(MyConstants.BGM_OFF);//change image off
				sound(count); //카운트가 짝수인 경우
			}
			
			
			
			if (obj == btnRetry) // 재도전 버튼을 눌렀을 경우
			{
				view.setLevel(curLevel); // 게임 레벨을 현재 레벨로 설정 
				for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE 갯수만큼 반복 : IMAGE = 5;
				{
					btnImage[i].setEnabled(true); // 버튼 활성화
					btnImage[i].setBackground(MyConstants.BROWN); // 배경색 설정 
					btnImage[i].setForeground(Color.white); // 전경색 설정
					
					if (curImage == i) // 현재 레벨 정보와 인덱스가 일치하는 버튼은 배경색을 RED로 설정
						btnImage[i].setBackground(MyConstants.RED);
				}
				playInit(); // lblTimer의 thread 초기화 
			}
			else if (obj == btnExit) // 나가기 버튼을 눌렀을 경우 
				System.exit(0); // 시스템 종료 
						
			
			for (int i=0; i<MyConstants.LEVEL; i++) // LEVEL 갯수 만큼 반복 : LEVEL = 3;
			{
				if (obj == btnLevel[i]) // i번째 레벨을 눌렀을 경우
				{
					btnLevel[curLevel].setBackground(MyConstants.BROWN); // 기존 빨간색이던 현재 레벨을 다시 갈색으로 바꿈 
					curLevel = i; // 현재 레벨정보에 i값을 저장 
					btnLevel[i].setBackground(MyConstants.RED); // 바뀐 현재 레벨의 배경색을 빨간색으로 바꿈
					view.setLevel(i); // 게임 레벨을 i값으로 설정
					playInit(); // lblTimer의 thread 초기화
					if(bestMin[curLevel]!=99 && bestSec[curLevel]!=99) 
						lblScore.setText(String.format("%02d:%02d", bestMin[curLevel], bestSec[curLevel])); // 최고점수를 레이블에 표시
					else // 현재 분,초가 99이면 초기값이므로 00:00으로 표기 
						lblScore.setText("00:00"); 
					break;
				}
			}
			
			for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE 갯수 만큼 반복 : IMAGE = 5;
			{
				if (obj == btnImage[i]) // i번째 이미지를 눌렀을 경우 
				{
					btnImage[curImage].setBackground(MyConstants.BROWN); // 기존 빨간색이던 현재 이미지를 다시 갈색으로 바꿈 
					curImage = i; // 현재 이미지 정보에 i값을 저장 
					btnImage[i].setBackground(MyConstants.RED); // 바뀐 현재 이미지의 배경색을 빨간색으로 바꿈
					view.setImage(i); // 게임 이미지를 i값으로 설정
					break;
				}
			}
			
		} // actionPerformed()
	} // LevelListener class
	
	
	
	private class BtnMouseListener implements MouseListener // 버튼을 위해 마우스 동작을 확인하는 마우스 리스너 
	{
		public void mouseClicked(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) // 마우스가 안으로 들어왔을 경우
		{
			Object obj = event.getSource(); // 어떤 버튼이나 어디서 액션이 입력됐는지 obj에 저장 : Object가 최상위라 모든 자료를 받을 수 있음 
			
			if (obj == btnRetry) // 마우스가 재도전 버튼 안으로 들어왔을 경우 
				btnRetry.setImageIcon(MyConstants.BTN_ENTER); // 재도전 버튼의 이미지를 BTN_ENTER로 바꿈 : 버튼의 배경 이미지임 
			else if (obj == btnExit) // 마우스가 나가기 버튼 안으로 들어왔을 경우 
				btnExit.setImageIcon(MyConstants.BTN_ENTER); // 나가기 버튼의 이미지를 BTN_ENTER로 바꿈 : 버튼의 배경 이미지임 
			
			for (int i=0; i<MyConstants.LEVEL; i++) // LEVEL 갯수만큼 반복 : LEVEL = 3;
			{
				if (obj == btnLevel[i]) // i번째 레벨 안에 들어왔을 경우
				{
					btnLevel[i].setBackground(MyConstants.LIGHT_BROWN); // 버튼의 배경색을 밝은색으로 바꿈
					
					if (curLevel == i) // 현재 레벨 안에 들어왔을 경우는 밝은 빨간색으로 설정
						btnLevel[i].setBackground(MyConstants.LIGHT_RED);
					break;
				}
			}
			
			for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE 갯수만큼 반복 : IMAGE = 5;
			{
				if (obj == btnImage[i] && bPlay == true) // 게임이 진행중이며 i번쨰 이미지 안에 들어왔을 경우 
				{
					btnImage[i].setBackground(MyConstants.LIGHT_BROWN); // 버튼의 배경색을 밝은색으로 바꿈 
					
					if (curImage == i) // 현재 이미지 안에 들어왔을 경우에는 밝은 빨간색으로 설정
						btnImage[i].setBackground(MyConstants.LIGHT_RED);
					break;
				}
			}

		} // mouseEntered() // 마우스가 밖으로 나갔을 경우 
		public void mouseExited(MouseEvent event)
		{
			Object obj = event.getSource(); // 어떤 버튼이나 어디서 액션이 입력됐는지 obj에 저장 : Object가 최상위라 모든 자료를 받을 수 있음 
			
			if (obj == btnRetry) // 마우스가 재도전 버튼 밖으로 나갔을 경우 
				btnRetry.setImageIcon(MyConstants.BTN_EXIT); // 재도전 버튼의 이미지를 BTN_EXIT 바꿈 : 버튼의 배경 이미지임 
			else if (obj == btnExit) // 마우스가 재도전 버튼 밖으로 나갔을 경우 
				btnExit.setImageIcon(MyConstants.BTN_EXIT); // 나가기 버튼의 이미지를 BTN_EXIT 바꿈 : 버튼의 배경 이미지임 
			
			for (int i=0; i<MyConstants.LEVEL; i++) // LEVEL 갯수만큼 반복 : LEVEL = 3;
			{
				if (obj == btnLevel[i]) // i번째 레벨 밖으로 나갔을 경우
				{
					btnLevel[i].setBackground(MyConstants.BROWN); // 버튼의 배경색을 원래색으로 바꿈
					
					if (curLevel == i) // 현재 레벨 안에 들어왔을 경우는 밝은 빨간색으로 설정
						btnLevel[i].setBackground(MyConstants.RED);
					break;
				}
			}
			
			for (int i=0; i<MyConstants.IMAGE; i++) // IMAGE 갯수만큼 반복 : IMAGE = 5;
			{
				if (obj == btnImage[i] && bPlay == true) // 게임이 진행중이며 i번쨰 이미지 밖으로 나갔을 경우
				{
					btnImage[i].setBackground(MyConstants.BROWN); // 버튼의 배경색을 원래색으로 바꿈
					
					if (curImage == i) // 현재 이미지 안에 들어왔을 경우에는 밝은 빨간색으로 설정
						btnImage[i].setBackground(MyConstants.RED);
					break;
				}
			}
		} // mouseExited()
		
	} // BtnMouseListener class
	

	
} // PrimaryPanel class
