package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.parents.ListEntity;

import javax.persistence.*;

@Entity
@Table(name = "wishlist")
public class WishList extends ListEntity {}