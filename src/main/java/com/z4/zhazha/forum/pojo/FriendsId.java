package com.z4.zhazha.forum.pojo;

import java.io.Serializable;

public class FriendsId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2920276581078454638L;
	private int zAUserId;
	private int zBUserId;




	public int getzAUserId() {
		return zAUserId;
	}




	public void setzAUserId(int zAUserId) {
		this.zAUserId = zAUserId;
	}




	public int getzBUserId() {
		return zBUserId;
	}




	public void setzBUserId(int zBUserId) {
		this.zBUserId = zBUserId;
	}


	public int hashCode()
	{
		return this.getzAUserId()+this.getzBUserId();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (obj instanceof FriendsId)
		{
			FriendsId a = (FriendsId)obj;
			if (this.getzAUserId()==a.getzAUserId()&&this.getzBUserId()==a.getzBUserId())
			{
				return true;
			}
		}
		return true;
	}
}
