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
		if (file.isDirectory()){
			for (File f: file.listFiles()){
				if (!f.isDirectory()){
					String filePath=f.getAbsolutePath();
					syncList.add(new FileToSync(filePath.substring(this.path.length(),filePath.length()), new Date(f.lastModified())));
				}else{
					getFileList(f.getAbsolutePath());
				}
			}	
		}else{
			String filePath=file.getAbsolutePath();
			syncList.add(new FileToSync(filePath.substring(this.path.length(),filePath.length()), new Date(file.lastModified())));
		}
		
		return syncList;
	}
	
	public static void main(String ... arg){
		LocalFileList l= new LocalFileList("d:\\");
		l.getFileList(l.path);
		System.out.println(l.path);
		System.out.println(syncList);
	}
}
