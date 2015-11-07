package com.eggshell.kanoting.repository;


import com.eggshell.kanoting.model.Group;
import com.eggshell.kanoting.model.User;
import com.eggshell.kanoting.repository.parent.Repository;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FactionRepository extends Repository {


    /**
     *
     * Only for admins or root.
     *
     */
    public void addUserToFaction(User user) {

        User userForMerge = getEm().getReference(User.class, user.id);

        Stream<Group> filteredGroups = user.roles.stream()
                .filter(userForMerge.roles::contains);

        userForMerge.roles = filteredGroups
                .collect(Collectors.toSet());
        getEm().merge(userForMerge);


    }
}
