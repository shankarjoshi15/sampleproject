package org.foldermonitor;

import java.io.File;

public class FolderMonitorHelper {

	public static long getFolderSize(File path)
	{
		long folderSize = 0;
	    File[] files = path.listFiles();
	 
	    int count = files.length;
	 
	    for (int i = 0; i < count; i++) {
	        if (files[i].isFile()) {
	        	folderSize += files[i].length();
	        }
	        else {
	        	folderSize += getFolderSize(files[i]);
	        }
	    }
		return folderSize;
	}
	
}
