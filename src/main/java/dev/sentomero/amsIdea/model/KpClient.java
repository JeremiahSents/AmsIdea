package dev.sentomero.amsIdea.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "kp_client")
public class KpClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kpClientId;

    @Column(name = "kp_client_fname", nullable = false)
    private String kpClientFName;

    @Column(name = "kp_client_lname", nullable = false)
    private String kpClientLName;

    @Column(name = "kp_client_unique_id", unique = true, nullable = false)
    private long kpClientSerialNumber;

    @Column(name = "kp_client_date_assigned")
    private LocalDateTime kpClientTimeAssigned;

    @ManyToOne
    @JoinColumn(name = "registered_by", nullable = false)
    private AmsUser registeredBy;  // Foreign key to AmsUser

   @ManyToOne
   @JoinColumn(name = "kp_category", nullable = false)
    private Category categoryRegistered;  // Now stored as a database entity
}
