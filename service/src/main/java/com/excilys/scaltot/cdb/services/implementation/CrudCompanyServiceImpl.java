package com.excilys.scaltot.cdb.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.scaltot.cdb.entities.Company;
import com.excilys.scaltot.cdb.pagination.Pagination;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudCompany;
import com.excilys.scaltot.cdb.services.interfaces.CrudCompanyService;

@Service
@Scope("singleton")
@Transactional
public class CrudCompanyServiceImpl implements CrudCompanyService {

    @Autowired
    private CrudCompany crudCompanyImpl;

    /**
     * Return company find by id.
     * @param id : id
     * @return company
     */
    public Optional<Company> find(long id) {

        return crudCompanyImpl.find(id);

    }

    /**
     * Return all companies.
     * @return list of companies
     */
    public List<Company> findAll() {

        return crudCompanyImpl.findAll();

    }

    /**
     * Return list of companies paginated.
     * @param pagination : page
     * @return list of companies
     */
    public List<Company> findByPage(Pagination pagination) {

        return crudCompanyImpl.findByPageFilter(pagination);

    }

    /**
     * Return number of companies.
     * @return long
     */
    public long getCountOfCompanies() {

        return crudCompanyImpl.getCountOfElements();

    }

    /**
     * Delete one company with computer associated.
     * @param companyId : id of company
     * @return boolean
     */
    public long delete(long companyId) {

        return crudCompanyImpl.delete(companyId);

    }
}
