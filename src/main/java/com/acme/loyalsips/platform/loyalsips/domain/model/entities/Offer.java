package com.acme.loyalsips.platform.loyalsips.domain.model.entities;

import com.acme.loyalsips.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Offer extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter

    private Long id;

    private String name;

    private String description;

    private String contentUrl;
}
