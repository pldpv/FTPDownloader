package ftpClient.settings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class RemoteFileList extends FileList{
	private static FTPClient ftpClient=null;
	private static List<FileToSync> syncList=new ArrayList<FileToSync>();;
	RemoteFileList(String path,FTPClient ftpClient) {
		super(path);
		this.ftpClient=ftpClient;
	}

	@Override
	List<FileToSync> getFileList(String path) {
		try {
			for(FTPFile file: ftpClient.listFiles(path)){
				if (file.isFile()){
					syncList.add(new FileToSync(path.substring(this.path.length())+"/"+file.getName(), new Date(file.getTimestamp().getTimeInMillis())));
				}else if(file.isDirectory()){
					getFileList(path+"/"+file.getName());
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return syncList;
	}
	public static void main(String ... arg) throws FileNotFoundException, IOException{
		RemoteFileList rem= new RemoteFileList("/pv/2014", new FTPConnection().connect());
		long time=System.currentTimeMillis();
		List<FileToSync> list=rem.getFileList(rem.path);
		

		for (FileToSync f: list){
			System.out.println(f);
		}
		System.out.println(System.currentTimeMillis()-time);
		System.out.println(list.size());
	}
}
