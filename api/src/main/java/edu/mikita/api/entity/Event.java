package edu.mikita.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event implements Serializable {
    @Id
    private String id;
    private String title;
    private String description;
}
