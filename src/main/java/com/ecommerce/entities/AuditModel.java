package com.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@MappedSuperclass
public abstract class AuditModel {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate dateCreated;


    @LastModifiedDate
    @Column(nullable = false)
    private LocalDate dateUpdated;


    @Column(name = "deleted")
    @Enumerated(EnumType.STRING)
    private YesNo deleted;

    @PrePersist
    public void create() {
        if (dateCreated == null) dateCreated = LocalDate.now();

        if (dateUpdated == null) dateUpdated = LocalDate.now();
        if (deleted == null) deleted = YesNo.NO;

    }

    @PreUpdate
    public void update() {
        if (dateUpdated == null) dateUpdated = LocalDate.now();
    }

}
