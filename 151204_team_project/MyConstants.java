import java.awt.*;
import javax.swing.*;

public class MyConstants
{
	public static final int LEVEL = 3;
	public static final int IMAGE = 5;
	public static final String[] BTN_LEVEL = {"3x3", "4x4", "5x5"};
	public static final String[] BTN_IMAGE = {"1", "2", "3", "4", "5"};	
	
	public static final Color YELLOW = new Color(255,238,90);
	public static final Color DARK = new Color(71,52,52);
	public static final Color DARK_BROWN = new Color(83,65,65);
	public static final Color BROWN = new Color(112,95,95);
	public static final Color LIGHT_BROWN = new Color(145, 127, 127);
	public static final Color RED = new Color(255,91,73);
	public static final Color LIGHT_RED = new Color(255,123,120);
	
	public static final Font TIME_FONT = new Font("verdana", Font.BOLD, 50);
	public static final Font MENU_FONT = new Font("verdana", Font.BOLD, 11);
	public static final Font SCORE_FONT = new Font("verdana", Font.BOLD, 26);
	
	public static final ImageIcon BTN_EXIT = new ImageIcon("puzzle\\btn_exited.png");
	public static final ImageIcon BTN_ENTER = new ImageIcon("puzzle\\btn_entered.png");
	public static final ImageIcon TXT_EXIT = new ImageIcon("puzzle\\txt_exit.png");
	public static final ImageIcon TXT_RETRY = new ImageIcon("puzzle\\txt_retry.png");
	public static final ImageIcon BGM_OFF = new ImageIcon("puzzle\\bgmoff.png"); //bgm button off
	public static final ImageIcon BGM_ON = new ImageIcon("puzzle\\bgmon.png"); //bgm button on
} // GameConstants
