import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewPanel extends JPanel
{
	private int panelWidth, panelHeight; // 패널의 가로 세로 크기를 저장하기 위한 변수 
	
	private int [][] data; // 퍼즐의 내부 데이터를 저장할 2차원 배열
	private int xlen, ylen; // 퍼즐의 가로 세로 칸 데이터를 저장할 변수 : x-length, y-length
	private int blank; // 퍼즐의 빈칸 위치(인덱스)를 저장할 변수

	private ImageButton[] btnArray; // 퍼즐 안에 들어갈 버튼들을 위한 이미지 버튼 배열 
	private ImageButton btnTemp; // 퍼즐 교체시 사용할 temp 변수 

	private int level, image; // 현재 레벨과 이미지 정보를 저장할 변수 
	private PrimaryPanel primary; // PrimaryPaenl을 받기 위한 변수
	
	private String url; // 이미지 주소를 만들기 위한 변수 
	
	private ViewActionListener viewActionL; // ViewPanel을 위해 응답을 확인하는 액션리스너


	
	public ViewPanel(PrimaryPanel p) // constructor
	{
		primary = p; // PrimaryPanel이 게임 시작과 종료를 알 수 있게 하기 위해 파라미터로 PrimaryPanel을 받고 primary에 저장
		
		viewActionL = new ViewActionListener(); // 액션리스너 생성 
		
		panelWidth = 380; // 가로 크기 저장 
		panelHeight = 380; // 세로 크기 저장
		
		level = 1; // 레벨의 초기값을 1로 저장
		image = 0; // 이미지의 초기값을 1로 저장 
		
		xlen = ylen = level+3; // 퍼즐의 X길이와 Y길이는 레벨 정보에서 3을 더한 값이다 : level = 0,1,2 / xlen,ylen = 3, 4, 5
		blank = xlen*ylen-1; // 처음 빈칸의 위치는 가로세로 길이를 곱해서 1을 뺀 값이다 : 3*3일 경우 index=8이 빈칸임
		
		this.setBackground(new Color(51,51,51)); // 배경색 설정
		this.setSize(panelWidth, panelHeight); // 가로세로 크기대로 패널 사이즈 설정 

		btnTemp = new ImageButton(); // temp를 생성
		
		init();	// 게임을 초기화
		
	} // View()
	
	
	
	
	// 구현할 때 인덱스 계산 등등을 퍼즐이 3*3일 떄로 가정하고 생각했었어서
	// 주석에 쓰인 예시는 level=3일 경우로 생각해서 작성했습니다.
	
	
	
	
	public void init()
	{
		this.removeAll(); // 패널 안의 기존 component를 모두 지움
		
		this.setLayout(new GridLayout(xlen,ylen)); // 배치관리자를 xlen, ylen에 따라 그리드레이아웃으로 새로 만든다
				
		data = new int [ylen][xlen]; // 데이터를 저장할 2차원 배열을 xlen, ylen의 값에 따라 만든다.
		for (int y=0; y<ylen; y++) // y는 0부터 2까지 3번
		{
			for (int x=0; x<xlen; x++) // x는 0부터 2까지 3번 반복 
			{
				int i = y*xlen+x; // 인덱스는 0,1,2,...,8 가 된다. 데이터는 2차원이고 인덱스는 1차원이기 때문에 계산
				if (i < xlen*ylen) data[y][x] = i; // data에 idx값 저장
			}
		}	
			
		btnArray = new ImageButton[xlen*ylen]; // 이미지버튼 배열을 xlen, ylen에 따라 만든다.

		for (int i=0; i<xlen*ylen; i++) // 이미지버튼 배열은 1차원인덱스고 0부터 8까지 9번 돌린다.
		{
			url = "puzzle\\puzzle" + level + image + "\\" + i + ".png"; // 주소를 레벨과 이미지에 따라 저장한다.

			btnArray[i] = new ImageButton(); // i번째 이미지버튼을 생성하고
			btnArray[i].setImageIcon(new ImageIcon(url)); // 아까 저장한 주소의 이미지를 가져와서 설정한다.
			btnArray[i].setEnabled(true); // 버튼 활성화
			btnArray[i].addActionListener(viewActionL); // 액션리스너 설정 
			this.add(btnArray[i]); // viewPanel에 i번째 버튼을 add한다. 그리드레이아웃이라 알아서 배치됨

			// 불러온 이미지를 저장함
		}
		
		shuffle();
		
		//repaint(); : repaint가 안먹혀서 setVisible을 이용함 
		setVisible(false);
		setVisible(true);
		
		
	} // init()


	public void shuffle() // data를 무작위로 섞기위한 메소드 
	{
		for (int m=0; m<500; m++) // 값을 500번 뿌려준다.
		{
			int x = (int)(Math.random()*xlen); // 0부터 2사이의 랜덤값
			int y = (int)(Math.random()*ylen); // 0부터 2사이의 랜덤값 
			move(x, y, true); // 생성된 랜덤값을 이용해서 이동시킨다. 버튼 9개중 랜덤으로 500개 클릭하는 것과 똑같음. 무브함수가 호출됐는데 이동가능한 버튼 위치인 경우 이동.
		}	
	}
	
	public void check() // 퍼즐이 완성되었는지 확인하기 위한 메소드 
	{
		for (int y=0; y<ylen; y++) // y는 0부터 2까지 3번 반복 
		{
			for (int x=0; x<xlen; x++) // x는 0부터 2까지 3번 반복 
			{
				int expect = y*xlen+x; // 기대값은 2차원 인덱스를 1차원으로 변경해서 0,1,2,...,8 처럼 순서대로 저장된다.
				if (data[y][x] != expect) return; // 현재 인덱스의 데이터가 기대값과 같지 않으면 리턴.
			}
		}
		
		// 모든 데이터가 기대값과 같다면 퍼즐이 완성된 상태이다!
		// 퍼즐이 완성되었으니 조각으로 안나뉘고 합쳐진 모습으로 보여줄 것이다.	
		btnArray[blank].setImageIcon(new ImageIcon("puzzle\\puzzle" + level + image + "\\" + 999 + ".png")); // 빈칸이었던 이미지를 퍼즐이미지로 가져옴 
		
		for (int i=0; i<xlen*ylen; i++) // 0부터 8까지 9번 반복, 모든 버튼을 아래와 같이 설정
		{
			btnArray[i].setBorderPainted(false); // 버튼 테두리를 없앤다
			btnArray[i].setEnabled(false); // 버튼 비활성화 
		}
			
		primary.setPlay(false); // primaryPanel의 setPlay 메소드를 이용해 lblTimer의 thread를 조정한다.
		JOptionPane.showMessageDialog(this,"퍼즐이 완성되었습니다!", "Complete", JOptionPane.INFORMATION_MESSAGE); // 퍼즐이 완성되었다는 메세지창을 띄운다 
		
	} // check()



	public void move(int x, int y, boolean init) // 받은 좌표가 이동 가능한 버튼 위치인지 확인하고 퍼즐을 움직이는 메소드
	{
		int i = y*xlen+x; // 우선 받은 좌표가 2차원이므로 1차원 인덱스로 변경한다.
		
		if ((y-1) >= 0) // y가 맨 윗줄이 아닐 때 : y = 1,2
			if (data[y-1][x] == blank) // blank가 위에 있는 경우 (위로 이동 가능)
			{
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i-xlen].getImageIcon());
				btnArray[i-xlen].setImageIcon(btnTemp.getImageIcon());
				// 이미지 교체
				data[y-1][x] = data[y][x];
				data[y][x] = blank; 
				// 데이터 교체
				repaint();
				if (init == false) check(); // 이동하는 매 순가 퍼즐이 완성되었는지 확인한다. (이하생략)
				return;
			}
		
		if ((y+1) <= ylen-1) // y가 맨 아랫줄이 아닐 때 : y = 0,1
			if (data[y+1][x] == blank) // blank가 밑에 있는 경우 (아래로 이동 가능)
			{
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i+xlen].getImageIcon());
				btnArray[i+xlen].setImageIcon(btnTemp.getImageIcon());
				// 이미지교체
				data[y+1][x] = data[y][x];
				data[y][x] = blank;
				// 데이터교체
				repaint();
				if (init == false) check();
				return;
			}
		
		if ((x-1) >= 0) // x가 맨 왼쪽이 아닐 때 : x = 1,2
			if (data[y][x-1] == blank) // blank가 왼쪽에 있는 경우 (왼쪽 이동 가능)
			{
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i-1].getImageIcon());
				btnArray[i-1].setImageIcon(btnTemp.getImageIcon());
				// 이미지 교체
				data[y][x-1] = data[y][x];
				data[y][x] = blank;
				// 데이터 교체
				repaint();
				if (init == false) check();
				return;
			}			
		
		if ((x+1) <= xlen-1) // x가 맨 오른쪽이 아닐 때 : x = 0,1
			if (data[y][x+1] == blank) // blank가 오른쪽에 있는 경우 (오른쪽 이동 가능)
			{
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i+1].getImageIcon());
				btnArray[i+1].setImageIcon(btnTemp.getImageIcon());
				// 이미지 교체 
				data[y][x+1] = data[y][x];
				data[y][x] = blank;
				// 데이터 교체
				repaint();
				if (init == false) check();
				return;
			}
		// 네가지 경우중 만족하는 부분의 조건문을 완료하고 리턴됨
	} // move()

	public int getWidth() { return panelWidth; } // 다른 클래스에서 ViewPanel의 가로크기를 가져갈 수 있는 메소드 
	public int getHeight() { return panelHeight; } // 다른 클래스에서 ViewPanel의 세로크기를 가져갈 수 있는 메소드 
	public int getLevel() { return level; } // 다른 클래스에서 ViewPanel의 level정보를 가져갈 수 있는 메소드 
	public int getImage() { return image; } // 다른 클래스에서 ViewPanel의 image정보를 가져갈 수 있는 메소드 

	public void setLevel(int n) // 다른 클래스에서 ViewPanel의 level정보를 설정할 수 있는 메소드 
	{
		level = n; // 받은값을 레벨에 저장 
		xlen = ylen = n+3; // xlen, ylen을 새로 계산 
		blank = xlen*ylen-1; // blank도 새로 계산
		init(); // 게임 새로 초기화
	} // setLevel()
	
	public void setImage(int n) // 다른 클래스에서 ViewPanel의 image 정보를 설정할 수 있는 메소드 
	{
		image = n; // 받은 값을 이미지에 저장 
		for (int y=0; y<ylen; y++)
		{
			for (int x=0; x<xlen; x++)
			{
				int i = data[y][x]; // 인덱스에 데이터값을 저장하고
				url = "puzzle\\puzzle" + level + image + "\\" + i + ".png"; // 그 데이터값으로 주소를 만들어놓은뒤
				btnArray[y*xlen+x].setImageIcon(new ImageIcon(url)); // 현재 버튼 이미지를 새로 바꾼다. 
			}
		}
		repaint();		
	} // setImage()
	





	private class ViewActionListener implements ActionListener // ViewPanel을 위해 응답을 확인하는 액션리스너
	{
		public void actionPerformed(ActionEvent event) // 입력을 받으면 들어오는 곳 
		{
			Object obj = event.getSource(); // 어떤 버튼이나 어디서 액션이 입력됐는지 obj에 저장 : Object가 최상위라 모든 자료를 받을 수 있음

			for (int i=0; i<xlen*ylen; i++)
			{
				if (obj == btnArray[i]) // i번쨰 퍼즐 조각을 눌렀을 경우
				{
					if (primary.getPlay() == false) // 게임시작 상태가 아니면 퍼즐조각을 처음 누른 경우이다.
						primary.setPlay(true); // 처음 눌렀으니 게임 상태를 진행중으로 바꾼다.

					//i번째가 x,y로 몇인지 계산 
					int x = i%xlen; // x는 i를 xlen으로 나눈 나머지. 
					int y = i/ylen; // y는 i를 ylen으로 나눈 나머지.
					check(); // 혹시 모르니 이동 전에 완성상태인지 다시한번 확인하고 
					move(x, y, false); // 이동시킨다. 
				}
			}

		} // actionPerformed()
		
	} // ViewActionListener class

} // View class
