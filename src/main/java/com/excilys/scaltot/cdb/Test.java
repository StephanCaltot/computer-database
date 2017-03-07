package com.excilys.scaltot.cdb;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.repository.Pagination;
import com.excilys.scaltot.cdb.repository.impl.CrudServiceComputerImpl;
import com.excilys.scaltot.cdb.services.CrudComputerService;

/**
 * @author Caltot Stéphan
 *
 *         22 févr. 2017
 */
public class Test {

    /**
     * @param args
     *            :
     * @throws Exception
     *             :
     * @throws SQLException
     *             :
     * @throws PersistenceException
     *             :
     */
    public static void main(String[] args) {

        //System.out.println("\n" + DateCheck.formatIsValid(Optional.of("2012-01-01")));

//        Optional<Computer> computer = CrudServiceComputerImpl.INSTANCE.find(503);
//        //computer.get().getManufacturer().setName("MDR");
//        System.out.println(computer.get().getDateWichIsDiscontinued());
//        LocalDate localDate = computer.get().getDateWichIsDiscontinued();
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
//        String text = localDate.format(formatters);
//        System.out.println(text);



//        Pagination pagination = new Pagination.PaginationBuilder().withPageSize(10).withOffset(0).build();
//        pagination.setFilter("apple");

        
        
        CrudComputerService.delete(85); 

        
//        new CrudComputerService();
//        List<Computer> computers =  CrudComputerService.findByPageFilter(pagination);
//        for (Computer computer : computers) {
//            System.out.println(computer.toString());
//        }
//        if (computer.isPresent()) {
//            System.out.println(computer.toString());
//        }
//        computer.get().setDateWichIsDiscontinued(LocalDate.of(2030, 02, 27));
//        computer.get().setDateWichIsIntroduced(LocalDate.of(2000, 11, 25));
//        computer.get().setName("ipad de test");
//        computer.get().setManufacturer(null);
//        CrudServiceComputerImpl.INSTANCE.update(computer);
//        Optional<Computer> comp = CrudServiceComputerImpl.INSTANCE.find(512);
//        System.out.println(comp);
//        System.out.println(computer.get().getManufacturer().toString());
//        System.out.println(ComputerValidator.check(computer));

    }

}