package dev.sentomero.amsIdea.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "kp_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // Unique ID for each category

    @Column(name = "name", unique = true, nullable = false)
    private String name;  // Category name (e.g., Breastfeeding)

    @OneToMany(mappedBy = "categoryRegistered", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KpClient> clients;
}
