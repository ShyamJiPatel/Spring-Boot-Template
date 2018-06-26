package org.shyam.security.repository;

import org.shyam.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT u FROM User u WHERE u.email = :username")
	User findByUsername(@Param("username") String username);
}
