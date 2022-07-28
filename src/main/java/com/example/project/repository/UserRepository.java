package com.example.project.repository;

import com.example.project.util.SqlMapper;
import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	@Query(value = "select id from user where email = ?1", nativeQuery = true)
	long getUserIdByEmail(String email);

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query(
			value = "insert into request(user_id, vaccine_id) value (?1, ?2)",
			nativeQuery = true
	)
	void createRequest(long userId, long vaccineId);
@Query(value = " select type from vaccine where id = ?1 ", nativeQuery = true)
    String getVaccineName(long vaccineId);
	@Query(value = " select v.vaccine_id from book_app v join user u on v.user_id=u.id where u.id = ?1 ", nativeQuery = true)
	long getAllVaccineForUser(long userId);


	@SuppressWarnings("deprecated")
	default boolean hasPendingNotifications() {
		return SqlMapper.existsResultToBool(__hasPendingNotifications());
	}

	@Query(
			value = "select exists(select 1 from request where id is not null)",
			nativeQuery = true
	)
	@Deprecated
	int __hasPendingNotifications();

	@SuppressWarnings("deprecated")
	default boolean hasPendingNotificationForVaccine(long id) {
		return SqlMapper.existsResultToBool(__hasPendingNotificationForVaccine(id));
	}

	@Query(
			value = "select exists(select 1 from request where user_id = ?1 )",
			nativeQuery = true
	)
	@Deprecated
	int __hasPendingNotificationForVaccine(long id);

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query(
			value = "delete from request where user_id = ?1 and vaccine_id=?2 ",
			nativeQuery = true
	)
	void deleteRequest(long userId,long vaccineId);
@Query(value = "insert into status(user_id, vaccine_id,accept) value (?1, ?2,?3)",
		nativeQuery = true)
	void addStatus(long userId,long vaccineId,String status);


@Query(value="select vaccine_id from book_app where user_id= ?1",nativeQuery = true)
	long findVaccineIdByUserId(long id);

	@Query(value="select v.type from book_app b join vaccine v on v.id=b.vaccine_id join user u on b.user_id=u.id where u.id = ?1",nativeQuery=true)
			List<String> getVaccineNames(long userId);
	@Query(value="select s.accept from status s  where s.user_id = ?1",nativeQuery=true)
	List<String> getStatuses(long userId);

	@Query(
			value = "select count(*) from status where user_id=?1",
			nativeQuery = true
	)
	int getNotification(long userId);

	@Query(
			value = "select count(*) from status where accept=?1 and user_id=?2",
			nativeQuery = true
	)
	int getCountOfStatus(String accept,long userId);

@Query(value= " select inventory from  vaccine  where id= ?1 ",
        nativeQuery=true
)
long findInventoryOfVaccine(long vaccineId,long vaccineCenterId);
	@Query(value = "update status set accept=?3 where user_id=?1 and vaccine_id=?2",
			nativeQuery = true)
	void makeStatusDone(long userId,long vaccineId,String statusDone);
	@SuppressWarnings("deprecated")
	default boolean hasReject(String reject,long userId) {
		return SqlMapper.existsResultToBool(__hasReject(reject,userId));
	}

	@Query(
			value = "select exists(select 1 from status where accept=?1 and user_id=?2)",
			nativeQuery = true
	)
	@Deprecated
	int __hasReject(String reject,long userId);


}
