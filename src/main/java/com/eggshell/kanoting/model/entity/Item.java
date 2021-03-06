package com.eggshell.kanoting.model.entity;

import com.eggshell.kanoting.model.enums.ItemCategory;
import com.eggshell.kanoting.model.entity.parents.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
