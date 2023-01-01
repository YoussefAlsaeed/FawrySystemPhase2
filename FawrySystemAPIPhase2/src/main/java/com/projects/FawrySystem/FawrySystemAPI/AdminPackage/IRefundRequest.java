package com.projects.FawrySystem.FawrySystemAPI.AdminPackage;

import com.projects.FawrySystem.FawrySystemAPI.UserPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transactions.*;



public interface IRefundRequest {
public String refund(ITransaction acceptedTransaction,User user);
}
