package com.test.project.internship.TestApplication.repository;
import com.test.project.internship.TestApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    Boolean existsByEmail(String email);

    User findByName(String name);
}
