package dev.vishnu.project.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vishnu.project.Model.LoginModel;
import jakarta.persistence.EntityManager;

@Repository
public class LoginRepository {

    @Autowired
    private EntityManager entityManager;

    public List<LoginModel> verifyLogin(String userName, String userPass) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<LoginModel> verify = currentSession
                .createQuery("FROM LoginModel WHERE userName =: userName AND userPass =: userPass", LoginModel.class);
        verify.setParameter("userName", userName);
        verify.setParameter("userPass", userPass);
        List<LoginModel> verifyList = verify.getResultList();

        return verifyList;
    }

}
