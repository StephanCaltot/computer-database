package com.excilys.scaltot.cdb.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.repository.Pagination;
import com.excilys.scaltot.cdb.services.CrudCompanyService;
import com.excilys.scaltot.cdb.services.CrudComputerService;
import com.excilys.scaltot.cdb.validation.DateCheck;


/**
 * Servlet class for computer.
 * @author Caltot Stéphan
 *
 * 3 mars 2017
 */
@WebServlet(name = "ServletComputer", urlPatterns = "/computerdatabase")
public class ServletComputer extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletComputer.class);
    private static final long serialVersionUID = 1L;
    private String pageToForward;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletComputer() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     * @param request :
     * @param response :
     * @throws ServletException :
     * @throws IOException :
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        List<Company> companies;
        List<Computer> computers;
        pageToForward = "/views/dashboard.jsp";
        Pagination page = getPage(request);
        String filter = "apple";
        Computer computer;
        page.setNumberOfElements(CrudComputerService.getCountOfComputers());

        if (request.getParameter("action") != null) {
            switch (request.getParameter("action")) {
            case "numOfPage":
                if (request.getParameter("numOfPage") != null) {
                    try {
                        int numOfPage = Integer.parseInt(request.getParameter("numOfPage"));
                        page.setCurrentPage(numOfPage);
                    } catch (NumberFormatException e) {
                  }
                }
                break;
            case "filter":
                if (request.getParameter("filter") != null) {
                    try {
                        filter = request.getParameter("filter");
                        page.setFilter(filter);
                    } catch (NumberFormatException e) {
                  }
                }
                break;
            case "nextPage":
                computers = page.nextPage();
                break;
            case "previousPage":
                computers = page.previousPage();
                break;
            case "size":
                if (request.getParameter("size") != null) {
                    try {
                        int size = Integer.parseInt(request.getParameter("size"));
                        page.setPageSize(size);
                    } catch (NumberFormatException e) {
                  }
                }
                break;
            case "add":
                companies = CrudCompanyService.findAll();
                request.getSession().setAttribute("companies", companies);
                pageToForward = "/views/addComputer.jsp";
                break;
            case "edit":
                if (request.getParameter("id") != null) {
                    try {
                        long id = Long.parseLong(request.getParameter("id"));
                        companies = CrudCompanyService.findAll();
                        computer = CrudComputerService.find(id).get();
                        request.getSession().setAttribute("companies", companies);
                        request.getSession().setAttribute("computer", computer);
                        pageToForward = "/views/editComputer.jsp";
                    } catch (NumberFormatException numberFormatException) {

                    }
                }
                break;
            default:
                break;
            }
        }
        if (pageToForward.equals("/views/dashboard.jsp")) {
            computers = page.findByPageFilter();
            request.getSession().setAttribute("computers", computers);
            request.getSession().setAttribute("filter", filter);
            request.getSession().setAttribute("numberOfPages", page.getNumberOfPages());
            request.getSession().setAttribute("currentPage", page.getCurrentPage());
            request.getSession().setAttribute("numberOfElements", page.getNumberOfElements());
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(pageToForward);
        rd.forward(request, response);
    }

    /**
     * Set page attribute if it's null.
     * @param request : request
     * @return page attribute
     */
    public Pagination getPage(HttpServletRequest request) {
        if (request.getSession().getAttribute("page") == null) {
            Pagination page = new Pagination.PaginationBuilder().build();
            request.getSession().setAttribute("page", page);
        }
        return (Pagination) request.getSession().getAttribute("page");
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     * @param request :
     * @param response :
     * @throws ServletException :
     * @throws IOException :
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("action") != null) {
            switch (request.getParameter("action")) {
            case "add":
                try {
                    String name = request.getParameter("computerName");
                    Computer.ComputerBuilder computerBuilder = new Computer.ComputerBuilder().withName(name);
                    LocalDate introduced = null;
                    if (DateCheck.formatIsValid(Optional.of(request.getParameter("introduced")))
                            && StringUtils.isNotBlank(request.getParameter("introduced"))) {
                        introduced = LocalDate.parse(request.getParameter("introduced"));
                        computerBuilder.withDateWichIsIntroduced(introduced);
                    }

                    LocalDate discontinued = null;
                    if (DateCheck.formatIsValid(Optional.of(request.getParameter("discontinued")))
                            && StringUtils.isNotBlank(request.getParameter("discontinued"))) {
                        if (DateCheck.isRealTime(Optional.ofNullable(introduced),
                                Optional.ofNullable(LocalDate.parse(request.getParameter("discontinued"))))) {
                            discontinued = LocalDate.parse(request.getParameter("discontinued"));
                            computerBuilder.withDateWichIsDiscontinued(discontinued);
                        }
                    }
                    int companyId = Integer.parseInt(request.getParameter("companyId"));

                    Company company = null;

                    if (companyId != 0) {
                        company = new Company.CompanyBuilder().withId(companyId).build();
                        computerBuilder.withManufacturer(company);
                    }
                    CrudComputerService.create(Optional.ofNullable(computerBuilder.build()));

                } catch (NumberFormatException e) {
                    System.out.println(e);
                }

            case "edit":
                try {

                    long id = Long.parseLong(request.getParameter("id"));
                    String name = request.getParameter("name");
                    LOGGER.info("voila le nom récupéré : " + name);
                    Computer.ComputerBuilder computerBuilder = new Computer.ComputerBuilder().withId(id).withName(name);
                    LocalDate introduced = null;
                    if (DateCheck.formatIsValid(Optional.of(request.getParameter("introduced")))
                            && StringUtils.isNotBlank(request.getParameter("introduced"))) {
                        introduced = LocalDate.parse(request.getParameter("introduced"));
                        computerBuilder.withDateWichIsIntroduced(introduced);
                    }

                    LocalDate discontinued = null;
                    if (DateCheck.formatIsValid(Optional.of(request.getParameter("discontinued")))
                            && StringUtils.isNotBlank(request.getParameter("discontinued"))) {
                        if (DateCheck.isRealTime(Optional.ofNullable(introduced),
                                Optional.ofNullable(LocalDate.parse(request.getParameter("discontinued"))))) {
                            discontinued = LocalDate.parse(request.getParameter("discontinued"));
                            computerBuilder.withDateWichIsDiscontinued(discontinued);
                        }
                    }
                    int companyId = Integer.parseInt(request.getParameter("companyId"));

                    Company company = null;

                    if (companyId != 0) {
                        company = new Company.CompanyBuilder().withId(companyId).build();
                        computerBuilder.withManufacturer(company);
                    }
                    CrudComputerService.update(Optional.ofNullable(computerBuilder.build()));

                } catch (NumberFormatException e) {
                    System.out.println(e);
                }

            case "delete":
                
                String selection = request.getParameter("selection");
                String[] selections = selection.split(",");
                for (String computerId : selections) {
                    LOGGER.info("DELETION ID" + Long.parseLong(computerId));
                    CrudComputerService.delete(Long.parseLong(computerId)); 
                }

            default:
                break;
            }
        }
        response.sendRedirect(request.getContextPath() + "/computerdatabase");
    }
}