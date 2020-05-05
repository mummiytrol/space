package com.space.repository;

import com.space.model.Ship;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipDAOImpl implements ShipDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ship> AllShips() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Ship").list();
    }

    @Override
    public void add(Ship ship) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(ship);
    }

    @Override
    public void delete(Ship ship) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(ship);
    }

    @Override
    public void edit(Ship ship) {
        Session session = sessionFactory.getCurrentSession();
        session.update(ship);
    }

    @Override
    public Ship getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Ship.class, id);
    }
}
