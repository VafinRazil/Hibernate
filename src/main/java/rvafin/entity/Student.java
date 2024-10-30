package rvafin.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@ToString(exclude = "course")
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private StudentProfile studentProfile;
}
