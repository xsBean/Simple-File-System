import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class simulationFlatDirectoryStruc {

	List<simulationFile> directory;
	public simulationFlatDirectoryStruc() {
		// TODO Auto-generated constructor stub
		
		this.directory = Collections.synchronizedList(new ArrayList<simulationFile>()); // return thread-safe list including synchronized		
	}
	
	public void addFile(simulationFile file){
		directory.add(file);
	}
	public void removeFile(simulationFile file){
		directory.remove(file);
	}
	public simulationFile getFile(String name){
		for(simulationFile file : directory)
			if(file.getFileName().equals(name))
				return file;		
		
		return null;
	}
	public boolean checkExistFile(String fileName){
		for(simulationFile fi : directory){
			if(fi.getFileName().equals(fileName))
				return true;
		}		
		return false;
	}
	public List<simulationFile> getDirectory(){
		return directory;
	}
	public void dir(){
		System.out.println("List of file in directory: ");
		if(directory.isEmpty())
			System.out.println("Empty. No file in directory. ");
		else
			for(simulationFile file : directory)
				System.out.println("File name: "+file.getFileName()+"\tFile size: "+file.getFileSize());
	}
	
}
