package org.assetmanagement;

public class OrderInfo {

	private String assetname;
	
	private int quantity;
	
	private int type;
	
	private String employeename;
	
	private String orderid;
	
	//1: placed, 2: fulfilled 4: Low Inventory
	private int status;

	/**
	 * @return the employeename
	 */
	public String getEmployeename() {
		return employeename;
	}

	/**
	 * @param employeename the employeename to set
	 */
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	
	public String getStatusStr() {
		if(status ==1)
			return "in-progress";
		else if(status ==2)
			return "success";
		else 
		return "unknown";
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the assetname
	 */
	public String getAssetname() {
		return assetname;
	}

	/**
	 * @param assetname the assetname to set
	 */
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the orderid
	 */
	public String getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderInfo [assetname=").append(assetname).append(", quantity=").append(quantity).append("]");
		return builder.toString();
	}
	
	
}
