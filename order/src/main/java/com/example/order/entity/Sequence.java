package com.example.order.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sequence {
    @Id
    private String id;
    private int sequence;
}
