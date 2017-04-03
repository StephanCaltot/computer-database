package com.excilys.scaltot.cdb.persistence.utils;

/**
 * Class contains all needed constant for dao request.
 *
 * @author Caltot Stéphan
 *
 *         21 févr. 2017
 */
public class DaoProperties {

    public static final String CREATE_COMPUTER = "insert into computer(name, introduced, discontinued, company_id) values ( ?, ?, ?, ? )";

    public static final String DELETE_COMPUTER = "delete from computer where id= ?;";

    public static final String UPDATE_COMPUTER = "update computer set name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?;";

    public static final String CREATE_COMPANY = "insert into company(name) values ( ? )";

    public static final String DELETE_COMPUTER_COMPANY = "delete computer from computer where computer.company_id = ?;";

    public static final String DELETE_COMPANY = "delete from company where id= ?;";

    public static final String UPDATE_COMPANY = "update company set name = ? where id = ?;";

    public static final String FIND_COMPUTER = "select computer.id, computer.name, computer.introduced, computer.discontinued,"
            + " computer.company_id, company.name company_name from computer"
            + " left join company on company.id = computer.company_id where computer.id = ?;";

    public static final String FIND_COMPANY = "select id, name from company where id= ?;";

    public static final String FIND_ALL_COMPUTERS = "select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name company_name from computer "
            + "left join company on company.id = computer.company_id;";

    public static final String FIND_ALL_COMPANIES = "select id, name from company;";

    public static final String PAGE_COMPUTER = "select computer.id, computer.name, computer.introduced, computer.discontinued,"
            + " computer.company_id, company.name company_name from computer "
            + "left join company on company.id = computer.company_id limit ? offset ?;";

    public static final String PAGE_COMPUTER_FILTERED = "select computer.id, computer.name, computer.introduced, computer.discontinued,"
            + " computer.company_id, company.name company_name from computer "
            + "left join company on company.id = computer.company_id where computer.name like ? limit ? offset ?;";

    public static final String PAGE_COMPANY = "select company.id, company.name from company limit ? offset ?;";

    public static final String COUNT_COMPUTER = "select count(*) as number from computer";

    public static final String COUNT_COMPANY = "select count(*) as number from company";

    public static final String COMPUTER_FILTERED = "select computer.id, computer.name, computer.introduced, computer.discontinued,"
            + " computer.company_id, company.name company_name from computer"
            + " left join company on company.id = computer.company_id where company.id like ?;";

    public static final String PAGE_COMPANY_FILTERED = "select company.id, company.name from company where company.name like ? limit ? offset ?;";
}