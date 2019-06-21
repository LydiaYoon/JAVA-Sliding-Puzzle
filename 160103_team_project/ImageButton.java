import java.awt.*;
import javax.swing.*;

class ImageButton extends JButton
{
	private ImageIcon img; // 버튼에 설정할 이미지를 저장되는 이미지아이콘 변수
	
	// constructor
	public ImageButton() // 아무 파라미터 없이 그냥 생성했을 때 
	{
		setHorizontalAlignment(ImageButton.CENTER); // 가운데정렬
		setBorder(BorderFactory.createLineBorder(Color.black,1)); // 검은색 1px짜리 테두리로 설정 
		setFocusPainted(false); // 포커스(선택했던 버튼 표시) 제거
	}
	
	public ImageButton(String url) // 아예 주소값을 넣고 생성했을 때 
	{
		setHorizontalAlignment(ImageButton.CENTER); // 가운데정렬
		setBorder(BorderFactory.createLineBorder(Color.black,1)); // 검은색 1px짜리 테두리로 설정 
		setFocusPainted(false); // 포커스(선택했던 버튼 표시) 제거
		setImageIcon(new ImageIcon(url));
	}
	
	public ImageButton(ImageIcon img) // 이미지아이콘을 넣고 생성했을 때
	{
		setHorizontalAlignment(ImageButton.CENTER); // 가운데정렬
		setBorder(BorderFactory.createLineBorder(Color.black,1)); // 검은색 1px짜리 테두리로 설정 
		setFocusPainted(false); // 포커스(선택했던 버튼 표시) 제거
		this.img = img; // 받은 이미지로 이미지 설정
	}
	
	// get/set
	public ImageIcon getImageIcon() { return this.img; } // 다른 클래스에서 이미지버튼의 이미지를 가져살 수 있는 메소드
	public void setImageIcon(ImageIcon img) { this.img = img; } // 다른 클래스에서 이미지버튼의 이미지를 변경시킬 수 있는 메소드 
	
	
	@Override
	protected void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		if (img != null) // 이미지가 null이 아니면 이미지를 그린다.
		{
			page.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		} // if
		
	} // paintComponent()
	
	
	
} // ImgButton class