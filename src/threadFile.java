
public class threadFile {
	String file_name;
	int handle_index; // index in system-file open-wide table
	
	public threadFile(String fName, int index)
	{
		this.file_name = fName;
		this.handle_index = index;
	}
	
	public String getName()
	{
		return file_name;
	}
	
	public int getHandleIndex()
	{
		return handle_index;
	}
}
