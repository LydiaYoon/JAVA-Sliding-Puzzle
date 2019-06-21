import javax.swing.*;

public class SlidingPuzzle	
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("KAKAO Sliding Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); // 프레임 크기 조정 못하게 설정
		
		PrimaryPanel primary = new PrimaryPanel();
		frame.getContentPane().add(primary);
		
		frame.pack();
		frame.setVisible(true);
	} // main()
} // SlidingPuzzle class

