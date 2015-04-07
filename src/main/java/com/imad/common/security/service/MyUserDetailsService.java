package com.imad.common.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imad.common.entity.UsersRights;
import com.imad.common.security.dao.UserDao;
import com.imad.common.services.ProfilesService;
import com.imad.common.services.RightsService;
import com.imad.common.services.UsersService;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Resource(name = "rightsService")
	RightsService rightService;

	@Resource(name = "profileService")
	ProfilesService profileService;

	@Resource(name = "usersService")
	UsersService userService;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		User userDetails;
		List<UsersRights> rights;
		com.imad.common.entity.EntrepriseUser user = userDao
				.findByUserName(username);

		if (user != null) {
			rights = rightService.getUserRights(user.getUserId());
			List<GrantedAuthority> authorities = buildUserAuthority(rights);
			userDetails = buildUserForAuthentication(user, authorities);
		} else {
			if (username != null && !username.isEmpty())
				userDetails = new User(username, "", false, false, false,
						false, new HashSet<GrantedAuthority>());
			else
				throw new UsernameNotFoundException("Empty name");
		}
		return userDetails;
	}

	// Convertir ModisUsers to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(
			com.imad.common.entity.EntrepriseUser user,
			List<GrantedAuthority> authorities) {
		boolean accountNotExpired = true; // TODO review
		boolean enabled;
		boolean locked;
		try {
			enabled = Boolean.parseBoolean(user.getEnabled());
			locked = Boolean.parseBoolean(user.getLocked());
		} catch (Exception e) {
			enabled = true; // TODO review
			locked = true;
		}
		return new User(user.getUsername(), user.getPassword(), enabled,
				accountNotExpired, true, !locked, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<UsersRights> rights) {
		Set<GrantedAuthority> setAuths = new HashSet();
		if (rights != null && !rights.isEmpty()) {
			// Build user's authorities
			for (UsersRights right : rights) {
				setAuths.add(new SimpleGrantedAuthority(right.getProfileId()
						.getProfileName()));
			}
		}
		List<GrantedAuthority> Result = new ArrayList(setAuths);
		return Result;
	}
}
