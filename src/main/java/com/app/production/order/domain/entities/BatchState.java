package com.app.production.order.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BatchState {
    not_programed("Orden sin programar"),
    unprogrammed("Orden no programable/materia prima"),
    programed("Orden programada"),
    weighed("Orden pesada"),
    fabricated("Orden fabricada"),
    packaged("Orden empaquetada"),
    stored("Orden enviada para almacenar"),
    pending("Orden pendiente"),
    completed("Orden completada");

    private final String description;
}
