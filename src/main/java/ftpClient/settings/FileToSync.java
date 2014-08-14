package ftpClient.settings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileToSync {
	
	private String filePath;
	private Date modifiedDate;
	
	public FileToSync(String filePath, Date modifiedDate) {
		this.filePath = filePath;
		this.modifiedDate = modifiedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filePath == null) ? 0 : filePath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileToSync other = (FileToSync) obj;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.before(other.modifiedDate))
			return false;
		return true;
	}
	public String toString(){
		return "Path:"+filePath+" Date:" +modifiedDate; 
	}
	public static void main(String ...a){
		List<FileToSync> remote=new ArrayList<FileToSync>();
		List list =new ArrayList(remote);
		
		remote.add(new FileToSync("pv/pz", new Date("10/08/2014")));
		remote.add(new FileToSync("pv/pz.zip", new Date("10/08/2014")));
		remote.add(new FileToSync("pv/pz.rar", new Date("10/08/2014")));
		List<FileToSync> local=new ArrayList<FileToSync>();
		local.add(new FileToSync("pv/pz", new Date("11/08/2014")));
		local.add(new FileToSync("pv/pz.zip", new Date("09/08/2014")));
		local.add(new FileToSync("pv/", new Date("10/08/2014")));
		System.out.println("Remote "+remote);
		System.out.println("Local "+local);
		remote.removeAll(local);
		System.out.println(remote);
	}
}