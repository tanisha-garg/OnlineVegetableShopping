package com.cg.vegetable.mgmt.service;

import com.cg.vegetable.mgmt.entities.Admin;

public interface IAdminService {
	public Admin addAdmin(Admin admin);
	public Admin updateAdminDetails(Admin admin);
	public Admin removeAdmin(Admin admin);
	public Admin viewAdmin(Admin admin);
	
}
