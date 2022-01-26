package com.golfie.user.domain;

import com.golfie.user.domain.profile.ProviderName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.socialProfile.email = :email AND u.socialProfile.providerName = :provider")
    Optional<User> findByEmailAndProviderName(@Param("email") String email, @Param("provider") ProviderName providerName);

    @Query("SELECT count(u.id) > 0 FROM User u WHERE u.basicProfile.nickname = :nickname")
    boolean existsByNickname(@Param("nickname") String nickname);
}
