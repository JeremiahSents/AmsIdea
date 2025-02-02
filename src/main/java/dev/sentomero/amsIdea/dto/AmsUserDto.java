package dev.sentomero.amsIdea.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AmsUserDto {
    private String amsUserFname;
    private String amsUserLname;
    private String amsUsername;
    private String amsPassword;

    public AmsUserDto(String fname, String lname, String username, String password) {
        this.amsUserFname = fname;
        this.amsUserLname = lname;
        this.amsUsername = username;
        this.amsPassword = password;  // Don't set to empty string
    }
}
