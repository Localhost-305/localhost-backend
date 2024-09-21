package com.api.domain.entity;

import com.api.domain.dto.HourDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Hours")
@Table(name = "dim_hour")
@NoArgsConstructor
@Data
public class Hour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hour_id")
    private Long hourId;
    private Integer hour;

    public Hour(@Valid HourDto hourDto){
        this.hour = hourDto.hour();
    }
}
