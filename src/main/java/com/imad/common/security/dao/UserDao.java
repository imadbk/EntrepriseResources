package com.imad.common.security.dao;

import com.imad.common.entity.EntrepriseUser;

public interface UserDao {

	EntrepriseUser findByUserName(String username);

	EntrepriseUser getUserAttempts(String username);

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);
}