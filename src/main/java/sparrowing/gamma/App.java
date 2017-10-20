package sparrowing.gamma;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import sparrowing.gamma.bot.GammaBot;

public class App {
	
	private static Properties properties;
	
	public static Properties properties() {
		return properties;
	}
	
    public static void main(String[] args) {
    	
		properties = new Properties();
		InputStream fileInput = null;
		
		// Attempt to load properties
		try {
			fileInput = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/sparrowing/gamma/config.properties");
			properties.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			
			if (fileInput != null) {
				
				try {
					fileInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Create bot
		@SuppressWarnings("unused") GammaBot bot = new GammaBot(properties);
    }
    
}
