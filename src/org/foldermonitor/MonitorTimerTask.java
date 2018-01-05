package org.foldermonitor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TimerTask;

public class MonitorTimerTask extends TimerTask {

	File monitorFolderPath;
	int maxFolderSize;
	File archiveFolderPath;
    public MonitorTimerTask(File monitorFolderPath, int size, File archiveFolderPath) {
		 this.monitorFolderPath = monitorFolderPath;
		 this.maxFolderSize = size * 1024 * 1024;
		 this.archiveFolderPath = archiveFolderPath;
	}
	
	@Override
	public void run() {
		System.out.println("Enter run() of MonitorTimerTask");
		List<String> archivedFiles = new ArrayList<String>();
		List<String> deletedFiles = new ArrayList<String>();
		long size  = FolderMonitorHelper.getFolderSize(monitorFolderPath);
		
		if(size>maxFolderSize)
		{
			long diff = size - maxFolderSize ;
			
			File[] fileList = monitorFolderPath.listFiles();
			
			Arrays.sort(fileList, new Comparator<File>() {
			    public int compare(File f1, File f2) {
			        return Long.compare(f1.lastModified(), f2.lastModified());
			    }
			});
			boolean isDel = true;
			
			for(File f: fileList)
			{
				if(f.isDirectory())
				{
					continue;
				}
				if(isDel)
				{
					long len = f.length();
					archivedFiles.add(f.getName());
					boolean renameResult = f.renameTo(new File(archiveFolderPath + File.separator + f.getName())) ;
					if(!renameResult)
					{
						f.renameTo(new File(archiveFolderPath + File.separator + System.currentTimeMillis() + "_" +f.getName())) ;
					}
					System.out.println("rename Result: " + renameResult);
					diff-=len;
					if(diff<=0)
					{
						isDel = false;
					}
				}
				else 
				{
					delExecutableFile(f, deletedFiles);
				}
			}
		}
		else
		{
			File[] fileList = monitorFolderPath.listFiles();
			
			for(File f: fileList)
			{
			    delExecutableFile(f, deletedFiles);
			}
		}
		
		System.out.println("**** Summary ****");
		System.out.println("Folder Size Before Archive :" + (float)(size /1024/1024) + " MB");
		System.out.println("Folder Size After Archive :" + (float)(FolderMonitorHelper.getFolderSize(monitorFolderPath) /1024/1024) + " MB");
		System.out.println("Files Archived " + archivedFiles);
		System.out.println("Files Archived Count" + archivedFiles.size());
		System.out.println("Files Deleted " + deletedFiles);
		System.out.println("**** End Summary ****");
		System.out.println("Exit run() of MonitorTimerTask");
	}
	
	private void delExecutableFile(File f, List<String> deletedFiles)
	{
		if (f.getName().toLowerCase().endsWith(".bat") || f.getName().toLowerCase().endsWith(".sh"))
		{
			deletedFiles.add(f.getName());
			f.delete();
		}
	}

}
