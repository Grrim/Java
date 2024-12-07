package org.gfijalkowski.pba_lab_4.users.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets Citizenship
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-29T21:13:27.795420100+01:00[Europe/Warsaw]")
public enum Citizenship {
  
  PL("PL"),
  
  DE("DE"),
  
  UK("UK");

  private String value;

  Citizenship(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Citizenship fromValue(String value) {
    for (Citizenship b : Citizenship.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

