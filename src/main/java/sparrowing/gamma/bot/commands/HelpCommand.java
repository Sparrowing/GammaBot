package sparrowing.gamma.bot.commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import sparrowing.gamma.bot.GammaBot;
import sparrowing.gamma.lib.Command;

public class HelpCommand extends Command {
	
	private String name = "help";
	private String description = "Get description of other commands.";

	public HelpCommand(GammaBot bot) {
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
