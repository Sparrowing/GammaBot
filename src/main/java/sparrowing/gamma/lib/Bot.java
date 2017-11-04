package sparrowing.gamma.lib;

import java.util.List;
import java.util.Properties;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.MessageChannel;

public abstract class Bot {

	// PROPERTIES ====================================================================

	@SuppressWarnings("unused")
	private static JDA jda;
	
	protected Properties properties;
	protected EventHandler eventHandler;
	protected MessageHandler messageHandler;
	protected List<Command> commands;
	
	protected String trigger;
	
	// TODO Factor out into some kind of default properties
	private static String defaultTrigger = "!";
	
	// CONSTRUCTORS ==================================================================
	
	// TODO There is DEFINITELY a better way to write these constructors
	
	public Bot(Properties properties) {
		
		this.properties = properties;
		this.eventHandler = new EventHandler(this);
		this.messageHandler = new MessageHandler(this);
		this.commands = loadCommands();
		this.trigger = defaultTrigger;
		
		start();
	}
	
	public Bot(Properties properties, EventHandler eventHandler) {
		
		this.properties = properties;
		this.eventHandler = eventHandler;
		this.messageHandler = new MessageHandler(this);
		this.commands = loadCommands();
		this.trigger = defaultTrigger;
		
		start();
		
	}
	
	public Bot(Properties properties, String trigger) {
		
		this.properties = properties;
		this.eventHandler = new EventHandler(this);
		this.messageHandler = new MessageHandler(this);
		this.commands = loadCommands();
		this.trigger = trigger;
		
		start();
	}
	
	public Bot(Properties properties, EventHandler eventHandler, String trigger) {
		
		this.properties = properties;
		this.eventHandler = eventHandler;
		this.messageHandler = new MessageHandler(this);
		this.commands = loadCommands();
		this.trigger = trigger;
		
		start();
	}
	
	// BUILT-IN METHODS ==============================================================
	
	private void start() {
		
		try {
			
			// Start the bot
			jda = new JDABuilder(AccountType.BOT)
					.setToken(getProperty("BOT_TOKEN"))
					.addEventListener(eventHandler)
					.buildBlocking();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void say(String msg, MessageChannel channel) {
		channel.sendMessage(msg).queue();
	}
	
	/**
	 * Retrieve property 'propertyName' of bot from properties file
	 * 
	 * @param propertyName  Name of desired property
	 * @return              Value of requested property from the properties file
	 * @throws Exception    Requested property does not exist
	 * 
	 */
	public String getProperty(String propertyName) throws Exception {
		
		String prop = properties.getProperty(propertyName);
		
		// TODO This should be a specific kind of exception
		if (prop == null) throw new Exception("Property does not exist.");
		
		return prop;
	}
	
	// TODO Doc
	
	public MessageHandler messageHandler() { return this.messageHandler; }
	
	public String trigger() { return this.trigger; }
	
	public List<Command> commands() { return this.commands; }
	
	// ABSTRACT METHODS ==============================================================
	
	protected abstract List<Command> loadCommands();

}
