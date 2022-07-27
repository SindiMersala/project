package com.example.project.service;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.model.response.DbNotificationResponse;
import com.example.project.repository.ApplicationRoleRepository;
import com.example.project.repository.UserRepository;
import com.example.project.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class AdminService {
	private final UserRepository userRepo;
	private final ApplicationRoleRepository applicationRoleRepo;

	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public AdminService(UserRepository userRepo,ApplicationRoleRepository applicationRoleRepo,JdbcTemplate jdbcTemplate)
	{
		this.userRepo = userRepo;
		this.applicationRoleRepo = applicationRoleRepo;
		this.jdbcTemplate=jdbcTemplate;
	}

	public Optional<User> getUserByPrincipal(Principal principal) {
		return userRepo.findByEmail(principal.getName());
	}
	public boolean hasPendingNotifications(Principal principal) {

		return userRepo.hasPendingNotifications();
	}


	public List<DbNotificationResponse> showNotifications(){
		var query =
				"select i.user_id, i.vaccine_id, u.first_name, v.type" +
						" from request  i join user  u " +
						" on  i.user_id = u.id  " +
						" join vaccine v " +
						" on i.vaccine_id = v.id" +
				        " where u.application_role_id = 2";
		return jdbcTemplate.query(query, (rs, rowNum) ->
				new DbNotificationResponse(
						rs.getLong("i.user_id"),
						rs.getLong("i.vaccine_id"),
						rs.getString("u.first_name"),
						rs.getString("v.type")
				)
		);
	}
String status="";

	@Transactional
	public void acceptRequest(long id)
			throws
			ResourceNotFoundException {
        var vaccineId=userRepo.findVaccineIdByUserId(id);
		if (!userRepo.hasPendingNotificationForVaccine(id)) {
			var msg = String.format(
					"Admin id = %d does not have an notification/request ",
					id
			);
			throw new ResourceNotFoundException(msg);
		}
		status="Done";
		userRepo.addStatus(id, vaccineId, status);
		userRepo.deleteRequest(id,vaccineId);
	}

	@Transactional
	public void rejectRequest(long id)
			throws
			ResourceNotFoundException {
		var vaccineId=userRepo.findVaccineIdByUserId(id);
		if (!userRepo.hasPendingNotificationForVaccine(id)) {
			var msg = String.format(
					"Admin id = %d does not have an notification/request ",
					id
			);
			throw new ResourceNotFoundException(msg);
		}
		status="Reject";
		userRepo.addStatus(id, vaccineId, status);
		userRepo.deleteRequest(id,vaccineId);
	}


}

