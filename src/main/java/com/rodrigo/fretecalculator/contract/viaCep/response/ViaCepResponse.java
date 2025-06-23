package com.rodrigo.fretecalculator.contract.viaCep.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepResponse(
        String uf,
        String localidade,
        String estado
) {
}
