package Senior.Project;

import java.util.TimerTask;

import javax.swing.JLabel;

public class TimerCountTask extends TimerTask{

	
	JLabel label;
	int timeInSec = 0;
	
	public TimerCountTask(JLabel jlabel) {
		label = jlabel;
	}
	
	@Override
	public void run() {
		label.setText(""+timeInSec+"s"); 
		
		timeInSec++;
	}
}
