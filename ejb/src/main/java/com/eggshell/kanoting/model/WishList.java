package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.parents.ListEntity;

import javax.persistence.*;

@Entity
@Table(name = "wishlist")
// Daniel Laine was here
public class WishList extends ListEntity {}