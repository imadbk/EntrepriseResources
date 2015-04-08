package com.imad.common.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imad.common.entity.EntrepriseUser;
import com.imad.common.entity.Profiles;
import com.imad.common.entity.UsersRights;
import com.imad.common.services.ProfilesService;
import com.imad.common.services.RightsService;
import com.imad.common.services.UsersService;
import com.imad.common.utils.JqGridData;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Resource(name = "rightsService")
	RightsService rightsService;

	@Resource(name = "usersService")
	UsersService usersService;

	@Resource(name = "profileService")
	ProfilesService profileService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView getAllUsers(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			model.setViewName("security/login");
		} else {
			boolean isAdmin = SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities()
					.contains(new SimpleGrantedAuthority("ADMIN"));
			if (isAdmin) {
				model.setViewName("users/usersList");
			} else {
				model.setViewName("accueil");
			}
		}

		return model;

	}

	@RequestMapping(value = "/getUsers", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void getUsers(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			response.getWriter().println();
		} else {
			boolean isAdmin = SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities()
					.contains(new SimpleGrantedAuthority("ADMIN"));
			if (isAdmin) {
				List<EntrepriseUser> list = new ArrayList<EntrepriseUser>();
				try {
					list = usersService.getUsersAll();
				} catch (Exception e) {
					e.printStackTrace();
				}
				int totalNumberOfPages = 3;
				int currentPageNumber = 1;
				int totalNumberOfRecords = list.size();
				JqGridData<EntrepriseUser> gridData = new JqGridData<EntrepriseUser>(
						totalNumberOfPages, currentPageNumber,
						totalNumberOfRecords, list);
				String resp = gridData.getJsonString();
				System.out.println(resp);
				response.getWriter().write(resp);
			}
		}

	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView add() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			model.setViewName("redirect:/login");
			return model;
		} else {
			boolean isAdmin = SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities()
					.contains(new SimpleGrantedAuthority("ADMIN"));
			if (isAdmin) {
				model.setViewName("users/register");
				return model;
			} else {
				model.setViewName("redirect:/accueil");
				return model;
			}
		}

	}

	@RequestMapping(value = "/save", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String save(HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			return "security/login";
		} else {
			boolean isAdmin = SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities()
					.contains(new SimpleGrantedAuthority("ADMIN"));
			if (isAdmin) {

				try {
					String[] roles = request.getParameterValues("roles");
					String username = request.getParameter("username");
					String lastname = request.getParameter("lastname");
					String firstname = request.getParameter("firstname");
					String email = request.getParameter("email");
					String password = request.getParameter("password");

					EntrepriseUser user = new EntrepriseUser();
					user.setEmail(email);
					user.setFirstname(firstname);
					user.setLastname(lastname);
					user.setPassword(password);
					user.setUsername(username);
					user.setLocked("false");
					user.setEnabled("true");
					user.setPasswordToChange("true");
					List<UsersRights> usersRightsCollection = new ArrayList<UsersRights>();
					usersService.save(user);
					Profiles profile;
					UsersRights userRight;
					for (String role : roles) {
						profile = profileService.getProfileByRoleName(role);
						userRight = new UsersRights();
						userRight.setProfileId(profile);
						userRight.setUserId(user);
						usersRightsCollection.add(userRight);
						rightsService.add(userRight);
					}

					redirectAttributes.addAttribute("error", false);
					redirectAttributes.addAttribute("message",
							"user.add.success");
				} catch (Exception e) {
					e.printStackTrace();
					redirectAttributes.addAttribute("error", true);
					redirectAttributes.addAttribute("message", "server.error");
					return "redirect:/info";
				}
			} else {
				return "redirect:/accueil";
			}
		}
		return "users/register";
	}
}
