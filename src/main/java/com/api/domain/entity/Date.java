package com.api.domain.entity;

import com.api.domain.dto.DateDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Dates")
@Table(name = "dim_date")
@NoArgsConstructor
@Data
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Long dateId;
    @Column(name = "day")
    private Integer dateDay;
    @Column(name = "month")
    private Integer dateMonth;
    @Column(name = "year")
    private Integer dateYyear;

    public Date(@Valid DateDto dateDto){
        this.dateDay = dateDto.dateDay();
        this.dateMonth = dateDto.dateMonth();
        this.dateYyear = dateDto.dateYear();

    }

}
