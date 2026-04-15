package com.app.production.order.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    private UUID id;
    private Batch batch;
    private String received_user;
    private Date received_date;
    private Float received_quantity;
    private Float last_quantity;
    private String submitted_user;
    private Date submitted_date;
    private String submitted_observation;
    private Float missing_quantity;
}
