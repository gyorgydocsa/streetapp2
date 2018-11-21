package hu.streetartchoir.app.repository;

import hu.streetartchoir.app.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("select u from UserAccount u where u.email = :email")
    UserAccount findByEmail(@Param("email") String email);
}
