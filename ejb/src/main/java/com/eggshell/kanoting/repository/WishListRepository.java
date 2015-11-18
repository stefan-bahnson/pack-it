package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.model.WishList;
import com.eggshell.kanoting.repository.parent.Repository;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Stateless
public class WishListRepository extends Repository {


    public void addWishList(WishList wishList) {
        add(wishList);
    }

    public WishList findWishListById(long id) {
        return find(id, WishList.class);
    }


    public void updateWishList(WishList wishList) {
        update(wishList);
    }

    public void deleteWishList(WishList wishList) {
        delete(wishList.id, WishList.class);
    }

    public List<WishList> findAll() {
        List<WishList> wishlists = getEm().createQuery("select wl from WishList wl").getResultList();
        if (wishlists.isEmpty())
            throw new EntityNotFoundException("No packlists found");

        return wishlists;
    }
}
