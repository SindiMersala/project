package com.example.project.service;
import com.example.project.exception.BadResourceException;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.model.*;
import com.example.project.model.request.AnswerRequest;
import com.example.project.model.request.BookAppRequest;
import com.example.project.model.request.NotificationRequest;
import com.example.project.model.response.SimpleUserResponse;
import com.example.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	public UserService(UserRepository userRepository,VaccineCenterRepository vaccineCenterRepository,VaccineRepository vaccineRepository,BookAppRepository bookAppRepository,AnswerRepository answerRepository)
	{
		this.userRepository = userRepository;
		this.vaccineCenterRepository=vaccineCenterRepository;
		this.vaccineRepository=vaccineRepository;
		this.bookAppRepository=bookAppRepository;
		this.answerRepository = answerRepository;
	}

	@Transactional
	public Optional<SimpleUserResponse> getUserById(long id) {
		return userRepository.findById(id).map(SimpleUserResponse::fromEntity);
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
			ResourceNotFoundException,
			BadResourceException

	{

			var userId = userRepository
					.getUserIdByEmail(principal.getName());
            var user=userRepository.findByEmail(principal.getName()).get();

			var vaccine = vaccineRepository
					.findById(bookAppRequest.getVaccineId())
					.orElseThrow(() -> new ResourceNotFoundException("Vaccine not found"));

			var bookApp = new BookApp();
			bookApp.setDate(bookAppRequest.getDate());
			user.addBookApp(bookApp);
			bookApp.setVaccine(vaccine);
		    bookApp.setVaccineCenters(bookApp.getVaccineCenters());

			bookAppRepository.save(bookApp);
		}

	public List<Vaccine> getAllVaccines() {
		return vaccineRepository.findAll();
	}
	public List<VaccineCenter> getAllVaccineCenters() {
		return vaccineCenterRepository.findAll();
	}


	@Transactional
	public void afterChoosingVaccine(long vaccineCenterId, NotificationRequest req, Principal principal)

	{
		var userId = req.getUserId();

		userRepository.createNotification(userId, vaccineCenterId);
	}

	@Transactional
	public void createQuestionForm(AnswerRequest answerRequest, Principal principal)
			throws
			ResourceNotFoundException,
			BadResourceException

	{

		var userId = userRepository
				.getUserIdByEmail(principal.getName());

		var user=userRepository.findByEmail(principal.getName()).get();
		var answer = new Answer();
		answer.setAnswer1(answerRequest.getAnswer1());
		answer.setAnswer2(answerRequest.getAnswer2());
		answer.setAnswer3(answerRequest.getAnswer3());
		user.addAnswer(answer);
		answerRepository.save(answer);
	}
}

