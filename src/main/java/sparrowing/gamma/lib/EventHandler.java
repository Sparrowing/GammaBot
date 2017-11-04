package sparrowing.gamma.lib;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventHandler extends ListenerAdapter {
	
	protected Bot bot;
	
	public EventHandler(Bot bot) { this.bot = bot; }
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		Message message = event.getMessage();
		MessageHandler handler = bot.messageHandler();
		handler.processMessage(message);
	}

}
