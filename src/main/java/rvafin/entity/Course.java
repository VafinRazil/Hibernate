package rvafin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "course")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<TrainerCourses> trainerCourses = new ArrayList<>();

    public void removeStudent() {
        for (Student s : students) {
            s.setCourse(null);
        }
    }
}
