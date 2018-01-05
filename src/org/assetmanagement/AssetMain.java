package org.assetmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssetMain {

	public static void main(String[] args) {
		FileReader fw = null;
		try {
			fw = new FileReader(new File("c:\\temp\\inventory.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		try (BufferedReader br = new BufferedReader(fw)){
		
		
		String line = null;
		
			while((line=br.readLine())!=null)
			{
				//assetname  count
				String[] str = line.split(" ");
				try
				{
					AssetInfo assetinfo = new AssetInfo(str[0]);
					assetinfo.setAssetcount(Integer.parseInt(str[1]));
					InventoryManager.getInstance().addAsset(assetinfo);
				}
				catch(NumberFormatException ex)
				{
					System.out.println("Invaild Asset count.");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			fw = new FileReader(new File("c:\\temp\\orders.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		List<OrderInfo> updatedOrders = new ArrayList<OrderInfo>();
		try (BufferedReader br = new BufferedReader(fw)){
		
		
		String line = null;
		
			while((line=br.readLine())!=null)
			{
				//employeename assetname  quantity allocate/release
				String[] str = line.split(" ");
				try
				{
					OrderInfo ordI = new OrderInfo();
					ordI.setEmployeename(str[0]);
					ordI.setAssetname(str[1]);
					ordI.setQuantity(Integer.parseInt(str[2]));
					ordI.setType(Integer.parseInt(str[3]));
					
					OrderManager.getInstance().placeOrder(ordI);
					updatedOrders.add(ordI);
				}
				catch(NumberFormatException ex)
				{
					System.out.println("Invaild Asset count.");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

		try (FileWriter fr = new FileWriter(new File("c:\\temp\\orders.txt"))) {
			;
			for(OrderInfo o: updatedOrders)
			{
				fr.write(o.getEmployeename() + " "  + o.getAssetname() + " " + o.getQuantity() + " " + o.getType() + " " + o.getStatusStr() + " " + o.getOrderid() + System.lineSeparator());
			}
			
			fr.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
