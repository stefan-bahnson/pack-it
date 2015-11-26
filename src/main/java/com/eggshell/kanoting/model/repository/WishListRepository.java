package com.eggshell.kanoting.model.repository;

import com.eggshell.kanoting.model.entity.User;
import com.eggshell.kanoting.model.entity.WishList;
import com.eggshell.kanoting.model.repository.parent.Repository;

import javax.ejb.Stateless;

@Stateless
public class WishListRepository extends Repository {

    public void addWishlistToUser(long userId, WishList wishlist) {
        User userRef = getEm().getReference(User.class, userId);
        wishlist.user = userRef;

        getEm().merge(wishlist);
    }
}
