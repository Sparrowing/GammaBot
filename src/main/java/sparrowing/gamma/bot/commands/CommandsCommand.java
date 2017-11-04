package sparrowing.gamma.bot.commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import sparrowing.gamma.bot.GammaBot;
import sparrowing.gamma.lib.Command;

public class CommandsCommand extends Command {
	
	private String name = "commands";
	private String description = "List all available commands.";

	public CommandsCommand(GammaBot bot) {
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
		
		String commandList = "";
		
		// Concatenate all command names
		for (int i = 0, size = bot.commands().size(); i < size; i++) {
			
			// Add separators
			if (i != 0) commandList += " | ";
			
			// Add command name
			commandList += bot.commands().get(i).name();
		}
		
		bot.say(String.format("`%s`", commandList), channel);
	}
	
	

}
