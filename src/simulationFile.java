
public class simulationFile {

//	int fileIndex;
	String fileName;
	int fileSize;
	String fileContent;

//	String fileType;
	int startBlockNumber;
	fileControlBlock fcb;
	public simulationFile(String name, int size, String content) {
		// TODO Auto-generated constructor stub
		this.fileName = name;
		this.fileSize = size;
		this.fileContent = content;
	}
	public String getFileName(){
		return fileName;
	}
	public int getFileSize(){
		return fileSize;
	}
	public fileControlBlock getFCB(){
		return fcb;
	}
	public void setStartBlockNumber(int start){
		this.startBlockNumber = start;
	}
	public void setFileControlBlock(){
		this.fcb = new fileControlBlock(fileName,fileSize,startBlockNumber);
	}
	public String getFileContent(){
		return fileContent;
	}
	public void setFileContent(String newContent){
		String a = fileContent + " " +newContent;
		this.fileContent = a;		
	}

}
