import javax.swing.*;

public class SlidingPuzzle	
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("KAKAO Sliding Puzzle"); // 메인 프레임 이름을 설정해서 생성
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 프레임 닫을 수 있도록 설정 
		frame.setResizable(false); // 프레임 크기 조정 못하게 설정
		
		PrimaryPanel primary = new PrimaryPanel(); // PrimaryPanel 클래스의 primary 객체를 선언하고 생성
		frame.getContentPane().add(primary); // 생성한 panel을 frame에 add
		
		frame.pack();
		frame.setVisible(true);
	} // main()
} // SlidingPuzzle class

