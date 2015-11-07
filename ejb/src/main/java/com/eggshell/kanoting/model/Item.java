package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.enums.ItemCategory;
import com.eggshell.kanoting.repository.ItemRepository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @NotNull
    public String name;

    public Date created;

    public Date updated;

    @Enumerated(EnumType.STRING)
    public ItemCategory itemCategory;

    @PrePersist
    private void onCreated() {
        created = new Date();
    }

    @PreUpdate
    private void onUpdated() {
        updated = new Date();
    }

}
