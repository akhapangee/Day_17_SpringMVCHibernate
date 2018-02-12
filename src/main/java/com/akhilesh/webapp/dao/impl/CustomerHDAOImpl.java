/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhilesh.webapp.dao.impl;

import com.akhilesh.webapp.dao.CustomerHDAO;
import com.akhilesh.webapp.entity.Customer;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Akhilesh
 */
@Repository(value = "CustomerHDAO")
public class CustomerHDAOImpl implements CustomerHDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Override
    public List<Customer> getAll() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("select c from Customer c");
        return query.list();
    }

    @Override
    public void insert(Customer c) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
    }

    @Override
    public void update(Customer c) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(c);
        transaction.commit();
    }

    @Override
    public void delete(int id) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.delete(getById(id));
        transaction.commit();
    }

    @Override
    public Customer getById(int id) {
        session = sessionFactory.openSession();
        return (Customer) session.get(Customer.class, id);
    }

}
