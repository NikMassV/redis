package edu.mikita.api.dto;

import java.io.Serializable;
import java.util.Set;

public record UserDto(String id, String name, int age, Set<String> events) implements Serializable {
}
