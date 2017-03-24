//import java.util.List;

public class simulationDisk {

	volumeControlBlock vcb;
	systemWideFileTable sWFT;
	simulationFlatDirectoryStruc fds;
	perProcessOpenTable pPOT;
	public simulationDisk() {
		// TODO Auto-generated constructor stub
		this.vcb = new volumeControlBlock();
		this.sWFT = new systemWideFileTable();
		this.fds = new simulationFlatDirectoryStruc();
		this.pPOT = new perProcessOpenTable();
	}	
	//Create function:
		// when creating a file follow those steps below:
		// 1: check file name to make sure we don't have the same file name in directory
		// 2: find space in simulation disk
		// 3: make entry in directory: update FCB(name, start free block and size) of file and store file in directory	
	public boolean create(simulationFile newFile){
		String fileName= newFile.getFileName();
		// check file name in directory. If file name is existed return false, else continue	
		try {			
		if(!fds.checkExistFile(fileName)){
			
			// find space in the simulation disk base on file size. -1 mean there are no space in simulation disk
				int startBlock = vcb.getFreeBlockStart(newFile.getFileSize());
				if(startBlock == -1)
					return false;
				else{
					// make entry in directory
					boolean[] blockArray = vcb.getFreeBlockArray();
					for(int i = 0; i < newFile.getFileSize();i ++){
						blockArray[startBlock+i] = false;
					}
					//update FCB to file
					newFile.setStartBlockNumber(startBlock);
					newFile.setFileControlBlock();
					
					// add file to directory
					fds.addFile(newFile);					
					System.out.println("Create file "+fileName+ " successful.");					
					return true;
				}
		}else{
			System.out.println("Fail! Same File name exist in directory.");
			return false;
			}
			
		} catch (Exception e) {
			System.out.println("Some error happen in create function");
			return false;
		}			
	}
	
	//Open function:
		// open File
		// 1. First, should search in system-wide open-file table to see whether that file is opened.
		// if it is: a process open-file table is created pointing to system-wide open-file table
		// else: directory will search for the given file name. Once file is found then copy the FCB to system-wide open-file table
	public boolean open(simulationFile file){
		String fileName = file.getFileName();

		// check whether file is opening in per process file-open table
		if(pPOT.checkOpen(fileName)){
			return true;
//		}
//			
//		// check whether file is opening in system-wide open-file table or not.		
//		if(sWFT.checkFileExist(fileName) == true){
//			//System.out.println("File is already opened.");
//			return true;
		}else{
			
			// get file info from flat directory then add to file system-wide open-file table
			// Check whether file is existed on directory
			if(fds.checkExistFile(fileName)){
				sWFT.addOpenedFile(file);				
				System.out.println("Openning file: "+ fileName);
//				return true;

				//Track the number of processes that have the file open: update perProcess open-file table
				int index = sWFT.getIndex(file);
				threadFile tFile = new threadFile(fileName,index);
				pPOT.addProcessToOpenTable(tFile);				
				return true;
			}
			else {
				System.out.println("File is not existed in the directory. ");
				return false;
			}
		}	
	}
	
	//Write function:
		// Check whether that file is open or not. If not then open file. 
		// If yes, base on the last write index of content of that file to continue writing to that file	
	public void write(simulationFile file, String c){
		
		try {			
//		boolean check = sWFT.checkFileExist(file.getFileName()); // check whether file is opening or not by accessing directly to system wide file table
		boolean check = pPOT.checkOpen(file.getFileName()); //// check whether file is opening or not by accessing indirectly via per process open-file table
		if(check){
			System.out.println("Write to file: "+file.getFileName());
			file.setFileContent(c); // Write to file
				
		}else{
			System.out.println("Because file is not open so we have to open before writing to file.");
			open(file);
			write(file, c);
		}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Fail, cannot write to file.");	
		}		
	}
	
	//Close function:
		// close a file: check file name is system-wide open-file table  
		// remove file control block in system-wide open-file table and per-process open-file table
		
	public void close(simulationFile file){

		String fileName = file.getFileName();
//		boolean check = sWFT.checkFileExist(file.getFileName()); // check whether file is opening or not by accessing directly to system wide file table
		boolean check = pPOT.checkOpen(fileName); //// check whether file is opening or not by accessing indirectly via per process open-file table		
		if(check){
			
			System.out.println("Close file: "+fileName);
			sWFT.removeOpenedFile(file); //remove from system-wide open-file table
			
			// remove from per-process open-file table
			
			
		}else
			System.out.println("Fail. File is not open.");
		
	}
	
	//Read function:
		// To read: make sure the file is open, if not: open file
		// return content of file
	public void read(simulationFile file){

		try {		

//		boolean check = sWFT.checkFileExist(file.getFileName()); // check whether file is opening or not by accessing directly to system wide file table
		boolean check = pPOT.checkOpen(file.getFileName()); //// check whether file is opening or not by accessing indirectly via per process open-file table	
		if(check){					
			System.out.println(file.getFileContent());
		}else{
			System.out.println("Need open file before reading.");
			open(file);
			read(file);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Fail to read file: "+file.getFileName());	
			}
		
	}
	
	// Display all file in flat directory
	public void dir(){
		fds.dir();
	}
	
	//Delete function:
		// check file exist
		// check close file
		// delete file in directory
		// release all file space 
		// erase directory entry 
	public void delete(simulationFile file){

//		System.out.println("\nDeleting process.");
		System.out.println("Deleting file: "+file.getFileName());
		
		// check file exist
		if(fds.checkExistFile(file.getFileName())){
		
		// check open file. Need to close file before deleting
			if(sWFT.checkFileExist(file.getFileName()))
				close(file);
			
			// remove from directory
			fds.removeFile(file);
		}
		else
			System.out.println("File is not exist.");
		
	}
	public String displayDiskInfo(){
		
		return "Number of block: "+vcb.getNumberOfBlock()+"\nSize of block: "+vcb.getSizeOfBlock();
	}
	public void displaysWFT(){
		sWFT.displaySWFT();
	}
}
