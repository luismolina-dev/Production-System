package com.app.production.order.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WineryHistory {
    private UUID id;
    private Warehouse warehouse;
    private String user;
    private Float quantity;
    private String observation;
    private Boolean submitted;
}
