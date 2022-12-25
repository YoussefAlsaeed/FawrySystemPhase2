package com.projects.FawrySystem.FawrySystemAPI.refundRequestStrategy;

import com.projects.FawrySystem.FawrySystemAPI.mainPackage.User;
import com.projects.FawrySystem.FawrySystemAPI.transaction.ITransaction;

public interface IRefundRequest {
public String refund(ITransaction acceptedTransaction,User user);
}
