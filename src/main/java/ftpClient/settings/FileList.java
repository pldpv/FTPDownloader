package ftpClient.settings;

import java.util.List;

public abstract class FileList {
	String path;
	FileList(String path){
		this.path=path;
	}
	abstract List<FileToSync> getFileList(String path);
}
