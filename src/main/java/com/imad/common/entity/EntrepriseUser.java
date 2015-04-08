package com.imad.common.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.json.JSONObject;

/**
 *
 * @author ibakli
 */
@Entity
@Table(name = "entreprise_users")
@NamedQueries({
		@NamedQuery(name = "EntrepriseUsers.findAll", query = "SELECT e FROM EntrepriseUser e"),
		@NamedQuery(name = "EntrepriseUsers.findByUserId", query = "SELECT e FROM EntrepriseUser e WHERE e.userId = :userId"),
		@NamedQuery(name = "EntrepriseUsers.findByUsername", query = "SELECT e FROM EntrepriseUser e WHERE e.username = :username"),
		@NamedQuery(name = "EntrepriseUsers.findByFirstname", query = "SELECT e FROM EntrepriseUser e WHERE e.firstname = :firstname"),
		@NamedQuery(name = "EntrepriseUsers.findByLastname", query = "SELECT e FROM EntrepriseUser e WHERE e.lastname = :lastname"),
		@NamedQuery(name = "EntrepriseUsers.findByPassword", query = "SELECT e FROM EntrepriseUser e WHERE e.password = :password"),
		@NamedQuery(name = "EntrepriseUsers.findByEmail", query = "SELECT e FROM EntrepriseUser e WHERE e.email = :email"),
		@NamedQuery(name = "EntrepriseUsers.findByLocked", query = "SELECT e FROM EntrepriseUser e WHERE e.locked = :locked"),
		@NamedQuery(name = "EntrepriseUsers.findByEnabled", query = "SELECT e FROM EntrepriseUser e WHERE e.enabled = :enabled"),
		@NamedQuery(name = "EntrepriseUsers.findByAttempts", query = "SELECT e FROM EntrepriseUser e WHERE e.attempts = :attempts"),
		@NamedQuery(name = "EntrepriseUsers.findByPasswordToChange", query = "SELECT e FROM EntrepriseUser e WHERE e.passwordToChange = :passwordToChange"),
		@NamedQuery(name = "EntrepriseUsers.findByEndPassword", query = "SELECT e FROM EntrepriseUser e WHERE e.endPassword = :endPassword") })
public class EntrepriseUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "user_id")
	private Integer userId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "username")
	private String username;
	@Size(max = 45)
	@Column(name = "firstname")
	private String firstname;
	@Size(max = 45)
	@Column(name = "lastname")
	private String lastname;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "password")
	private String password;
	// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	// message="Invalid email")//if the field contains email address consider
	// using this annotation to enforce field validation
	@Size(max = 45)
	@Column(name = "email")
	private String email;
	@Size(max = 5)
	@Column(name = "locked")
	private String locked;
	@Size(max = 5)
	@Column(name = "enabled")
	private String enabled;
	@Column(name = "attempts")
	private Integer attempts;
	@Size(max = 5)
	@Column(name = "passwordToChange")
	private String passwordToChange;
	@Column(name = "endPassword")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endPassword;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "userId")
	private Collection<UsersRights> usersRightsCollection;

	public EntrepriseUser() {
	}

	public EntrepriseUser(Integer userId) {
		this.userId = userId;
	}

	public EntrepriseUser(Integer userId, String username, String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public String getPasswordToChange() {
		return passwordToChange;
	}

	public void setPasswordToChange(String passwordToChange) {
		this.passwordToChange = passwordToChange;
	}

	public Date getEndPassword() {
		return endPassword;
	}

	public void setEndPassword(Date endPassword) {
		this.endPassword = endPassword;
	}

	public Collection<UsersRights> getUsersRightsCollection() {
		return usersRightsCollection;
	}

	public void setUsersRightsCollection(
			Collection<UsersRights> usersRightsCollection) {
		this.usersRightsCollection = usersRightsCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userId != null ? userId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof EntrepriseUser)) {
			return false;
		}
		EntrepriseUser other = (EntrepriseUser) object;
		if ((this.userId == null && other.userId != null)
				|| (this.userId != null && !this.userId.equals(other.userId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("userid", userId);
		json.put("username", username);
		json.put("name", firstname + " " + lastname);
		String rights = "";
		for (UsersRights right : usersRightsCollection) {
			rights += right.getProfileId().getProfileName() + " ";
		}
		json.put("roles", rights);
		json.put("enabled", enabled);
		json.put("locked", locked);
		return json.toString();
	}

}
