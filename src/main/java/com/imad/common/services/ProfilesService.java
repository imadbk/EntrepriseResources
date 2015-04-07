package com.imad.common.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.imad.common.entity.Profiles;

@Service("profileService")
public class ProfilesService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Profiles> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Profiles.findAll");
		return query.list();
	}

	public Profiles getProfileById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Profiles.findByProfileId").setParameter("profileId", id);
		return (Profiles) query.list().get(0);
	}

	public Profiles getProfileByRoleName(String role) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Profiles.findByProfileName").setParameter("profileName", role);
		return (Profiles) query.list().get(0);
	}
}
