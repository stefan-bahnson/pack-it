package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.ejb.Stateless;

@Stateless
public class WishListRepository extends Repository {


    public WishList findWishListById(long id) {
        return find(id, WishList.class);
    }

    public void addWishList(long userId, WishList wishList) {
        add(wishList);
    }

    public void updateWishList(long userId, WishList wishList) {
        update(userId, wishList);
    }

    public void deleteWishList(long wishListId, WishList wishList) {
        delete(wishListId, WishList.class);
    }
}
