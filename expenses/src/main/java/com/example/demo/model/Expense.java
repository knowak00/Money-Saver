package com.example.demo.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expense {
    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;
    @NotBlank
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="sum")
    private Double sum;
}
