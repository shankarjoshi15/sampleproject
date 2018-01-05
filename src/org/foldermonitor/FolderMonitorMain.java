package org.foldermonitor;

public class FolderMonitorMain {

	public static void main(String[] args) {
		System.out.println("Started");
		FolderManager.getInstance().monitorFolder("G:\\deletefodler\\secured", 10, "G:\\deletefodler\\archived");
		System.out.println("Ended ");
	}

}
