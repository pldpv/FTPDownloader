package ftpClient.settings;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalFileList extends FileList {
	static List<FileToSync> syncList=new ArrayList<FileToSync>();
	LocalFileList(String path) {
		super(path);
	}

	@Override
	List<FileToSync> getFileList(String path) {
		File file=new File(path);
			for (File f: file.listFiles()){
				if (f.isFile()){
					String filePath=f.getAbsolutePath();
					syncList.add(new FileToSync(filePath.substring(this.path.length()), new Date(f.lastModified())));
				}else if (f.isDirectory()){
					getFileList(f.getAbsolutePath());
				}
			}	
		return syncList;
	}
}
