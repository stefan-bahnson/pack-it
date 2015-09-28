package com.eggshell.kanoting.repository;

import com.eggshell.kanoting.model.PackList;
import com.eggshell.kanoting.repository.parent.Repository;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import javax.ejb.Stateless;

@Stateless
public class PackListRepository extends Repository {

    public PackList findPackListById(long id) {
        return find(PackList.class, id);
    }

    public void addPackList(PackList packList) {
        add(packList);
    }

    public void updatePackList(PackList packList) {
        update(packList);
    }

    public void deletePacklist(PackList packList) {
        delete(PackList.class, packList.id);
    }
}
