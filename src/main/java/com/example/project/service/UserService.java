package com.example.project.service;
import com.example.project.config.SecurityConfig;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.model.*;
import com.example.project.model.request.AnswerRequest;
import com.example.project.model.request.BookAppRequest;
import com.example.project.model.request.UserCreateRequest;
import com.example.project.model.request.UserUpdateRequest;
import com.example.project.model.response.SimpleUserResponse;
import com.example.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final VaccineCenterRepository vaccineCenterRepository;
	private final VaccineRepository vaccineRepository;
	private final BookAppRepository bookAppRepository;
	private final AnswerRepository answerRepository ;
	private final ApplicationRoleRepository applicationRoleRepository;

	@Autowired
	public UserService(UserRepository userRepository,VaccineCenterRepository vaccineCenterRepository,VaccineRepository vaccineRepository,BookAppRepository bookAppRepository,AnswerRepository answerRepository,ApplicationRoleRepository applicationRoleRepository)
	{
		this.userRepository = userRepository;
		this.vaccineCenterRepository=vaccineCenterRepository;
		this.vaccineRepository=vaccineRepository;
		this.bookAppRepository=bookAppRepository;
		this.answerRepository = answerRepository;
		this.applicationRoleRepository=applicationRoleRepository;
	}


	public Optional<SimpleUserResponse> getUserResponseById(long id) {
		return userRepository.findById(id).map(SimpleUserResponse::fromEntity);
	}
	public Optional<User> getUserById(long id) {
		return userRepository.findById(id);
	}

	public Optional<User> getUserByPrincipal(Principal principal) {
		return userRepository.findByEmail(principal.getName());
	}
    public long getUserIdByEmail(Principal principal){

		return userRepository.getUserIdByEmail(principal.getName());
    }

	@Transactional
    public void createBookApp(BookAppRequest bookAppRequest, Principal principal)
			throws
			ResourceNotFoundException

	{
		var userOpt= userRepository.findByEmail(principal.getName());
		if (!userOpt.isPresent()) {
			var msg = String.format(
					"User  does not exist"
			);
			throw new ResourceNotFoundException(msg);
		                          }
		var userId = userRepository
					.getUserIdByEmail(principal.getName());
		var user=userOpt.get();

		var vaccine = vaccineRepository
					.findById(bookAppRequest.getVaccineId())
					.orElseThrow(() -> new ResourceNotFoundException("Vaccine not found"));
		var vaccineCenter = vaccineCenterRepository
				.findById(bookAppRequest.getVaccineCenterId())
				.orElseThrow(() -> new ResourceNotFoundException("Vaccine Center not found"));

		var bookApp = new BookApp();
			bookApp.setDate(bookAppRequest.getDate());
			user.addBookApp(bookApp);
			bookApp.setVaccine(vaccine);
			var inventory=userRepository.findInventoryOfVaccine(bookAppRequest.getVaccineId(),bookAppRequest.getVaccineCenterId());
		     newInventory( inventory);
			 bookApp.getVaccine().setInventory(newInventory(inventory));
			bookApp.setVaccineCenter(vaccineCenter);
			userRepository.createRequest(userId,bookApp.getVaccine().getId());
			bookAppRepository.save(bookApp);
	}

    public List<String> showUserVaccine(Principal principal)
		throws
		ResourceNotFoundException
    {
	    var userOpt= userRepository.findByEmail(principal.getName());
	     if (!userOpt.isPresent()) {
		var msg = String.format(
				"User  does not exist"
		);
		throw new ResourceNotFoundException(msg);
	                                }
		var user=userOpt.get();
		var userId = user.getId();
		return userRepository.getVaccineNames(userId);
    }

	public List<Vaccine> getAllVaccines()
	{
		return vaccineRepository.findAll();
	}
	public List<VaccineCenter> getAllVaccineCenters()
	{
		return vaccineCenterRepository.findAll();
	}

	@Transactional
	public void createQuestionForm(AnswerRequest answerRequest, Principal principal)
			throws
			ResourceNotFoundException

	{	var userOpt= userRepository.findByEmail(principal.getName());
		if (!userOpt.isPresent()) {
			var msg = String.format(
					"User  does not exist"
			);
			throw new ResourceNotFoundException(msg);
		                          }
		var user=userOpt.get();
		var answer = new Answer();
		answer.setAnswer1(answerRequest.getAnswer1());
		answer.setAnswer2(answerRequest.getAnswer2());
		answer.setAnswer3(answerRequest.getAnswer3());
		user.addAnswer(answer);
		answerRepository.save(answer);
	}

	public List<String> showStatuses(Principal principal)
			throws
			ResourceNotFoundException
	{
		var userOpt= userRepository.findByEmail(principal.getName());
		if (!userOpt.isPresent()) {
			var msg = String.format(
					"User  does not exist"
			);
			throw new ResourceNotFoundException(msg);
		}
		var user=userOpt.get();
		var userId = user.getId();
		return userRepository.getStatuses(userId);
	}

	public boolean showNotification1(Principal principal)
	{
		var out =false;
		var userId = userRepository
				.getUserIdByEmail(principal.getName());
		if (userRepository.getNotification(userId)==1)
		{
			out =true;
		}
		return out;
	}
	public boolean showNotification2(Principal principal)
	{
		var out =false;
		var userId = userRepository
				.getUserIdByEmail(principal.getName());
		if (userRepository.getNotification(userId)==2)
		{
			out =true;
		}
		return out;
	}
	public boolean showNotification3(Principal principal)
	{
		var out = false;
		var userId = userRepository
				.getUserIdByEmail(principal.getName());
		if (userRepository.getNotification(userId)==3)
		{
			out =true;
		}
		return out;
	}
	public String  showStatus1(Principal principal)
	{
		var out = "Done";
		var doze="";
		var reject="Reject";
		var userId = userRepository
				.getUserIdByEmail(principal.getName());
		if (userRepository.getCountOfStatus(out,userId)==1)
		{
			doze="The first Doze was accepted you can continue with the second one";
		}

		else
		{
			if(userRepository.getCountOfStatus(reject,userId)==1)
			{
				doze="The  Doze was rejected ";

			}
		}
		return doze;
	}
	public String  showStatus2(Principal principal)
	{
		var out = "Done";
		var doze="";
		var out2="Reject";
		var userId = userRepository
				.getUserIdByEmail(principal.getName());
		if (userRepository.getCountOfStatus(out,userId)==2)
		{
			doze="The Second Doze was accepted you can continue with the third one";
		}
		else
		{
			if(userRepository.getCountOfStatus(out2,userId)==2)
			{
				doze="The  Doze was rejected ";

			}
		}
		return doze;
	}
	public String  showStatus3(Principal principal)
	{
		var out = "Done";
		var doze="";

		var out2="Reject";
		var userId = userRepository
				.getUserIdByEmail(principal.getName());
		if (userRepository.getCountOfStatus(out,userId)==3)
		{
			doze="The Third Doze was accepted now you are protected from corona";
		}
		else
		{
			if(userRepository.getCountOfStatus(out2,userId)==3)
			{
				doze="The  Doze was rejected";

			}
		}
		return doze;
	}
	@Transactional
	public void createUser(UserCreateRequest req) throws IOException
	{
		var usr = User.fromCreateRequest(req);
		var optRoleUsr = applicationRoleRepository.findById(SecurityConfig.ROLE_USER_ID);
		if (optRoleUsr.isPresent()) {
			var roleUsr = optRoleUsr.get();

			usr.setApplicationRole(roleUsr);

			userRepository.save(usr);
		}
	}
	@Transactional
	public void updateUser(UserUpdateRequest req) {

		var opt = userRepository.findById(req.getId());
		if(opt.isPresent()){
			var usr = opt.get();
			usr.update(req);
			userRepository.save(usr);
		}
	}
	public long newInventory(long inventory){
		return inventory-1;
	}
}

