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
public class Surplus {
    private UUID id;
    private Batch batch;
    private Float original_quantity;
    private Float received_quantity;
    private Float surplus_quantity;
    private Date completion_date;
    private Date impression_date;
}
