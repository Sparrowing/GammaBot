package sparrowing.gamma.bot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

import sparrowing.gamma.bot.commands.CommandsCommand;
import sparrowing.gamma.bot.commands.HelpCommand;
import sparrowing.gamma.bot.commands.PingCommand;
import sparrowing.gamma.bot.commands.SearchCommand;
import sparrowing.gamma.lib.Bot;
import sparrowing.gamma.lib.Command;

public class GammaBot extends Bot {
	
	private YouTube yt;
	
	public GammaBot(Properties properties) {
		super(properties, "=");
		
	}

	@Override
	protected List<Command> loadCommands() {
		
		// TODO Load commands from their package
		
		List<Command> commands = new ArrayList<Command>();
		
		commands.add(new PingCommand(this));
		commands.add(new HelpCommand(this));
		commands.add(new CommandsCommand(this));
		commands.add(new SearchCommand(this));
		
		return commands;
	}
	
	public YouTube getYt() {
		
		if (yt == null) {
			
			HttpTransport httpTransport = new NetHttpTransport();
			JsonFactory jsonFactory = new JacksonFactory();
			String appName = "gamma-bot-discord";
			
			return yt = new YouTube.Builder(httpTransport, jsonFactory, new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException { }
			}).setApplicationName(appName).build();
			
		} else {
			return yt;
		}
	}
	
}
