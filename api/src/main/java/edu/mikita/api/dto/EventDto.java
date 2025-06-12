package edu.mikita.api.dto;

import java.io.Serializable;

public record EventDto(String id, String title, String description) implements Serializable {
}
