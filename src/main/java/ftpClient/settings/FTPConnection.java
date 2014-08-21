package ftpClient.settings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.ftp.FTPClient;

public class FTPConnection {
	private static FTPClient ftpClient = null;

	public static FTPClient connect() throws FileNotFoundException, IOException {
		if (ftpClient == null) {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("cp1251");
			ftpClient.setDataTimeout(100000);
			Settings settings = new Settings();
			settings.load();
			ftpClient.connect(settings.getServer(),
					Integer.parseInt(settings.getPort()));
			if (new Boolean(settings.getPassiveMode())) {
				ftpClient.enterLocalPassiveMode();
			}

			ftpClient.login(settings.getUserName(), settings.getPassword());
		}
		return ftpClient;
	}

	public void disconnect() throws IOException {
		if (ftpClient != null && ftpClient.isConnected())
			ftpClient.disconnect();
	}
	public static void main(String ...strings) throws FileNotFoundException, IOException{
		FTPClient ftp=connect();
	}
}
