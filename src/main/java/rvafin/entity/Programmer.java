package rvafin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Programmer extends User{
    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder
    public Programmer(Long id, String username, PersonalInfo personalInfo, Role role, Company company, Profile profile, List<UserChat> userChats, Language language) {
        super(id, username, personalInfo, role, company, profile, userChats);
        this.language = language;
    }
}
