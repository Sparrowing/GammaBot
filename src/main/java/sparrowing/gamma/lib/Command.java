package sparrowing.gamma.lib;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import sparrowing.gamma.bot.GammaBot;

public abstract class Command {
	
	protected GammaBot bot;
	
	public Command(GammaBot bot) { this.bot = bot; }
	
	// TODO Doc
	
	public abstract String name();
	public abstract String description();
	public abstract void execute(String[] args, User author, MessageChannel channel);

}
