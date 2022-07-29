package com.example.project.service;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.model.response.DbNotificationResponse;
import com.example.project.model.response.StatusResponse;
import com.example.project.repository.StatusRepository;
import com.example.project.repository.UserRepository;
import com.example.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AdminService {
	private final UserRepository userRepo;
	private final StatusRepository statusRepository;

	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public AdminService(UserRepository userRepo,StatusRepository statusRepository,JdbcTemplate jdbcTemplate)
	{
		this.userRepo = userRepo;
		this.statusRepository=statusRepository;
		this.jdbcTemplate=jdbcTemplate;
	}

	public Optional<User> getUserByPrincipal(Principal principal) {
		return userRepo.findByEmail(principal.getName());
	}
	public boolean hasPendingNotifications() {

		return userRepo.hasPendingNotifications();
	}


	public List<DbNotificationResponse> showNotifications()
	{
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
	public void rejectRequest(long id)
			throws
			ResourceNotFoundException
	{
		var vaccineId=userRepo.findVaccineIdByUserId(id);
		if (!userRepo.hasPendingNotificationForVaccine(id)) {
			var msg = String.format(
					"Admin id = %d does not have an notification/request ",
					id
			);
			throw new ResourceNotFoundException(msg);
		}
		String statusAccept="Accepted";
		String doneFromAccepted="Done";
        long idAccept=userRepo.findAccept(id,vaccineId, statusAccept);
		userRepo.makeStatusDoneFromAccepted( idAccept,doneFromAccepted);
		status="Reject";
		userRepo.addStatus(id, vaccineId, status);
		userRepo.deleteRequest(id,vaccineId);
	}
	@Transactional
	public void acceptRequest(long id)
			throws ResourceNotFoundException
	{
		var vaccineId=userRepo.findVaccineIdByUserId(id);
		if (!userRepo.hasPendingNotificationForVaccine(id)) {
			var msg = String.format(
					"Admin id = %d does not have an notification/request ",
					id
			);
			throw new ResourceNotFoundException(msg);
		}

if (userRepo.hasAccepted("Accepted",id,vaccineId)){
	String doneFromAccepted="Done";
	String statusAccept="Accepted";
	long idAccept=userRepo.findAccept(id,vaccineId, statusAccept);
	userRepo.makeStatusDoneFromAccepted( idAccept,doneFromAccepted);
}
		status="Accepted";
		userRepo.addStatus(id, vaccineId, status);
		userRepo.deleteRequest(id,vaccineId);
	}

	public  List<StatusResponse> getAllStatuses() {
		return statusRepository.findAll()
				.stream()
				.map(StatusResponse::fromEntity)
				.collect(Collectors.toList());
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	String statusDone="Done";
	public void makeStatusDone(long userId,long vaccineId){

		userRepo.makeStatusDone(userId,vaccineId,statusDone);
	}

}