package sparrowing.gamma.modules.youtube;

import java.io.IOException;
import java.util.List;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import sparrowing.gamma.App;
import sparrowing.gamma.modules.youtube.core.Auth;

public class Search {
	
	private static final Long RESULT_MAX = (long)5;
	
	// TODO untested
	public static List<SearchResult> search(String searchTerm) throws IOException {
		
		YouTube.Search.List search = (Auth.yt()).search().list("id,snippet");
		
		String apiKey = App.properties().getProperty("API_KEY");
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
