package com.neosoft.poc1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neosoft.poc1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.firstName like %?1 or u.lastName like %?1 or u.pincode like %?1")
	List<User> searchByNameOrPincode(String searchParam);

	@Query("select u from User u order by dob,joiningDate asc ")
	List<User> sortUserByDobAndJoinDate();

}
