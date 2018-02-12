package com.akhilesh.webapp.controller;

import com.akhilesh.webapp.dao.CustomerHDAO;
import com.akhilesh.webapp.entity.Customer;
import com.akhilesh.webapp.entity.ResponseBodyEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Akhilesh
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerHDAO customerHDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("customer/index");
//        mv.addObject("customers", customerHDAO.getAll());
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "customer/add";
    }
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public @ResponseBody ResponseBodyEntity<Customer> json() {
        ResponseBodyEntity<Customer> response = new ResponseBodyEntity<>();
        response.setData(customerHDAO.getAll());
        return response;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Customer customer) {
        if (customer.getId() == 0) {
            customerHDAO.insert(customer);
        } else {
            customerHDAO.update(customer);
        }
        return "redirect:/customer";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, ModelMap modelMap) {
        Customer customer = customerHDAO.getById(id);
        if (customer == null) {
            return "redirect:/customer";
        } else {
            modelMap.addAttribute("customer", customer);
        }
        return "customer/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        customerHDAO.delete(id);
        return "redirect:/customer";
    }

}
