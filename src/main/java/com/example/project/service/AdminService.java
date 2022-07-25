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
import org.springframework.transaction.annotation.Transactional;

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
		var id = userRepo.getUserIdByEmail(principal.getName());
		return userRepo.hasPendingNotifications(id);
	}

	public List<DbNotificationResponse> getNotifications(Principal principal) {
		var userId = userRepo.getUserIdByEmail(principal.getName());
		var query =
				"select i.id, i.vaccine_center_id, b.name " +
						"from notification as i join vaccineCenter as v " +
						"    on i.vaccine_center_id = v.id " +
						"where i.user_id = " + userId;

		return jdbcTemplate.query(query, (rs, rowNum) ->
				new DbNotificationResponse(
						rs.getLong("i.id"),
						rs.getLong("i.vaccine_center_id"),
						rs.getString("v.name")
				)
		);
	}

	@Transactional
	public void acceptInvitation(Principal principal, long vaccineCenterId)
			throws
			ResourceNotFoundException
	{
		var userId = userRepo.getUserIdByEmail(principal.getName());

		if (!userRepo.hasPendingNotificationForVaccine(userId, vaccineCenterId)) {
			var msg = String.format(
					"Admin id = %d does not have an notification/request for vaccine %d",
					userId,
					vaccineCenterId
			);
			throw new ResourceNotFoundException(msg);
		}

		userRepo.deleteNotification(userId, vaccineCenterId);

	}

}

