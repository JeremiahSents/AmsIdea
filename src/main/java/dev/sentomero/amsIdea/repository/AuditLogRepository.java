package dev.sentomero.amsIdea.repository;

import dev.sentomero.amsIdea.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    // Later, we can add filtering methods (e.g., find logs by user)
}
