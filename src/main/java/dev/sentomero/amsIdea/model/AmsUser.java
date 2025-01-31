package dev.sentomero.amsIdea.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Ams_User")

public class AmsUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "Ams_Fname")
    private String Fname;

    @Column(name = "Ams_Lname")
    private String Lname;

    @Column(name = "Ams_Email",unique = true,nullable = false)
    private String Email;

    @Column(name = "Ams_Password" ,unique = true,nullable = false)
    private String Password;

    @Column(name = "Time_login")
    private LocalDateTime Timestamp;

    @Column(name = "Id_assigned" ,unique =true)
    private int IdAssigned;
}
