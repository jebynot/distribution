package com.vsc.legacy.model.common;

import java.io.Serializable;

/**
 * @author A-3116
 *
 */
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String partnerID;
	private String transactionID;
	
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	
}
