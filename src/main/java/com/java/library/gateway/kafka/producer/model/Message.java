package com.java.library.gateway.kafka.producer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

      @JsonProperty("message")
      private String message;

      private String author;
}

