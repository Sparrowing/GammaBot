package sparrowing.gamma.modules.youtube;

import java.util.List;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import sparrowing.gamma.bot.GammaBot;

public class Search {

	private static final Long RESULT_MAX = (long) 5;
	
	public static List<SearchResult> search(GammaBot bot, String searchTerm) throws Exception {
		
		YouTube yt = bot.getYt();
		
		YouTube.Search.List search = yt.search().list("id,snippet");
		
		String apiKey;
		
		try {
			apiKey = bot.getProperty("API_KEY");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to get bot's YouTube Data API key");
		}
		
		search.setKey(apiKey);
		
		search.setQ(searchTerm);
		
		search.setType("video");
		
		search.setFields("items(id,snippet(title))");
		search.setMaxResults(RESULT_MAX);
		
		SearchListResponse response = search.execute();
		List<SearchResult> resultList = response.getItems();
		
		return resultList;
	}

}
