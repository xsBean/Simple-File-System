import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


// contain all info about all open file of each process
// have pointer to system-wide open-file table

public class perProcessOpenTable {

	
	List<threadFile> threadList;
	public perProcessOpenTable(){
		threadList = Collections.synchronizedList(new ArrayList<threadFile>());
	}
	
	public void addProcessToOpenTable(threadFile tFile){		
			threadList.add(tFile);
	}
	public void removeProcessFromOpenTable(threadFile tFile){

			threadList.remove(tFile);
	}
	public void displayOpenTable(){
		
	}
	public boolean checkOpen(String name){
		
		Iterator<threadFile> iter = threadList.iterator();
		threadFile thread; 
		while(iter.hasNext()){
			thread = iter.next();
			if(thread.getName().equals(name))
				return true;
		}
		return false;
	}
}
