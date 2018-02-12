package com.akhilesh.webapp.controller.api;

import com.akhilesh.webapp.dao.CustomerHDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Akhilesh
 */
@RestController
@RequestMapping(value = "/api/customer")
public class CustomerAPIController {
    
    @Autowired
    private CustomerHDAO customerHDAO;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index(){
        return ResponseEntity.ok(customerHDAO.getAll());
    }
}
