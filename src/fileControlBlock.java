
// include: file size
// file data blocks
public class fileControlBlock {

	String fileName;
	int fileSize;
	int firstDataBlock;
	
	public fileControlBlock() {
		// TODO Auto-generated constructor stub
	}
	public fileControlBlock(String fileName, int size, int first){
		this.fileName = fileName;
		this.fileSize = size;
		this.firstDataBlock = first;
	}
	public String  getFileName(){
		return fileName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public int getFirstDataBlock() {
		return firstDataBlock;
	}	
	
}
