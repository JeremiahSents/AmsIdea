package dev.sentomero.amsIdea.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "kp_category")
public class category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique ID for each category

    @Column(name = "name", unique = true, nullable = false)
    private String name;  // Category name (e.g., Breastfeeding)
}
