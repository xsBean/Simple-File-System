
public class SimpleFileSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		simulationDisk disk = new simulationDisk();
		simulationFile file1 = new simulationFile("file1",26,"This is content of file 1."); // file size is a total number of characters of content
		simulationFile file2 = new simulationFile("file2",26,"This is content of file 2.");
//		simulationFile file3 = new simulationFile("file3",1,"This is content of file 3.");
//		simulationFile file4 = new simulationFile("file4",8,"This is content of file 4.");
	
	//	System.out.println(file1.getFileContent());
	// Create thread 1.
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (disk) {
				System.out.println("------------Thread one-------------");	

				disk.create(file1);				
				disk.write(file1,"Content after writing. ");
				disk.close(file1);
				disk.create(file2);
				disk.write(file2,"Continue writing to file2. ");
				disk.close(file2);
				
				System.out.println("------------Thread one is completed-------------");
				}
			}
		});
		t1.start();
		
	// Create thread 2
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (disk) {
				System.out.println("\n------------Thread two-------------");	
				disk.open(file1);
				disk.read(file1);				
				disk.close(file1);	
				System.out.println("------------Thread two is completed-------------");
				}
			}
		});
		t2.start();
		
	// Create thread 3
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (disk) {
				System.out.println("\n------------Thread three-------------");
				disk.open(file2);
				disk.read(file2);
				disk.close(file2);
				System.out.println("------------Thread three is completed-------------");
				}
			}
		});	
		t3.start();
	
	// Create thread 4 for delete and dir()
		Thread t4 = new Thread(new Runnable(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				synchronized (disk) {
				System.out.println("\n------------Thread four-------------");
				disk.dir();
				disk.delete(file2);
				disk.dir();
				System.out.println("\n------------Thread four is completed-------------");
				}
			}
		});
	t4.start();
	
		try{
			//t1.join();
			t2.join(); //wait until thread 1 is completed
			t3.join(); //wait until thread 2 is completed
			t4.join(); //wait until thread 3 is completed
		}catch(InterruptedException e){
			e.printStackTrace();
		}	
	}

}
