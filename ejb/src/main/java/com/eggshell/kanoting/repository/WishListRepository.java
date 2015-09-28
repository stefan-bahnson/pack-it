package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 * Created by tailage on 9/19/15.
 */

@Stateless
public class WishListRepository extends Repository {

    public WishList findWishListById(long id) {
        return find(WishList.class, id);
    }

    public void addWishList(WishList wishList) {
        add(wishList);
    }

    public void updateWishList(WishList wishList) {
        update(wishList);
    }

    public void deleteWishList(WishList wishList) {
        delete(WishList.class, wishList.id);
    }
}
