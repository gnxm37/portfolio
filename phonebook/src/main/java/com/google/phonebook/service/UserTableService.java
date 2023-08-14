package com.google.phonebook.service;

import java.util.ArrayList;


import com.google.phonebook.dao.UserTableDao;
import com.google.phonebook.dto.UserTableDto;

public class UserTableService {
	public int equalcheck(String userid, String userpassword) throws Exception{
		UserTableDao usertableDao = new UserTableDao();
		ArrayList<UserTableDto> usertabledto = usertableDao.isexistuserid();
		int check = 2;
		for(int i=0; i<usertabledto.size(); i++) {
			if(userid.equals(usertabledto.get(i).getUserid())
					&&userpassword.equals(usertabledto.get(i).getUserpassword())) {
					check = 1;
					break;
			}else {
					check = 0;
			}
		}
		return check;
	}
}

