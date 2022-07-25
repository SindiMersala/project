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
			value = "insert into book_app_vaccine_center (vaccineCenter_id, user_id, bookApp_id) value (?1, ?2, ?3)",
			nativeQuery = true
	)
	void addVaccineCenter(long vaccineCenterId, long userId,long bookAppId);
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query(
			value = "insert into notification(user_id, vaccine_center_id) value (?1, ?2)",
			nativeQuery = true
	)
	void createNotification(long userId, long vaccineCenterId);

	@SuppressWarnings("deprecated")
	default boolean hasPendingNotifications(long id) {
		return SqlMapper.existsResultToBool(__hasPendingNotifications(id));
	}

	@Query(
			value = "select exists(select 1 from notification where user_id = ?1)",
			nativeQuery = true
	)
	@Deprecated
	int __hasPendingNotifications(long id);

	@SuppressWarnings("deprecated")
	default boolean hasPendingNotificationForVaccine(long userId, long vaccineCenterId) {
		return SqlMapper.existsResultToBool(__hasPendingNotificationForVaccine(userId, vaccineCenterId));
	}

	@Query(
			value = "select exists(select 1 from notification where user_id = ?1 and  vaccine_id = ?2)",
			nativeQuery = true
	)
	@Deprecated
	int __hasPendingNotificationForVaccine(long userId, long vaccineId);

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query(
			value = "delete from notification where user_id = ?1 and vaccine_center_id = ?2",
			nativeQuery = true
	)
	void deleteNotification(long userId, long vaccineId);

}
