package org.badcoding.controller;

import javafx.util.Pair;
import org.badcoding.dao.*;
import org.badcoding.dao.implementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;
import java.util.Calendar;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value="/main")
public class StartController {
    @Autowired
    SalesOrderImplementation salesOrderdao = new SalesOrderImplementation();
    @Autowired
    CustomerImplementation customerdao = new CustomerImplementation();
    @Autowired
    EmployeeImplementation employeedao = new EmployeeImplementation();
    @Autowired
    EducationImplementation educationdao = new EducationImplementation();
    @Autowired
    JobImplementation jobdao = new JobImplementation();
    @Autowired
    ServiceImplementation servicedao = new ServiceImplementation();

    @RequestMapping(value="/start", method= RequestMethod.GET)
    public String getSalesOrders(Model model) {
        List<SalesOrderEntity> contracts = salesOrderdao.getList();
        model.addAttribute("contracts", contracts);
        return "start";
    }

    @RequestMapping(value = "/start/add", method = RequestMethod.GET)
    public String getAddSalesOrder(Model model) {
        List<CustomerEntity> clients = customerdao.getList();
        model.addAttribute("clientsList", clients);
        List<EmployeeEntity> servants = employeedao.getList();
        model.addAttribute("servantsList", servants);
        List<ServiceEntity> services = servicedao.getList();
        model.addAttribute("servicesList", services);
        model.addAttribute("salesOrderAttribute", new SalesOrderEntity());
        return "addSalesOrder";
    }

    @RequestMapping(value = "/start/add", method = RequestMethod.POST)
    public String addNews(@ModelAttribute("salesOrderAttribute") SalesOrderEntity order) throws SQLException, InterruptedException {
        java.sql.Date orderDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        CustomerEntity customer = customerdao.getById(order.getCustomerByCustomerId().getCustomerId());
        EmployeeEntity employee = employeedao.getById(order.getEmployeeByEmployeeId().getEmployeeId());
        ServiceEntity service = servicedao.getById(order.getServiceByServiceId().getServiceId());
        SalesOrderEntity salesOrder = new SalesOrderEntity(customer, employee,
                service, orderDate);
        salesOrderdao.save(salesOrder);
        return "redirect:/main/start";
    }

    @RequestMapping(value = "/start/delete", method = RequestMethod.GET)
    public String deleteNews(@RequestParam(value="id") Integer id,
                             Model model) throws SQLException {
        salesOrderdao.delete(salesOrderdao.getById(id));
        model.addAttribute("id", id);
        return "redirect:/main/start";
    }

    @RequestMapping(value = "/start/edit", method = RequestMethod.GET)
    public String getEditNews(@RequestParam(value="id") Integer id,
                              Model model) throws SQLException {
        List<CustomerEntity> clients = customerdao.getList();
        model.addAttribute("clientsList3", clients);
        List<EmployeeEntity> servants = employeedao.getList();
        model.addAttribute("servantsList3", servants);
        List<ServiceEntity> services = servicedao.getList();
        model.addAttribute("servicesList3", services);
        model.addAttribute("salesOrderAttribute", salesOrderdao.getById(id));
        return "editSalesOrder";
    }
    @RequestMapping(value = "/start/edit", method = RequestMethod.POST)
    public String saveEditContract(@ModelAttribute("contractAttribute") SalesOrderEntity order,
                                   @RequestParam(value="id") Integer id,
                                   Model model) throws SQLException {
        SalesOrderEntity editOrder=salesOrderdao.getById(id);
        editOrder.setCustomerByCustomerId(customerdao.getById(order.getCustomerByCustomerId().getCustomerId()));
        editOrder.setEmployeeByEmployeeId(employeedao.getById(order.getEmployeeByEmployeeId().getEmployeeId()));
        editOrder.setOrderDate(order.getOrderDate());
        salesOrderdao.update(editOrder);
        model.addAttribute("id", id);

        return "redirect:/main/start";
    }

    //clients
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String getClients(Model model) throws SQLException {

        List<CustomerEntity> clients = customerdao.getList();
        model.addAttribute("clients", clients);
        model.addAttribute("searched",false);
        return "clients";
    }
    @RequestMapping(value = "/clients/add", method = RequestMethod.GET)
    public String getAddClient(Model model) {
        model.addAttribute("clientAttribute", new CustomerEntity());
        return "addClient";
    }
    @RequestMapping(value = "/clients/add", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("clientAttribute") CustomerEntity client) throws SQLException, InterruptedException {
        client.setCustomerId(customerdao.getList().size()+1);
        customerdao.save(client);
        return "redirect:/main/clients";
    }
    @RequestMapping(value = "/clients/delete", method = RequestMethod.GET)
    public String deleteClient(@RequestParam(value="id") Integer id,
                               Model model) throws SQLException {
        customerdao.delete(customerdao.getById(id));
        model.addAttribute("id", id);
        return "redirect:/main/clients";
    }
    @RequestMapping(value = "/clients/edit", method = RequestMethod.GET)
    public String getEditClient(@RequestParam(value="id") Integer id,
                                Model model) throws SQLException {
        model.addAttribute("clientAttribute", customerdao.getById(id));
        return "editClient";
    }
    @RequestMapping(value = "/clients/edit", method = RequestMethod.POST)
    public String saveEditClient(@ModelAttribute("clientAttribute") CustomerEntity client,
                                 @RequestParam(value="id") Integer id,
                                 Model model) throws SQLException {
        client.setCustomerId(id);
        customerdao.update(client);
        model.addAttribute("id", id);
        return "redirect:/main/clients";
    }

