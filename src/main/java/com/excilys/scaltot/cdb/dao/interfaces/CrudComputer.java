package com.excilys.scaltot.cdb.dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.computer.Computer;

/**
 * @author Caltot Stéphan
 *
 * 20 mars 2017
 */
public interface CrudComputer extends CrudService<Computer> {

    /**
     * Method which permits to create a computer.
     * @param computer : computer
     */
    void create(Optional<Computer> computer);

    /**
     * Method which permits to update a computer.
     * @param computer : computer
     */
    void update(Optional<Computer> computer);

    /**
     * Method which permits to retrieves a list of computer filtered.
     * @param filter : filter
     * @return list of computer
     */
    List<Computer> getComputersFiltered(String filter);

}