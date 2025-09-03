package com.sandy.monedas.mappers;

import com.sandy.monedas.dto.MonedaRequest;
import com.sandy.monedas.dto.MonedaResponse;
import com.sandy.monedas.entities.Moneda;

//@Component
public class MonedaMapper {

	public static Moneda toEntity(MonedaRequest req) {
        Moneda m = new Moneda();
        m.setNumCia(req.numCia());
        m.setClaveMoneda(req.claveMoneda());
        m.setDescripcion(req.descripcion());
        m.setSimbolo(req.simbolo());
        m.setEstatus(req.estatus());
        return m;
    }

    public static void updateEntity(Moneda entity, MonedaRequest req) {
        if (req.descripcion() != null)
            entity.setDescripcion(req.descripcion());
        if (req.simbolo() != null)
            entity.setSimbolo(req.simbolo());
        if (req.estatus() != null)
            entity.setEstatus(req.estatus());
    }

    public static MonedaResponse toResponse(Moneda entity) {
        return new MonedaResponse(
            entity.getNumCia(),
            entity.getClaveMoneda(),
            entity.getDescripcion(),
            entity.getSimbolo(),
            entity.getEstatus()
        );
    }

}
