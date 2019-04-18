package org.finalyearproject.repositories;

import java.util.List;

import org.finalyearproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {

	List<User> findByNameLike(String name); 

}
