package com.marius.playground.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(@JsonProperty String message) {
}
