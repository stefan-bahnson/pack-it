package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.parents.ListEntity;

import javax.persistence.*;
@NamedQueries({
    @NamedQuery(name = "PackList.findALL",
            query = "SELECT pl FROM PackList pl"),
    @NamedQuery(name = "PackList.findPackListByUserId",
            query = "SELECT pl FROM PackList pl WHERE pl.user.id = :userId")

})
@Entity
@Table(name = "packlist")
@NamedQuery(name="PackList.findByUsers", query="Select p FROM PackList p WHERE p.user.id = :userId")
public class PackList extends ListEntity {}