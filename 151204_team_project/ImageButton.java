import java.awt.*;
import javax.swing.*;

class ImageButton extends JButton
{
	private ImageIcon img;
	
	// constructor
	public ImageButton()
	{
		setHorizontalAlignment(ImageButton.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black,1));
		setFocusPainted(false);
	}
	
	public ImageButton(String url)
	{
		setHorizontalAlignment(ImageButton.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black,1));
		setFocusPainted(false);
		setImageIcon(new ImageIcon(url));
	}
	
	public ImageButton(ImageIcon img)
	{
		setHorizontalAlignment(ImageButton.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.black,1));
		setFocusPainted(false);
		this.img = img;
	}
	
	// get/set
	public ImageIcon getImageIcon() { return this.img; }
	public void setImageIcon(ImageIcon img) { this.img = img; }
	
	
	@Override
	protected void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		if (img != null)
		{
			page.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		} // if
		
	} // paintComponent()
	
	
	
} // ImgButton class