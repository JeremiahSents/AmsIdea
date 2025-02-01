package dev.sentomero.amsIdea.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class KpClientDto {
    private String kpClientFName;
    private String kpClientLName;
    private int registeredBy;
    private String categoryRegistered;
    private LocalDateTime kpClientTimeAssigned;


    public KpClientDto(String kpClientFName, String kpClientLName, long kpClientSerialNumber,int registeredBy, String categoryRegistered) {
        this.kpClientFName = kpClientFName;
        this.kpClientLName = kpClientLName;
        this.registeredBy = registeredBy;
        this.categoryRegistered = categoryRegistered;
    }
}
