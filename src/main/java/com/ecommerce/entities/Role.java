package com.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
