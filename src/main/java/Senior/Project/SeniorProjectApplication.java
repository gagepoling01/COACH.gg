package Senior.Project;

import java.awt.AWTException;
import java.io.IOException;

public class SeniorProjectApplication {
	
	public static void main(String[] args) throws AWTException, IOException {
		
		MainScreenRecorderFrame mainScreenRecorderFrame = new MainScreenRecorderFrame(500, 300);
		mainScreenRecorderFrame.prepareGUI();
		
	}
}
