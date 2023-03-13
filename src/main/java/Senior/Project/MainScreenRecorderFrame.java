package Senior.Project;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class MainScreenRecorderFrame extends JFrame {
	
	private static final long serialVersionUID = -9017174244729535803L;
	
	private int width;
	private int height;
	private JButton startButton;
	private JButton stopButton;
	private JCheckBox delayBox;
	private JSeparator separator;
	private JLabel recordState;
	private JLabel recordTime;
	private JLabel timer;
	
	public MainScreenRecorderFrame(int w, int h) {
		
		setResizable(false);
		width = w;
		height = h;
		startButton = new JButton("Start Recording");
		stopButton = new JButton("Stop Recording");
		delayBox = new JCheckBox("5s Delay");
		
		separator = new JSeparator();
		recordState = new JLabel("Recording State: ");
		recordTime = new JLabel("Record Time: ");
		timer = new JLabel("Timer: ");
	}
	
	public void prepareGUI() {
		setSize(width, height);
		setLayout(null);
		setTitle("Recording Controls");
		
		startButton.setBounds(25, 25, 95, 30);
		stopButton.setBounds(25, 60, 95, 30);
		delayBox.setBounds(350, 25, 100, 20);
		separator.setOrientation(SwingConstants.HORIZONTAL);
		separator.setBounds(0, 120, 500, 1);
		recordState.setBounds(25, 150, 100, 20);
		recordTime.setBounds(25, 180, 100, 20);
		timer.setBounds(25, 210, 50, 20);
		
		

		add(startButton);
		add(stopButton);
		add(delayBox);
		add(separator);
		add(recordState);
		add(recordTime);
		add(timer);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}