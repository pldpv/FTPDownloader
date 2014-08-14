package ftpClient.settings;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FTPConnection {
	private FTPClient ftpClient = null;
	
	/**
	 * 
	 * @return Connecting and logging FTPClient
	 * @throws FileNotFoundException when configuration file for FTP is not found
	 * @throws IOException
	 * @throws NumberFormatException when Port setting are invalid
	 * @throws IllegalStateException
	 * @throws FTPIllegalReplyException
	 * @throws FTPException
	 */
	
	public FTPClient connect() throws FileNotFoundException, IOException,
			NumberFormatException, IllegalStateException,
			FTPIllegalReplyException, FTPException {
		ftpClient = new FTPClient();
		Settings settings = new Settings();
		settings.load();
		ftpClient.connect(settings.getServer(),
				Integer.parseInt(settings.getPort()));
		ftpClient.setPassive(new Boolean(settings.getPassiveMode()));
		ftpClient.login(settings.getUserName(), settings.getPassword());
		return ftpClient;
	}

	public void disconnect() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException {
		if (ftpClient != null && ftpClient.isConnected())
			ftpClient.disconnect(true);
	}
}
