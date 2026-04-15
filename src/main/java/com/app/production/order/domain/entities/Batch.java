package com.app.production.order.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch {
    private Integer batch;
    private String code;
    private String description;
    private Float quantity;
    private Date due_date;
    private Integer bin;
}
