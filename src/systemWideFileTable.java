import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
public class systemWideFileTable {

	int index = 0;
	List<fileControlBlock> openFileList;
	public systemWideFileTable(){
		this.openFileList = Collections.synchronizedList(new ArrayList<fileControlBlock>());
	}
	
	// use this functions is not optimized because we have to search system-wide table so many time, 
	// the best way is to use per process open-file table
	public boolean checkFileExist(String fileName){
		Iterator<fileControlBlock> iter = openFileList.iterator();
		fileControlBlock fcb = new fileControlBlock();

		while(iter.hasNext()){
			fcb = iter.next();

			try {
			if(fcb.getFileName().equals(fileName)){				
				return true;
			}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error in checking file exist function.");
			}			
		}
		return false;
	}
	
	// When open a file, we need to add FCB of file into System Wide File Table	
	public void addOpenedFile(simulationFile file){
		fileControlBlock fcb = file.getFCB();
		synchronized (openFileList) {
			openFileList.add(fcb);			
		}		
	}

	// get index of opening file 
	public int getIndex(simulationFile file){
		return openFileList.indexOf(file);
	}
	
	// When close a file, we need to remove FCB of file in System Wide File Table
	public void removeOpenedFile(simulationFile file){
		fileControlBlock fcb = file.getFCB();
		synchronized (openFileList) {
			openFileList.remove(fcb);
		}
	}
	
	// Display info of System Wide File Table
	public void displaySWFT(){
		
		System.out.println("Files are opening: ");
		Iterator<fileControlBlock> iter = openFileList.iterator();
		while(iter.hasNext()){
			fileControlBlock fcb = iter.next();
			System.out.println("File size: "+fcb.getFileSize()+"\tStart Data Block: "+fcb.firstDataBlock);
		}	
	}
}
