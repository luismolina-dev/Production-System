package com.app.production.organization.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  TeamComposition {
    private UUID id;
    private User user;
    private Team team;
    private LocalDate startDate;
    private LocalDate endDate;
}
