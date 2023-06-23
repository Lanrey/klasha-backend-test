package com.klasha.countries.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response<T> {
    @JsonProperty("data")
    private T data;
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("message")
    private String message;
}
