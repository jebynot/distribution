package com.vsc.services.authentication.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.vsc.services.authentication.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private Environment environment;

	@Override
	public Boolean authenticate(String userName, String password) {
		String name = environment.getProperty("ID");
		String pwd  = environment.getProperty("password");
		if(name.equals(userName) && pwd.equals(password))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

}
