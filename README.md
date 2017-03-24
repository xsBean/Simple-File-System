Project : Simulation File system.

Autor: Darren Do

------------------------------------------------------------------------------------------------	
	Classes detail:

simulationFileSystem stores:
	// main function
	// create 3 threads and implement following requirements

simulationDisk
	// store directory class
	// definition of create(), open(), write(), read(), delete(), dir(), close(),  	

volumeControlBlock
	// store info of volume: number of blocks: 512, size of blocks:2048, number of free blocks are available  

simulationFlatDirectoryStruc:
	// list of simulation files 

simulationFile
	// store file name, file size, file contents, file control block.

fileControlBlock
	// store file name, start block in directory, file size

system-wide open-file table:
	// list of FCB of file which are opening.
	
perProcess open-file table:
	// list of threadFile ( which include file name and handler )

threadFile
	// file name and handler( index in system-wide open-file table) 	

----------------------------------------------------------------------------------------------------
	Implement operation of file system: create, open, write, read, close, delete and dir (show all files in directory)

Create function:
	// when creating a file follow those steps below:
	// 1: check file name to make sure we don't have the same file name in directory
	// 2: find space in simulation disk
	// 3: make entry in directory: update FCB(name, start free block and size) of file and store file in directory
	
Open function:
	// open File
	// 1. First, should search in system-wide open-file table to see whether that file is opened.
	// if it is: a process open-file table is created pointing to system-wide open-file table
	// else: directory will search for the given file name. Once file is found then copy the FCB to system-wide open-file table

Write function:
	// Check whether that file is open or not. If not then open file. 
	// If yes, base on the last write index of content of that file to continue writing to that file	
	
Read function:
	// To read: make sure the file is open, if not: open file
	// return content of file

Close function:
	// close a file: check file name is system-wide open-file table  
	// remove file control block in system-wide open-file table and per-process open-file table
	
Delete function:
	// check file exist
	// check close file
	// delete file in directory
	// release all file space 
	// erase directory entry 

Dir function:
	// list all file's names which are storing in directory.
	

