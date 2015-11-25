package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.ejb.Stateless;

@Stateless
public class WishListRepository extends Repository {

    public void addWishlistToUser(long userId, WishList wishlist) {
        User userRef = getEm().getReference(User.class, userId);
        wishlist.user = userRef;

        getEm().merge(wishlist);
    }
}
