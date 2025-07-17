package com.arg.user.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "employee",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.
            ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

    @OneToOne(cascade = CascadeType.
            ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
}

