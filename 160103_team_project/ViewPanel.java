import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewPanel extends JPanel
{
	private int panelWidth, panelHeight; // �г��� ���� ���� ũ�⸦ �����ϱ� ���� ���� 
	
	private int [][] data; // ������ ���� �����͸� ������ 2���� �迭
	private int xlen, ylen; // ������ ���� ���� ĭ �����͸� ������ ���� : x-length, y-length
	private int blank; // ������ ��ĭ ��ġ(�ε���)�� ������ ����

	private ImageButton[] btnArray; // ���� �ȿ� �� ��ư���� ���� �̹��� ��ư �迭 
	private ImageButton btnTemp; // ���� ��ü�� ����� temp ���� 

	private int level, image; // ���� ������ �̹��� ������ ������ ���� 
	private PrimaryPanel primary; // PrimaryPaenl�� �ޱ� ���� ����
	
	private String url; // �̹��� �ּҸ� ����� ���� ���� 
	
	private ViewActionListener viewActionL; // ViewPanel�� ���� ������ Ȯ���ϴ� �׼Ǹ�����


	
	public ViewPanel(PrimaryPanel p) // constructor
	{
		primary = p; // PrimaryPanel�� ���� ���۰� ���Ḧ �� �� �ְ� �ϱ� ���� �Ķ���ͷ� PrimaryPanel�� �ް� primary�� ����
		
		viewActionL = new ViewActionListener(); // �׼Ǹ����� ���� 
		
		panelWidth = 380; // ���� ũ�� ���� 
		panelHeight = 380; // ���� ũ�� ����
		
		level = 1; // ������ �ʱⰪ�� 1�� ����
		image = 0; // �̹����� �ʱⰪ�� 1�� ���� 
		
		xlen = ylen = level+3; // ������ X���̿� Y���̴� ���� �������� 3�� ���� ���̴� : level = 0,1,2 / xlen,ylen = 3, 4, 5
		blank = xlen*ylen-1; // ó�� ��ĭ�� ��ġ�� ���μ��� ���̸� ���ؼ� 1�� �� ���̴� : 3*3�� ��� index=8�� ��ĭ��
		
		this.setBackground(new Color(51,51,51)); // ���� ����
		this.setSize(panelWidth, panelHeight); // ���μ��� ũ���� �г� ������ ���� 

		btnTemp = new ImageButton(); // temp�� ����
		
		init();	// ������ �ʱ�ȭ
		
	} // View()
	
	
	
	
	// ������ �� �ε��� ��� ����� ������ 3*3�� ���� �����ϰ� �����߾��
	// �ּ��� ���� ���ô� level=3�� ���� �����ؼ� �ۼ��߽��ϴ�.
	
	
	
	
	public void init()
	{
		this.removeAll(); // �г� ���� ���� component�� ��� ����
		
		this.setLayout(new GridLayout(xlen,ylen)); // ��ġ�����ڸ� xlen, ylen�� ���� �׸��巹�̾ƿ����� ���� �����
				
		data = new int [ylen][xlen]; // �����͸� ������ 2���� �迭�� xlen, ylen�� ���� ���� �����.
		for (int y=0; y<ylen; y++) // y�� 0���� 2���� 3��
		{
			for (int x=0; x<xlen; x++) // x�� 0���� 2���� 3�� �ݺ� 
			{
				int i = y*xlen+x; // �ε����� 0,1,2,...,8 �� �ȴ�. �����ʹ� 2�����̰� �ε����� 1�����̱� ������ ���
				if (i < xlen*ylen) data[y][x] = i; // data�� idx�� ����
			}
		}	
			
		btnArray = new ImageButton[xlen*ylen]; // �̹�����ư �迭�� xlen, ylen�� ���� �����.

		for (int i=0; i<xlen*ylen; i++) // �̹�����ư �迭�� 1�����ε����� 0���� 8���� 9�� ������.
		{
			url = "puzzle\\puzzle" + level + image + "\\" + i + ".png"; // �ּҸ� ������ �̹����� ���� �����Ѵ�.

			btnArray[i] = new ImageButton(); // i��° �̹�����ư�� �����ϰ�
			btnArray[i].setImageIcon(new ImageIcon(url)); // �Ʊ� ������ �ּ��� �̹����� �����ͼ� �����Ѵ�.
			btnArray[i].setEnabled(true); // ��ư Ȱ��ȭ
			btnArray[i].addActionListener(viewActionL); // �׼Ǹ����� ���� 
			this.add(btnArray[i]); // viewPanel�� i��° ��ư�� add�Ѵ�. �׸��巹�̾ƿ��̶� �˾Ƽ� ��ġ��

			// �ҷ��� �̹����� ������
		}
		
		shuffle();
		
		//repaint(); : repaint�� �ȸ����� setVisible�� �̿��� 
		setVisible(false);
		setVisible(true);
		
		
	} // init()


	public void shuffle() // data�� �������� �������� �޼ҵ� 
	{
		for (int m=0; m<500; m++) // ���� 500�� �ѷ��ش�.
		{
			int x = (int)(Math.random()*xlen); // 0���� 2������ ������
			int y = (int)(Math.random()*ylen); // 0���� 2������ ������ 
			move(x, y, true); // ������ �������� �̿��ؼ� �̵���Ų��. ��ư 9���� �������� 500�� Ŭ���ϴ� �Ͱ� �Ȱ���. �����Լ��� ȣ��ƴµ� �̵������� ��ư ��ġ�� ��� �̵�.
		}	
	}
	
	public void check() // ������ �ϼ��Ǿ����� Ȯ���ϱ� ���� �޼ҵ� 
	{
		for (int y=0; y<ylen; y++) // y�� 0���� 2���� 3�� �ݺ� 
		{
			for (int x=0; x<xlen; x++) // x�� 0���� 2���� 3�� �ݺ� 
			{
				int expect = y*xlen+x; // ��밪�� 2���� �ε����� 1�������� �����ؼ� 0,1,2,...,8 ó�� ������� ����ȴ�.
				if (data[y][x] != expect) return; // ���� �ε����� �����Ͱ� ��밪�� ���� ������ ����.
			}
		}
		
		// ��� �����Ͱ� ��밪�� ���ٸ� ������ �ϼ��� �����̴�!
		// ������ �ϼ��Ǿ����� �������� �ȳ����� ������ ������� ������ ���̴�.	
		btnArray[blank].setImageIcon(new ImageIcon("puzzle\\puzzle" + level + image + "\\" + 999 + ".png")); // ��ĭ�̾��� �̹����� �����̹����� ������ 
		
		for (int i=0; i<xlen*ylen; i++) // 0���� 8���� 9�� �ݺ�, ��� ��ư�� �Ʒ��� ���� ����
		{
			btnArray[i].setBorderPainted(false); // ��ư �׵θ��� ���ش�
			btnArray[i].setEnabled(false); // ��ư ��Ȱ��ȭ 
		}
			
		primary.setPlay(false); // primaryPanel�� setPlay �޼ҵ带 �̿��� lblTimer�� thread�� �����Ѵ�.
		JOptionPane.showMessageDialog(this,"������ �ϼ��Ǿ����ϴ�!", "Complete", JOptionPane.INFORMATION_MESSAGE); // ������ �ϼ��Ǿ��ٴ� �޼���â�� ���� 
		
	} // check()



	public void move(int x, int y, boolean init) // ���� ��ǥ�� �̵� ������ ��ư ��ġ���� Ȯ���ϰ� ������ �����̴� �޼ҵ�
	{
		int i = y*xlen+x; // �켱 ���� ��ǥ�� 2�����̹Ƿ� 1���� �ε����� �����Ѵ�.
		
		if ((y-1) >= 0) // y�� �� ������ �ƴ� �� : y = 1,2
			if (data[y-1][x] == blank) // blank�� ���� �ִ� ��� (���� �̵� ����)
			{
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i-xlen].getImageIcon());
				btnArray[i-xlen].setImageIcon(btnTemp.getImageIcon());
				// �̹��� ��ü
				data[y-1][x] = data[y][x];
				data[y][x] = blank; 
				// ������ ��ü
				repaint();
				if (init == false) check(); // �̵��ϴ� �� ���� ������ �ϼ��Ǿ����� Ȯ���Ѵ�. (���ϻ���)
				return;
			}
		
		if ((y+1) <= ylen-1) // y�� �� �Ʒ����� �ƴ� �� : y = 0,1
			if (data[y+1][x] == blank) // blank�� �ؿ� �ִ� ��� (�Ʒ��� �̵� ����)
			{
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i+xlen].getImageIcon());
				btnArray[i+xlen].setImageIcon(btnTemp.getImageIcon());
				// �̹�����ü
				data[y+1][x] = data[y][x];
				data[y][x] = blank;
				// �����ͱ�ü
				repaint();
				if (init == false) check();
				return;
			}
		
		if ((x-1) >= 0) // x�� �� ������ �ƴ� �� : x = 1,2
			if (data[y][x-1] == blank) // blank�� ���ʿ� �ִ� ��� (���� �̵� ����)
			{
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i-1].getImageIcon());
				btnArray[i-1].setImageIcon(btnTemp.getImageIcon());
				// �̹��� ��ü
				data[y][x-1] = data[y][x];
				data[y][x] = blank;
				// ������ ��ü
				repaint();
				if (init == false) check();
				return;
			}			
		
		if ((x+1) <= xlen-1) // x�� �� �������� �ƴ� �� : x = 0,1
			if (data[y][x+1] == blank) // blank�� �����ʿ� �ִ� ��� (������ �̵� ����)
			{
				btnTemp.setImageIcon(btnArray[i].getImageIcon());
				btnArray[i].setImageIcon(btnArray[i+1].getImageIcon());
				btnArray[i+1].setImageIcon(btnTemp.getImageIcon());
				// �̹��� ��ü 
				data[y][x+1] = data[y][x];
				data[y][x] = blank;
				// ������ ��ü
				repaint();
				if (init == false) check();
				return;
			}
		// �װ��� ����� �����ϴ� �κ��� ���ǹ��� �Ϸ��ϰ� ���ϵ�
	} // move()

	public int getWidth() { return panelWidth; } // �ٸ� Ŭ�������� ViewPanel�� ����ũ�⸦ ������ �� �ִ� �޼ҵ� 
	public int getHeight() { return panelHeight; } // �ٸ� Ŭ�������� ViewPanel�� ����ũ�⸦ ������ �� �ִ� �޼ҵ� 
	public int getLevel() { return level; } // �ٸ� Ŭ�������� ViewPanel�� level������ ������ �� �ִ� �޼ҵ� 
	public int getImage() { return image; } // �ٸ� Ŭ�������� ViewPanel�� image������ ������ �� �ִ� �޼ҵ� 

	public void setLevel(int n) // �ٸ� Ŭ�������� ViewPanel�� level������ ������ �� �ִ� �޼ҵ� 
	{
		level = n; // �������� ������ ���� 
		xlen = ylen = n+3; // xlen, ylen�� ���� ��� 
		blank = xlen*ylen-1; // blank�� ���� ���
		init(); // ���� ���� �ʱ�ȭ
	} // setLevel()
	
	public void setImage(int n) // �ٸ� Ŭ�������� ViewPanel�� image ������ ������ �� �ִ� �޼ҵ� 
	{
		image = n; // ���� ���� �̹����� ���� 
		for (int y=0; y<ylen; y++)
		{
			for (int x=0; x<xlen; x++)
			{
				int i = data[y][x]; // �ε����� �����Ͱ��� �����ϰ�
				url = "puzzle\\puzzle" + level + image + "\\" + i + ".png"; // �� �����Ͱ����� �ּҸ� ����������
				btnArray[y*xlen+x].setImageIcon(new ImageIcon(url)); // ���� ��ư �̹����� ���� �ٲ۴�. 
			}
		}
		repaint();		
	} // setImage()
	





	private class ViewActionListener implements ActionListener // ViewPanel�� ���� ������ Ȯ���ϴ� �׼Ǹ�����
	{
		public void actionPerformed(ActionEvent event) // �Է��� ������ ������ �� 
		{
			Object obj = event.getSource(); // � ��ư�̳� ��� �׼��� �Էµƴ��� obj�� ���� : Object�� �ֻ����� ��� �ڷḦ ���� �� ����

			for (int i=0; i<xlen*ylen; i++)
			{
				if (obj == btnArray[i]) // i���� ���� ������ ������ ���
				{
					if (primary.getPlay() == false) // ���ӽ��� ���°� �ƴϸ� ���������� ó�� ���� ����̴�.
						primary.setPlay(true); // ó�� �������� ���� ���¸� ���������� �ٲ۴�.

					//i��°�� x,y�� ������ ��� 
					int x = i%xlen; // x�� i�� xlen���� ���� ������. 
					int y = i/ylen; // y�� i�� ylen���� ���� ������.
					check(); // Ȥ�� �𸣴� �̵� ���� �ϼ��������� �ٽ��ѹ� Ȯ���ϰ� 
					move(x, y, false); // �̵���Ų��. 
				}
			}

		} // actionPerformed()
		
	} // ViewActionListener class

} // View class
