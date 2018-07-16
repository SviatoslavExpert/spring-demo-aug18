package com.akhambir.dao;

import com.akhambir.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    //private JdbcTemplate jdbcTemplate;

    /*@Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }*/

    @Override
    public User addUser(User user) {
        sessionFactory.getCurrentSession()
                .save(user);
        return user;
    }

    @Override
    public User getByEmail(User user) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where email =: email", User.class)
                .setParameter("email", user.getEmail())
                .uniqueResult();
    }

    /*@Override
    public User getByEmail(User user) {
        return this.jdbcTemplate.queryForObject(
                "SELECT ID, EMAIL, PASSWORD FROM USERS U WHERE U.EMAIL = ?",
                new Object[]{user.getEmail()},
                (rs, i) -> {
                    User u = new User();
                    u.setEmail(rs.getString("EMAIL"));
                    u.setPassword(rs.getString("PASSWORD"));
                    return u;
                }
        );
    }*/
}
