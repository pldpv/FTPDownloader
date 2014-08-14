package ftpClient.settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import ftpClient.settings.ConfigurationException;

public class Settings {
	private static Properties properties=null;
	private static File settingsFile =new File(Settings.class.getClassLoader().getResource("connectionSetting.properties").getFile());
	
	public void load() throws FileNotFoundException, IOException{
		properties = new Properties();
		BufferedReader inputStr=new BufferedReader(new FileReader(settingsFile));
		properties.load(inputStr);
		inputStr.close();
	}
	public void save() throws IOException {
		BufferedWriter outStr = new BufferedWriter(new FileWriter(settingsFile));
		System.out.println(settingsFile);
		properties.store(outStr, "FTP config");
		outStr.close();
	}
	
	private void setSetting(String key, String value) {
		properties.setProperty(key, value);
	}

	private String getSetting(String key) {
		String setting=properties.getProperty(key);
		return setting;
	}

	public String getServer() {
		return getSetting("server");
	}

	public void setServer(String value) {
		setSetting("server", value);
	}

	public String getPort(){
		return getSetting("port");
	}

	public void setPort(String value) {
		if (value != null && !value.equals("")) {
			setSetting("port", value);
		} else {
			setSetting("port", "21");
		}

	}

	public String getUserName() {
		return getSetting("userName");
	}

	public void setUserName(String value) {
		setSetting("userName", value);
	}
	public String getPassword() {
		return getSetting("password");
	}

	public void setPassword(String value) {
		setSetting("password", value);
	}
	public String getPassiveMode() {
		return getSetting("passiveMode");
	}

	public void setPassiveMode(String value) {
		setSetting("passiveMode", value);
	}

}
