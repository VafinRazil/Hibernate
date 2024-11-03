package rvafin.entity.second;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = {"country", "user"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile", schema = "test")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    private String street;

    @OneToOne(mappedBy = "profile")
    private Userr user;
}
