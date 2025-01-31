package dev.sentomero.amsIdea.repository;

import dev.sentomero.amsIdea.model.KpClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KpClientRepository extends JpaRepository<KpClient,Integer> {
    KpClient findByKpClientSerialNumber(long serialNumber);
}
