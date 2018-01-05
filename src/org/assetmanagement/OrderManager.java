package org.assetmanagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderManager {

	private static OrderManager instance;
	
	private static Object lock = new Object();
	
	private String orderlocation = "c:\\temp\\orders";
	
	
	public static OrderManager getInstance()
	{
		if(instance == null)
		{
			synchronized (lock) {
			
				if(instance == null)
				{
					instance = new OrderManager();
				}
			}
		}
		return instance;
	}
	
	public void placeOrder(OrderInfo order)
	{
		int status = 1;
		if(order.getType() ==0)
		{
			InventoryManager.getInstance().returnAsset(order.getAssetname(), order.getQuantity());
			status = 2;
		}
		else
		{
			if(InventoryManager.getInstance().allocateAsset(order.getAssetname(), order.getQuantity()))
			{
				status = 2;
			}
			else
			{
				status = 3;
			}
		}
		
		order.setStatus(status);
		order.setOrderid(getOrderId());
		serialiseOrder(order);
	}
	
	private String getOrderId()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSS");
		return sdf.format(new Date(System.currentTimeMillis()));
	}
	
	private void serialiseOrder(OrderInfo asset)
	{
		
		try (ObjectOutputStream oos =
				new ObjectOutputStream(new FileOutputStream(orderlocation + "\\"+ asset.getOrderid() +".ser"))) {

			oos.writeObject(asset);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
	private OrderInfo deserialiseAsset(String asset)
	{
		OrderInfo info = null;
		try (ObjectInputStream oos =
				new ObjectInputStream(new FileInputStream(orderlocation + "\\"+ asset +".ser"))) {

			info = (OrderInfo)oos.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return info;
	}
}
