package org.assetmanagement;

import java.io.Serializable;

public class AssetInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String assetName;
	
	private int assetcount;

	public AssetInfo(String assetName) {
		super();
		this.assetName = assetName;
	}

	/**
	 * @return the assetName
	 */
	public String getAssetName() {
		return assetName;
	}

	/**
	 * @param assetName the assetName to set
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	/**
	 * @return the assetcount
	 */
	public int getAssetcount() {
		return assetcount;
	}

	/**
	 * @param assetcount the assetcount to set
	 */
	public void setAssetcount(int assetcount) {
		this.assetcount = assetcount;
	}
	
	public void incrementAsset(int quantity)
	{
		this.assetcount+=quantity;
	}
	
	public void decrementAsset( int quantity)
	{
		this.assetcount-=quantity;
	}
	
}
