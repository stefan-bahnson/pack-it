package com.eggshell.kanoting.model.entity;

import com.eggshell.kanoting.model.entity.parents.ListEntity;

import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name = "PackList.findALL",
            query = "SELECT pl FROM Packlist pl"),
    @NamedQuery(name = "PackList.findPackListByUserId",
            query = "SELECT pl FROM Packlist pl WHERE pl.user.id = :userId"),
    @NamedQuery(name="PackList.findByUsers",
            query="Select p FROM Packlist p WHERE p.user.id = :userId")
})
@Entity
@Table(name = "packlist")
public class Packlist extends ListEntity {

}