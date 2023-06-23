package com.klasha.countries.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryResponse<T> {

    private boolean error;
    private String msg;
    private T data;
}
