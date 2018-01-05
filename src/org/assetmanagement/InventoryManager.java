package org.assetmanagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InventoryManager {

	private static InventoryManager instance;
	
	private static Object lock = new Object();
	
	private String assetlocation = "c:\\temp\\assets";
	
	
	public static InventoryManager getInstance()
	{
		if(instance == null)
		{
			synchronized (lock) {
			
				if(instance == null)
				{
					instance = new InventoryManager();
				}
			}
		}
		return instance;
	}
	
	public void addAsset(AssetInfo asset)
	{
		serialiseAsset(asset);
	}
	
	
	public boolean allocateAsset(String assetName, int quantity)
	{
		boolean isSuccess = false;
		AssetInfo info =  deserialiseAsset(assetName);
		
		if(info.getAssetcount() > quantity)
		{
			isSuccess = true;			
			info.decrementAsset(quantity);
			serialiseAsset(info);
		}
		if(!isSuccess)
		{
			System.out.println("Generate Order for new Asset with minimium quantity of " + quantity +", assetname : " + assetName);
		}
		return isSuccess;
	}
	
	public void returnAsset(String assetName, int quantity)
	{
	
		 AssetInfo info =  deserialiseAsset(assetName);
		
			info.incrementAsset(quantity);
			serialiseAsset(info);
	}
	
		
	
	private void serialiseAsset(AssetInfo asset)
	{
		
		try (ObjectOutputStream oos =
				new ObjectOutputStream(new FileOutputStream(assetlocation + "\\"+ asset.getAssetName() +".ser"))) {

			oos.writeObject(asset);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
	
	private AssetInfo deserialiseAsset(String asset)
	{
		AssetInfo info = null;
		try (ObjectInputStream oos =
				new ObjectInputStream(new FileInputStream(assetlocation + "\\"+ asset +".ser"))) {

			info = (AssetInfo)oos.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return info;
	}
}
