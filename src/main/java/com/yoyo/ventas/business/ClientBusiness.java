package com.yoyo.ventas.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yoyo.ventas.data.UserData;
import com.yoyo.ventas.domain.Address;
import com.yoyo.ventas.domain.Client;
import com.yoyo.ventas.domain.ContactInformation;

@Service
public class ClientBusiness {
	
	@Autowired
	private UserData userData;
	
	public void insertClient (Address address, ContactInformation contactInformation, Client client) {
		userData.insertClient(address, contactInformation, client);
	}//end
	
	public Client findByEmail(String email) {
		return userData.findByEmail(email);
	}//end
	
	public boolean findByPhone(String phone) {
		return userData.findByPhone(phone);
	}//end
	
	public boolean findEmail(String email) {
		return userData.findEmail(email);
	}//end
	
	public boolean findFax(String fax) {
		return userData.findFax(fax);
	}//end

}//end class
