package com.pastillazz.f1nt3ch.users.infrastructure.entities;
import com.pastillazz.f1nt3ch.users.domain.model.Roles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name",  nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Roles roles;

}
