package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;
    private Transaction transaction;

    public UserDaoHibernateImpl() {
    }

    public Session getTransactionSession() {
        session = Util.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
        return session;
    }

    @Override
    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (id  INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(40), lastName VARCHAR(40), age TINYINT)";

        try {
            session = getTransactionSession();
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";

        try {
            session = getTransactionSession();
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try {
            session = getTransactionSession();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String query = String.format("DELETE FROM User WHERE id = %d", id);

        try {
            session = getTransactionSession();
            session.createQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String query = "from User";
        List<User> users;

        try {
            session = getTransactionSession();
            users = session.createQuery(query).getResultList();
            transaction.commit();
            return users;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users";

        try {
            session = getTransactionSession();
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
