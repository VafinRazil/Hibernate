package rvafin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rvafin.converter.BirthdayConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class PersonalInfo{

    private String firstname;
    private String lastname;
    @Column(name = "birth_date")
    @Convert(converter = BirthdayConverter.class)
    private Birthday birthDate;
}
