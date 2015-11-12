package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.parents.ListEntity;

import javax.persistence.*;
@Entity
@Table(name = "packlist")
@NamedQuery(name="PackList.findByUsers", query="Select p FROM PackList p WHERE p.user.id = :userId")
public class PackList extends ListEntity {}