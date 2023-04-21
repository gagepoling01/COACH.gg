package Senior.Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class APIServiceTest {
	
	APIService api = new APIService();


	@Test
	public void testGetCurrentMatch() { // need to refresh API key and enter name when you wish to test
		
		assertNotNull(api.getCurrentMatchID("fakeUser"));
	}
	
	@Test
	public void testGetMatchTimeline() {
		
		assertNotNull(api.getMatchTimeline(api.getCurrentMatchID("fakeUser")));
	}
}
