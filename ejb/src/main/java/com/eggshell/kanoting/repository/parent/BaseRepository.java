package com.eggshell.kanoting.repository.parent;

import com.eggshell.kanoting.model.User;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Temp class to circumvent that repos extend repository.
 * We can now inject this repo into controllers.
 */
@Stateless
public class BaseRepository extends Repository {}
