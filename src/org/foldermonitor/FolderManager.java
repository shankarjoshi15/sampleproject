package org.foldermonitor;

import java.io.File;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FolderManager {

	private static FolderManager instance;
	
	private static Object lock = new  Object();
	
	private static ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
	private FolderManager()
	{
		
	}
	
	
	public static FolderManager getInstance()
	{
		if(null == instance)
		{
			synchronized (lock) {
			
				if(null == instance)
				{
					instance = new FolderManager();
				}
			}
		}
		return instance;
	}
	
	
	public void monitorFolder(String monitorpath, int size, String archivepath)
	{
		File monitorFile = new File(monitorpath);
		File archiveFile = new File(archivepath);
		MonitorTimerTask monTask = new MonitorTimerTask(monitorFile, size, archiveFile);
		System.out.println("Start monitor Task for Every 5Min");
		pool.scheduleAtFixedRate(monTask,0, 5, TimeUnit.SECONDS);
		
		
	}
}
