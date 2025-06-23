package com.rodrigo.fretecalculator.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShippingByState {
    RS(0),
    SC(1),
    PR(2),
    SP(3),
    RJ(4),
    MG(4),
    ES(5),
    MS(3),
    MT(4),
    GO(4),
    DF(5),
    BA(4),
    SE(5),
    AL(5),
    PE(5),
    PB(6),
    RN(6),
    CE(6),
    PI(5),
    MA(5),
    TO(5),
    PA(4),
    AP(5),
    AM(5),
    RO(5),
    AC(6),
    RR(6);

    private final double basePrice;

    public static double calculateShipping(String uf, double totalWeight) {
        ShippingByState state = ShippingByState.valueOf(uf.toUpperCase());
        return state.getBasePrice() * totalWeight;
    }
}
