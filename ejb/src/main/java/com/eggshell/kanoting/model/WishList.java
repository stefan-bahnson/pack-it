package com.eggshell.kanoting.model;

import com.eggshell.kanoting.model.parents.ListEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@Table(name = "wishlist")
public class WishList extends ListEntity {

}