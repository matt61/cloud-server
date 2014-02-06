package lgb.model;

import org.hibernate.Session;

import lgb.util.HibernateUtil;

public class User {
    private Long id;
    private String first_name;

    public User() {}

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void getFirstName(String value) {
        this.first_name = value;
    }
    
    public static void createAndStoreEvent(String first_name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        User theUser = new User();
        theUser.getFirstName(first_name);
        session.save(theUser);

        session.getTransaction().commit();
    }
}