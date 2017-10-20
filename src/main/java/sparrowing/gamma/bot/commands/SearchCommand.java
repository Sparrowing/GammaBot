package sparrowing.gamma.bot.commands;

import java.io.IOException;
import java.util.List;

import com.google.api.services.youtube.model.SearchResult;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import sparrowing.BotLib.classes.Bot;
import sparrowing.BotLib.classes.Command;
import sparrowing.gamma.modules.youtube.Search;

public class SearchCommand extends Command {
	
	private String name = "search";
	private String description = "Search YouTube videos!";

	public SearchCommand(Bot bot) {
		super(bot);
	}
	
	@Override
	public String name() {
		return name;
	}

	@Override
	public String description() {
		return description;
	}

	@Override
	public void execute(String[] args, User author, MessageChannel channel) {
		
		// Join all 'args' as one search
		String searchQuery = String.join(" ", args);
		
		// Search
		List<SearchResult> results;
		try {
			results = Search.search(searchQuery);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		// Write a pretty response string
		// TODO Add better functionality here
		String response = "";
		for (SearchResult r : results) {
			response += "Video: ";
			response += String.format("%s https://www.youtube.com/watch?v=%s", r.getSnippet().getTitle(), r.getId().getVideoId());
			response += "\n";
		}
		
		bot.say(response, channel);
	}

}
