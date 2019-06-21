import java.awt.*;
import javax.swing.*;

class ImageButton extends JButton
{
	private ImageIcon img; // ��ư�� ������ �̹����� ����Ǵ� �̹��������� ����
	
	// constructor
	public ImageButton() // �ƹ� �Ķ���� ���� �׳� �������� �� 
	{
		setHorizontalAlignment(ImageButton.CENTER); // �������
		setBorder(BorderFactory.createLineBorder(Color.black,1)); // ������ 1px¥�� �׵θ��� ���� 
		setFocusPainted(false); // ��Ŀ��(�����ߴ� ��ư ǥ��) ����
	}
	
	public ImageButton(String url) // �ƿ� �ּҰ��� �ְ� �������� �� 
	{
		setHorizontalAlignment(ImageButton.CENTER); // �������
		setBorder(BorderFactory.createLineBorder(Color.black,1)); // ������ 1px¥�� �׵θ��� ���� 
		setFocusPainted(false); // ��Ŀ��(�����ߴ� ��ư ǥ��) ����
		setImageIcon(new ImageIcon(url));
	}
	
	public ImageButton(ImageIcon img) // �̹����������� �ְ� �������� ��
	{
		setHorizontalAlignment(ImageButton.CENTER); // �������
		setBorder(BorderFactory.createLineBorder(Color.black,1)); // ������ 1px¥�� �׵θ��� ���� 
		setFocusPainted(false); // ��Ŀ��(�����ߴ� ��ư ǥ��) ����
		this.img = img; // ���� �̹����� �̹��� ����
	}
	
	// get/set
	public ImageIcon getImageIcon() { return this.img; } // �ٸ� Ŭ�������� �̹�����ư�� �̹����� ������ �� �ִ� �޼ҵ�
	public void setImageIcon(ImageIcon img) { this.img = img; } // �ٸ� Ŭ�������� �̹�����ư�� �̹����� �����ų �� �ִ� �޼ҵ� 
	
	
	@Override
	protected void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		if (img != null) // �̹����� null�� �ƴϸ� �̹����� �׸���.
		{
			page.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		} // if
		
	} // paintComponent()
	
	
	
} // ImgButton class