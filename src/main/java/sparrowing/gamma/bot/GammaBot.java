package sparrowing.gamma.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import sparrowing.BotLib.classes.Bot;
import sparrowing.BotLib.classes.Command;
import sparrowing.gamma.bot.commands.CommandsCommand;
import sparrowing.gamma.bot.commands.HelpCommand;
import sparrowing.gamma.bot.commands.PingCommand;

public class GammaBot extends Bot {
	
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
		
		return commands;
	}

}