    //employee
    @RequestMapping(value = "/servants", method = RequestMethod.GET)
    public String getServants(Model model) throws SQLException {
        List<EmployeeEntity> servants = employeedao.getList();
        model.addAttribute("servants", servants);
        model.addAttribute("searched",false);
        return "servants";
    }

    @RequestMapping(value = "/servants/add", method = RequestMethod.GET)
    public String getAddServant(Model model) {
        model.addAttribute("servantAttribute", new EmployeeEntity());
        return "addServant";
    }

    @RequestMapping(value = "/servants/add", method = RequestMethod.POST)
    public String addServant(@ModelAttribute("servantAttribute") EmployeeEntity servant) throws SQLException, InterruptedException {
        JobEntity job = jobdao.getById(servant.getJobByJobId().getJobId());
        EducationEntity educ = educationdao.getById(servant.getEducationByEducationId().getEducationId());
        EmployeeEntity new_emp = new EmployeeEntity(servant.getFirstname(), servant.getLastname(), servant.getMail(),
                servant.getPhone(), servant.getAddress(), job, educ);
        employeedao.save(new_emp);
        return "redirect:/main/servants";
    }

    @RequestMapping(value = "/servants/delete", method = RequestMethod.GET)
    public String deleteServant(@RequestParam(value="id") Integer id,
                                Model model) throws SQLException {
        employeedao.delete(employeedao.getById(id));
        model.addAttribute("id", id);
        return "redirect:/main/servants";
    }

    @RequestMapping(value = "/servants/edit", method = RequestMethod.GET)
    public String getEditServant(@RequestParam(value="id") Integer id,
                                 Model model) throws SQLException {
        model.addAttribute("servantAttribute", employeedao.getById(id));
        return "editServant";
    }

    @RequestMapping(value = "/servants/edit", method = RequestMethod.POST)
    public String saveEditServant(@ModelAttribute("servantAttribute") EmployeeEntity servant,
                                  @RequestParam(value="id") Integer id,
                                  Model model) throws SQLException {
        servant.setEmployeeId(id);
        employeedao.update(servant);
        model.addAttribute("id", id);
        return "redirect:/main/servants";
    }


    // search

    @RequestMapping(value = "/clients/search", method = RequestMethod.GET)
    public String getSearchClient(Model model) {
        List<CustomerEntity> clients = customerdao.getList();
        model.addAttribute("clientsList1", clients);
        List<EmployeeEntity> servants = employeedao.getList();
        model.addAttribute("servantsList1", servants);
        List<ServiceEntity> services = servicedao.getList();
        model.addAttribute("servicesList1", services);

        model.addAttribute("pairAttribute", new SalesOrderEntity());
        return "searchClient";
    }

    @RequestMapping(value = "/clients/search", method = RequestMethod.POST)
    public String SearchClient(@ModelAttribute("pairAttribute") SalesOrderEntity order,
                               Model model) throws SQLException,ParseException {
        List<SalesOrderEntity> clients = salesOrderdao.getByFilters(order.getCustomerByCustomerId().getCustomerId()==0?null:customerdao.getById(order.getCustomerByCustomerId().getCustomerId()).getCustomerId(),
                order.getEmployeeByEmployeeId().getEmployeeId()==0?null:employeedao.getById(order.getEmployeeByEmployeeId().getEmployeeId()).getEmployeeId(),
                order.getServiceByServiceId().getServiceId()==0?null:servicedao.getById(order.getServiceByServiceId().getServiceId()).getServiceId(),
                new java.sql.Date(1990-1-1), order.getOrderDate());
        model.addAttribute("contracts", clients);
        model.addAttribute("searched",true);
        return "start";
    }

    @RequestMapping(value = "/servants/search", method = RequestMethod.GET)
    public String getSearchServant(Model model) {
        model.addAttribute("pairAttribute", new SalesOrderEntity());
        List<CustomerEntity> clients = customerdao.getList();
        model.addAttribute("clientsList2", clients);
        List<EmployeeEntity> servants = employeedao.getList();
        model.addAttribute("servantsList2", servants);
        List<ServiceEntity> services = servicedao.getList();
        model.addAttribute("servicesList2", services);
        return "searchServant";
    }

    @RequestMapping(value = "/servants/search", method = RequestMethod.POST)
    public String SearchServant(@ModelAttribute("pairAttribute") SalesOrderEntity order,
                                Model model) throws SQLException,ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        List<SalesOrderEntity> servants = salesOrderdao.getByFilters(order.getCustomerByCustomerId().getCustomerId()==0?null:customerdao.getById(order.getCustomerByCustomerId().getCustomerId()).getCustomerId(),
                order.getEmployeeByEmployeeId().getEmployeeId()==0?null:employeedao.getById(order.getEmployeeByEmployeeId().getEmployeeId()).getEmployeeId(),
                order.getServiceByServiceId().getServiceId()==0?null:servicedao.getById(order.getServiceByServiceId().getServiceId()).getServiceId(),
                new java.sql.Date(1990-1-1), order.getOrderDate());

        model.addAttribute("servants", servants);
        model.addAttribute("searched",true);

        return "servants";
    }
}