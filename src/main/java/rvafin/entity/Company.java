package rvafin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Важно исключать Lazy поля из методов ToString и EqualsAndHashCode,
 * так как в момент работы этих методов поля могут быть не инициализированы
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = "users")
@Builder
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    //можно использовать аннотацию JoinColumn
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        if (user != null){
            users.add(user);
            user.setCompany(this);
        }
    }
}
