import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewPanel extends JPanel
{
	private int panelWidth, panelHeight;
	
	private int [][] data;
	private int xlen, ylen;
	private int blank;

	private ImageIcon[] imgArray;
	private ImageButton[] btnArray;
	private ImageButton btnTemp;

	private int level, image;	
	private LabelThread timer;
	private PrimaryPanel primary;
	
	private String url; // �̹��� �ּҸ� ����� ���� ���� 
	
	private ViewActionListener viewActionL;


	
	
	public ViewPanel(PrimaryPanel p, LabelThread t) // constructor
	{
		primary = p;
		timer = t;
		
		viewActionL = new ViewActionListener();
		
		panelWidth = 380;
		panelHeight = 380;
		
		level = 1; // ���� 
		image = 0; // ���� �̹��� 
		
		xlen = ylen = level+3;
		blank = xlen*ylen-1;
		
		this.setBackground(new Color(51,51,51));
		this.setSize(panelWidth, panelHeight);

		btnTemp = new ImageButton();
		
		init();	
		
	} // View()
	
	
	
	
	public void init()
	{
		this.removeAll(); // �г� ���� ���� component�� ��� ����
		
		//System.out.println("xlen: " + xlen + ", ylen: " + ylen);
		this.setLayout(new GridLayout(xlen,ylen));
				
		data = new int [ylen][xlen];
		for (int y=0; y<ylen; y++) // data�� ������� ���� �Ҵ�
		{
			for (int x=0; x<xlen; x++)
			{
				int idx = y*xlen+x;
				data[y][x] = idx;
				if (idx<xlen*ylen) data[y][x] = idx; 
			}
		}	
			
		imgArray = new ImageIcon[xlen*ylen];
		btnArray = new ImageButton[xlen*ylen];

		for (int i=0; i<xlen*ylen; i++)
		{
			url = "puzzle\\puzzle" + level + image + "\\" + i + ".png";
			//System.out.println(url);
			
			//imgArray[i] = new ImageIcon(url);
			// puzzle �̹����� �ҷ���

			//btnArray[i] = new ImageButton(imgArray[data[i/ylen][i%xlen]]);
			
			btnArray[i] = new ImageButton();
			btnArray[i].setImageIcon(new ImageIcon(url));
			btnArray[i].setEnabled(true);
			//btnArray[i].setImageIcon(imgArray[data[i/ylen][i%xlen]]);
			//btnArray[i] = new ImageButton(url);

			btnArray[i].addActionListener(viewActionL);
			this.add(btnArray[i]);

			// �ҷ��� �̹����� ������
		}
		
		shuffle();
		
		//repaint();
		setVisible(false);
		setVisible(true);
		
		//primary.setPlay(false);// ������ �̽��� (�̿ϼ�) ���·� �ʱ�ȭ
		//System.out.println("primary.setPlay(false);	Init");
		
		//System.out.println("data[][] length: " + data.length);
		//System.out.println("imgArray[] length: " + data.length);
		//System.out.println("btnArray[] length: " + data.length);
		
	} // init()


	public void shuffle()
	{
		for (int m=0; m<500; m++) // data�� �������� ����
		{
			int x = (int)(Math.random()*xlen);
			int y = (int)(Math.random()*ylen);
			move(x, y, true);
		}	
	}
	public void check()
	{
		for (int y=0; y<ylen; y++)
		{
			for (int x=0; x<xlen; x++)
			{
				int expect = y*xlen+x;
				if (data[y][x] != expect) return;
			}
		}
	
		btnArray[blank].setImageIcon(new ImageIcon("puzzle\\puzzle" + level + image + "\\" + 999 + ".png"));
		
		for (int i=0; i<xlen*ylen; i++)
		{
			btnArray[i].setBorderPainted(false);
			btnArray[i].setEnabled(false);
		}
			
		primary.setPlay(false);
		//System.out.println("primary.setPlay(false);	Check");
		
		JOptionPane.showMessageDialog(this,"������ �ϼ��Ǿ����ϴ�!", "Complete", JOptionPane.INFORMATION_MESSAGE);
		
	} // check()



	public void move(int x, int y, boolean init) // �����¿쿡 blank�� �ִ��� Ȯ���ϰ� �����δ�.
	{
		int i = (y*xlen)+x;
		
		if ((y-1) >= 0) // y=1,2
		if (data[y-1][x] == blank) // blank�� ���� �ִ� ��� (���� �̵� ����)
			{
				//System.out.print("���� �̵�	");
				//System.out.println("[y:"+y+", x:"+x+"]");
				
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i-xlen].getImageIcon());
				btnArray[i-xlen].setImageIcon(btnTemp.getImageIcon());
				// �̹��� ��ü
				data[y-1][x] = data[y][x];
				data[y][x] = blank; 
				repaint();
				// ������ ��ü
				if (init == false) check();
				return;
			}
		
		if ((y+1) <= ylen-1) // y=0,1
			if (data[y+1][x] == blank) // blank�� �ؿ� �ִ� ��� (�Ʒ��� �̵� ����)
			{
				//System.out.print("�Ʒ��� �̵�	");
				//System.out.println("[y:"+y+", x:"+x+"]");
				
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i+xlen].getImageIcon());
				btnArray[i+xlen].setImageIcon(btnTemp.getImageIcon());
				
				data[y+1][x] = data[y][x];
				data[y][x] = blank;
				repaint();
				if (init == false) check();
				return;
			}
		
		if ((x-1) >= 0) //x=1,2
			if (data[y][x-1] == blank) // blank�� ���ʿ� �ִ� ��� (���� �̵� ����)
			{
				//System.out.print("���� �̵�	");
				//System.out.println("[y:"+y+", x:"+x+"]");
				
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i-1].getImageIcon());
				btnArray[i-1].setImageIcon(btnTemp.getImageIcon());
				
				data[y][x-1] = data[y][x];
				data[y][x] = blank;
				repaint();
				if (init == false) check();
				return;
			}			
		
		if ((x+1) <= xlen-1) // x=0,1
			if (data[y][x+1] == blank) // blank�� �����ʿ� �ִ� ��� (������ �̵� ����)
			{
				//System.out.print("������ �̵�	");
				//System.out.println("[y:"+y+", x:"+x+"]");
				
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i+1].getImageIcon());
				btnArray[i+1].setImageIcon(btnTemp.getImageIcon());
				
				data[y][x+1] = data[y][x];
				data[y][x] = blank;
				repaint();
				if (init == false) check();
				return;
			}
			
	} // move()

