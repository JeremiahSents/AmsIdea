package dev.sentomero.amsIdea.repository;

import dev.sentomero.amsIdea.model.AmsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmsUserRepository extends JpaRepository<AmsUser,Integer> {
}
