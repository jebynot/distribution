package com.vsc.ws.legacy.rs.common;

import com.vsc.context.Context;
import com.vsc.context.ContextPlaceHolder;
import com.vsc.legacy.model.common.BaseModel;

public class BaseService {
	
	public void setContextData(BaseModel model) {
		Context context = getContext();
		model.setPartnerID(context.getPartnerId());
		model.setTransactionID(context.getTransactionId());
	}
	
	
	public String getTransactionID() {
		Context context = getContext();
		return context.getTransactionId();
	}
	
	private Context getContext() {
		Context context = ContextPlaceHolder.getContext();
		return context;
	}

}
