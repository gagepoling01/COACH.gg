package Senior.Project;

public class RecordTimer {

	private static long startTime;
	
	private static long stopTime;
	
	public static void reset() {
		startTime = 0;
		stopTime = 0;
	}
	
	public static void start() {
		startTime = System.currentTimeMillis();
	}
	
	public static long getTimeInMillisec() {
		return stopTime - startTime;
	}
	
	public static long getTimeInSec() {
		return (stopTime - startTime) / 1000;
	}
	
	public static long getTimeInMin() {
		return (stopTime - startTime) / (60*1000);
	}
}
