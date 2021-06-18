package com.example.demo.model;

import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Setter
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