package Senior.Project;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jcodec.api.awt.AWTSequenceEncoder;



public class DesktopScreenRecorder {
	
	public static void main(String[] args) throws AWTException, IOException {
		
		MainScreenRecorderFrame mainScreenRecorderFrame = new MainScreenRecorderFrame(500, 300);
		mainScreenRecorderFrame.prepareGUI();
		
		List<BufferedImage> imageList = new ArrayList<>();
		
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		
		Robot robot = new Robot();
		
		File file = new File("outputVideo.mp4");
		
		System.out.println("getting screen images...");
		
		int count = 0;
		
			while (count < 100) {
				
				BufferedImage image = robot.createScreenCapture(screenRect);
				imageList.add(image);
				count++;
			}
		makeVideoFromImages(imageList, file);
	}
	
	public static void makeVideoFromImages(List<BufferedImage> imageList, File file) throws AWTException, IOException {
		
		AWTSequenceEncoder sequenceEncoder = AWTSequenceEncoder.createSequenceEncoder(file, 25);
		
		for (int i = 0; i < imageList.size(); i++) {
			
			sequenceEncoder.encodeImage(imageList.get(i));
			
			System.out.println("list encode " + i);
		}
		sequenceEncoder.finish();
	}
}