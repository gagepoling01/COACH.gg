package Senior.Project;

import javax.swing.JFrame;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.jcodec.api.awt.AWTSequenceEncoder;

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
	
	Rectangle rectangle;
	AWTSequenceEncoder encoder;
	ScreenRecorderTask recorderTask;
	TimerCountTask countTask;
	File file;
	
	public static Timer timerRecord;
	Timer timerCount;
	
	boolean isRecording = false;
	
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
	
	public void prepareGUI() {						// method to prepare the recording GUI
		setSize(width, height);
		setLayout(null);
		setTitle("Recording Controls");
		
		startButton.setBounds(25, 25, 95, 30);
		stopButton.setBounds(25, 60, 95, 30);
		delayBox.setBounds(350, 25, 100, 20);
		separator.setOrientation(SwingConstants.HORIZONTAL);
		separator.setBounds(0, 120, 500, 1);
		recordState.setBounds(25, 150, 200, 20);
		recordTime.setBounds(25, 180, 200, 20);
		timer.setBounds(25, 210, 200, 20);

		add(startButton);
		add(stopButton);
		add(delayBox);
		add(separator);
		add(recordState);
		add(recordTime);
		add(timer);

		startButton.addActionListener(new java.awt.event.ActionListener() {		// makes StartButton listen for
			public void actionPerformed(java.awt.event.ActionEvent evt) {		// button press
				buttonStartRecordingActionPerformed(evt);
			}
		});
		
		stopButton.addActionListener(new java.awt.event.ActionListener() {		// makes StopButton listen for
			public void actionPerformed(java.awt.event.ActionEvent evt) {		// button press
				buttonStopRecordingActionPerformed(evt);
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void buttonStartRecordingActionPerformed(java.awt.event.ActionEvent evt) {	// starts recording
		if (!isRecording) {
			
			initEncoder("vod_recording_test");
		
			int delay = 1000 / 24;
		
			RecordTimer.reset();
		
			timerRecord = new Timer("Thread TimerRecord");
		
			timerCount = new Timer("Thread TimerCount");
		
			recorderTask = new ScreenRecorderTask(encoder, rectangle);
		
			countTask = new TimerCountTask(timer);
		
			timerRecord.scheduleAtFixedRate(recorderTask, 0, delay);
			timerCount.scheduleAtFixedRate(countTask, 0, 1000);
		
			recordState.setText("Recording State: Recording...");
		}
		
		isRecording = true;
	}
	
	private void buttonStopRecordingActionPerformed(java.awt.event.ActionEvent evt) {		// stops recording
		if (isRecording) {
			RecordTimer.stop();
			
			recordState.setText("Recording Stopped");
			recordTime.setText("" + RecordTimer.getTimeInSec());
			
			timerCount.cancel();
			timerCount.purge();
			
			timerRecord.cancel();
			timerRecord.purge();
			
			recorderTask.cancel();
			countTask.cancel();
			
			try {
				encoder.finish();
				System.out.println("encoding finish " + (RecordTimer.getTimeInSec()) + "s");
				recordState.setText("Recording State: Finished!");
				recordTime.setText("" + RecordTimer.getTimeInMin() + "min");
			} catch (IOException ex) {
				Logger.getLogger(MainScreenRecorderFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		isRecording = false;
	}
	
	public void initEncoder(String filename) {										// initializes the encoder
		rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());		// gets size of screen
		
		file = new File(filename + ".mp4");											// creates a File object to store
		
		try {
			encoder = AWTSequenceEncoder.createSequenceEncoder(file, 24/4);			// creates the encoder
		} catch (IOException ex) {
			Logger.getLogger(MainScreenRecorderFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
