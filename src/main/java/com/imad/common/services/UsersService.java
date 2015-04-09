package com.imad.common.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.imad.common.entity.EntrepriseUser;
import com.imad.common.security.Cryptage;

@Service("usersService")
@Transactional
public class UsersService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Transactional
	public List<EntrepriseUser> getUsersAll() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("EntrepriseUsers.findAll");
		return query.list();
	}

	@Transactional
	public EntrepriseUser getUserById(Integer id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("EntrepriseUsers.findByUserId")
				.setParameter("userId", id);
		return (EntrepriseUser) query.list().get(0);
	}

	@Transactional
	public EntrepriseUser getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("EntrepriseUsers.findByUsername")
				.setParameter("username", username);
		return (query.list().size() > 0 ? (EntrepriseUser) query.list().get(0)
				: null);
	}

	@Transactional
	public void save(EntrepriseUser user) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(user)) {
			user.setPassword(Cryptage.enBCrypt(user.getPassword()));
			session.saveOrUpdate(user);
		}
	}

	@Transactional
	public void delete(EntrepriseUser user) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(user))
			session.delete(user);

	}

	@Transactional
	public void update(EntrepriseUser user) {
		Session session = sessionFactory.getCurrentSession();
		if (!StringUtils.isEmpty(user))
			session.update(user);
	}

	@Transactional
	public void updateFailAttempts(String username) {
		Session session = sessionFactory.getCurrentSession();
		EntrepriseUser users = getUserByUsername(username);
		boolean locked = false;
		if (!StringUtils.isEmpty(users)) {
			users.setAttempts(users.getAttempts() + 1);
			if (users.getAttempts() >= 3) {
				users.setLocked("" + Boolean.TRUE);
				locked = true;
			}
			session.save(users);
			if (locked)
				throw new LockedException("User Account is locked!");
		}

	}

	@Transactional
	public void resetFailAttempts(String username) {
		Session session = sessionFactory.getCurrentSession();
		EntrepriseUser users = getUserByUsername(username);
		if (!StringUtils.isEmpty(users)) {
			users.setAttempts(0);
			session.save(users);
		}
	}

	@Transactional
	public EntrepriseUser getUserAttempts(String username) {
		EntrepriseUser users = getUserByUsername(username);
		if (StringUtils.isEmpty(users)) {
			return null;
		} else {
			return users;
		}
	}

}
