package dev.sentomero.amsIdea.repository;

import dev.sentomero.amsIdea.model.AmsUser;
import dev.sentomero.amsIdea.model.KpClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface KpClientRepository extends JpaRepository<KpClient,Integer> {
    KpClient findByKpClientSerialNumber(long serialNumber);
    @Query("SELECT MAX(k.kpClientSerialNumber) FROM KpClient k")
    Optional<Long> findMaxSerialNumber();
    List<KpClient> findByRegisteredBy_Id(Integer userId);
    List<KpClient> findByRegisteredBy(AmsUser user);

}
