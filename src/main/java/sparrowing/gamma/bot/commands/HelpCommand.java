package sparrowing.gamma.bot.commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import sparrowing.BotLib.classes.Bot;
import sparrowing.BotLib.classes.Command;

public class HelpCommand extends Command {
	
	private String name = "help";
	private String description = "Get description of other commands.";

	public HelpCommand(Bot bot) {
		super(bot);
	}
	
	@Override
	public String name() {
		return this.name;
	}

	@Override
	public String description() {
		return this.description;
	}

	@Override
	public void execute(String[] args, User author, MessageChannel channel) {
		
		// Requested command is whatever the first arg is
		String requestedCommand = args[0];
		
		// Check all bot commands
		for (Command c : bot.commands()) {
			
			// If command name matches requested command
			if (c.name().equals(requestedCommand)) {
				bot.say(String.format("`%s`", c.description()), channel);
				return;
			}
		}
	}

}
