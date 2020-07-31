package br.com.gianvittorio.data.model.repository;

import br.com.gianvittorio.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u from User u WHERE u.userName =:userName")
    User findByUsername(@Param("userName") String userName);
}
