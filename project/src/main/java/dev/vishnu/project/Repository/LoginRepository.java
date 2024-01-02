package dev.vishnu.project.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vishnu.project.Model.LoginModel;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

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

    @Transactional
    public String registerUser(String userName, String userPass, String userType) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<LoginModel> verify = currentSession
                .createQuery("FROM LoginModel WHERE userName =:userName AND userPass =:userPass AND userType =:userType",
                        LoginModel.class);
        verify.setParameter("userName", userName);
        verify.setParameter("userPass", userPass);
        verify.setParameter("userType", userType);
        List<LoginModel> verifyList = verify.getResultList();
        System.out.println("verify List >>>>"+verifyList);

        if (!verifyList.isEmpty()) {
            System.out.println("Here");
            return "Exists";
        } else {

            LoginModel register = new LoginModel();
            register.setUserName(userName);
            register.setUserPass(userPass);
            register.setUserType(userType);

            currentSession.persist(register);
            return "Success";
        }
    }

}