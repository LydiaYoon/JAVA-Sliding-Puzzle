import javax.swing.*;

public class SlidingPuzzle	
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("KAKAO Sliding Puzzle"); // ���� ������ �̸��� �����ؼ� ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ������ ���� �� �ֵ��� ���� 
		frame.setResizable(false); // ������ ũ�� ���� ���ϰ� ����
		
		PrimaryPanel primary = new PrimaryPanel(); // PrimaryPanel Ŭ������ primary ��ü�� �����ϰ� ����
		frame.getContentPane().add(primary); // ������ panel�� frame�� add
		
		frame.pack();
		frame.setVisible(true);
	} // main()
} // SlidingPuzzle class

