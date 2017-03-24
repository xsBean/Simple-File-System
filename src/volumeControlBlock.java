
public class volumeControlBlock {
	// Store information of virtual Disk
	// we have 4 variables:
	int numberOfBlock = 512; // default number of block in memory is 512
	int sizeOfBlock = 2048; // default data block size is 2M
	int freeBlockStart; // number of free blocks which is used to allow user can create a new file or not
	boolean[] freeBlockArray; // store which blocks are free(set true) or in used(set false)
	
	
	//default constructor
	public volumeControlBlock() {
		// TODO Auto-generated constructor stub
		
		this.freeBlockArray = new boolean[numberOfBlock];
		this.freeBlockArray[0] = false; // store volume control block
		this.freeBlockArray[1] = false; // store flat directory 	
		for(int i = 2; i<512; i++)
			this.freeBlockArray[i] = true;
	}
	//constructor
	public volumeControlBlock(int number,int size, boolean[] array){
		
		this.numberOfBlock = number;
		this.sizeOfBlock = size;
//		this.freeBlockCount = free;
		this.freeBlockArray = array;		
	}
	
	// get & set functions
	public int getNumberOfBlock() {
		return numberOfBlock;
	}
	public void setNumberOfBlock(int numberOfBlock) {
		this.numberOfBlock = numberOfBlock;
	}
	public int getSizeOfBlock() {
		return sizeOfBlock;
	}
	public void setSizeOfBlock(int sizeOfBlock) {
		this.sizeOfBlock = sizeOfBlock;
	}
	
	// get free block start. Return -1 if we can not find any free space to store file with requirement size
	// else. return the index of free block start which is used to store file
	public int getFreeBlockStart(int fileSize) {
		freeBlockStart = 0;
		int check;
		for(int i = 0; i<numberOfBlock; i++){
			if(freeBlockArray[i] == true){  
				check = 1;
				for(int j = 1; j < fileSize; j++){
					if(freeBlockArray[i+j] == true)
						check++;
					else
						break;
					if(check == fileSize)
						return freeBlockStart;
				}
			}
			freeBlockStart++;
		}
		return -1;
	}

	public boolean[] getFreeBlockArray() {
		return freeBlockArray;
	}
	public void setFreeBlockArray(boolean[] freeBlockArray) {
		this.freeBlockArray = freeBlockArray;
	}
	
	////////////////////////////////
	
	
}