/*
	public void paint(Graphics g)
	{
		paintComponent(g); // �ʱ�ȭ

		for (int y=0; y<ylen; y++) {
			for (int x=0; x<xlen; x++) {
				
				String str = Integer.toString(data[y][x]); // ���ڸ� String�� ��ȯ
				int dx = x*size;
				int dy = y*size;
				
				g.setColor(Color.black);
				g.drawRect(dx,dy,size,size);
				
				if (blank != data[y][x])
				{	
					g.setColor(Color.white);
					g.drawString(str, dx+size/2, dy+size/2); // ���� ������ ����� ���ڸ� ���ش�.	
				}			
			}
		}
		
	} // paint()
*/

	public int getWidth() { return panelWidth; }
	public int getHeight() { return panelHeight; }
	public int getLevel() { return level; }
	public int getImage() { return image; }

	public void setLevel(int n)
	{
		level = n;
		xlen = ylen = n+3;
		blank = xlen*ylen-1;
		System.out.println("setLevel: " + n);
		init();
	} // setLevel()
	public void setImage(int n)
	{
		image = n;
		System.out.println("setImage: " + n);
		
		for (int y=0; y<ylen; y++)
		{
			for (int x=0; x<xlen; x++)
			{
				int idx = data[y][x];
				url = "puzzle\\puzzle" + level + image + "\\" + idx + ".png";
				//System.out.println(url);
				btnArray[y*xlen+x].setImageIcon(new ImageIcon(url));
			}
		}
		repaint();		
	} // setImage()
	





	private class ViewActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object obj = event.getSource();
			

			//if (bPlay == true) // ������ �������� ��� ��ư�� ������ ������ move�Ѵ�.
			//{
				for (int i=0; i<xlen*ylen; i++)
				{
					if (obj == btnArray[i])
					{
						//bPlay = true;
						if (primary.getPlay() == false)
							primary.setPlay(true);
							//System.out.println("primary.setPlay(true);	ActionListener");
						
						//System.out.println("bPlay = true [ViewActionListener]");
						int x = i%xlen;
						int y = i/ylen;
						check();
						move(x, y, false);
						//System.out.println("action	[y:"+y+", x:"+x+"]");						break;
					}
				}
			//}

			
		} // actionPerformed()
		
	} // ViewActionListener class

} // View class