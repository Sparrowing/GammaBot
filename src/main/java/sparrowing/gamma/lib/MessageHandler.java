package sparrowing.gamma.lib;

import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class MessageHandler {
	
	protected Bot bot;
	
	public MessageHandler(Bot bot) { this.bot = bot; }
	
	public void processMessage(Message msg) {
		
		// Get actual message text
		String msgText = msg.getContent();
		
		// If message doesn't start with the trigger, stop
		if ( !msgText.startsWith(bot.trigger()) ) return;
		
		// Fetch author and channel from the message object
		User           author  = msg.getAuthor();
		MessageChannel channel = msg.getChannel();
		
		// Creates array of words in the message split on spaces, minus the trigger at the beginning
		String[] parseMessage = msgText.substring(bot.trigger().length()).split(" ");
		
		// Get (supposed) command, aka the first word of the message following the trigger
		String commandText = parseMessage[0].toLowerCase();
		
		// Put all words after into args array
		String[] args = Arrays.copyOfRange(parseMessage, 1, parseMessage.length);
		
		List<Command> cmds = bot.commands();
		
		// Iterate through all the bot's commands and attempt to match the command text to one
		for (Command c : cmds) {
			
			// TODO Handle command aliases here!
			
			// Match command text to command
			if (commandText.equals(c.name())) {
				
				System.out.println("Executing command '" + c.name() + "'");
				
				try {
					c.execute(args, author, channel);
				} catch (Exception e) {
					System.err.println("Execution of command '" + c.name() + "' failed");
					e.printStackTrace();
				}
				
				// Break out of loop when command is matched
				break;
			}
		}
		
		// If it gets here, the command text didn't match any of the bot's commands
	}
}