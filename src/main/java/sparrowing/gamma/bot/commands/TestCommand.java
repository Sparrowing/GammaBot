package sparrowing.gamma.bot.commands;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import sparrowing.BotLib.classes.Bot;
import sparrowing.BotLib.classes.Command;
import sparrowing.gamma.modules.youtube.Search;

public class TestCommand extends Command {

	public TestCommand(Bot bot) {
		super(bot);
	}

	@Override
	public String description() {
		return "something";
	}

	@Override
	public void execute(String[] args, User author, MessageChannel channel) {
		
		List<SearchResult> results;
		try {
			results = Search.search(args[0]);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		String response = "";
		for (SearchResult r : results) {
			response += "```";
			response += String.format("Video Title: %s", r.getSnippet().getTitle());
			response += "```\n";
		}
		
		bot.say(response, channel);
		
	}

	@Override
	public String name() {
		return "test";
	}
	
	

}
