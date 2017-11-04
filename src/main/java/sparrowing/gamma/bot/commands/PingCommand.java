package sparrowing.gamma.bot.commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import sparrowing.gamma.bot.GammaBot;
import sparrowing.gamma.lib.Command;

public class PingCommand extends Command {
	
	private String name = "ping";
	private String description = "Ping!";

	public PingCommand(GammaBot bot) {
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
		bot.say("Pong!", channel);
	}

}
