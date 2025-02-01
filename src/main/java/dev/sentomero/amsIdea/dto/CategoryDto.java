package dev.sentomero.amsIdea.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private int id;
    private String name;

    public CategoryDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
