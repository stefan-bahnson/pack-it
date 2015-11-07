package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.ejb.Stateless;


@Stateless
public class WishListRepository extends Repository {


    public WishList findWishListById(long id, long userId) {
        return find(id, userId, WishList.class);
    }

    public void addWishList(long userId, WishList wishList) {
        add(wishList);
    }

    public void updateWishList(long userId, WishList wishList) {
        update(userId, wishList);
    }

    public void deleteWishList(long userId, WishList wishList) {
        delete(userId, wishList.id, WishList.class);
    }
}
