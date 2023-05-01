package Senior.Project;

import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Timeline;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.summoner.Summoner;


public class APIService {
	
	public long getCurrentMatchID(String summonerName) {
		Summoner summoner = Summoner.named(summonerName).withRegion(Region.NORTH_AMERICA).get();

		CurrentMatch match = CurrentMatch.forSummoner(summoner).get();
		
		System.out.println("Live Match ID: " + match.getId());
		return match.getId();
	}
	
	
	public Timeline getMatchTimeline(long matchID) {
		Timeline timeline = Timeline.withId(matchID).withRegion(Region.NORTH_AMERICA).get();
		
		System.out.println(timeline);
		return timeline;
	}
}
