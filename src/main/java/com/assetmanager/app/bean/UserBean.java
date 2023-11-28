package com.assetmanager.app.bean;

import com.assetmanager.app.mail.bean.MailBeanI;
import com.assetmanager.app.mail.model.Mail;
import com.assetmanager.app.mail.utility.MailFormatter;
import com.assetmanager.app.model.entity.User;
import com.assetmanager.exceptions.InvalidEmailFormatException;
import com.assetmanager.exceptions.UserAlreadyExistsException;
import com.assetmanager.exceptions.UserPasswordEncodingException;
import com.assetmanager.util.EmailValidator;
import com.assetmanager.util.security.PasswordEncoderI;


import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Stateless
@Remote
public class UserBean extends GenericBean<User> implements UserBeanI, Serializable {

    @Inject
    PasswordEncoderI passwordEncoder;
    @Inject
    EmailValidator emailValidator;
    @Inject
    MailBeanI mailBean;
    @Inject
    MailFormatter mailFormat;

    @Override
    public Boolean registerUser(User user) {
        try {
            if (findUserByUsername(user.getUsername()).isPresent())
                throw new UserAlreadyExistsException("Failed!!. User with username " + user.getUsername() + " already exists.");

            if (findUserByEmail(user.getEmail()).isPresent())
                throw new UserAlreadyExistsException("Failed!!. User with email " + user.getEmail() + " already exists.");

            if (!emailValidator.isValidEmail(user.getEmail()))
                throw new InvalidEmailFormatException("Failed!!. Invalid Email Format");

            if (user.getPassword().equals(user.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encodePassword(user.getPassword()));

                getDao().create(user);

                String subject = "RE: Account Creation Confirmation" ;
                String message = "Dear " + user.getUsername() + ", <br>" +
                        "Thank you for registering with us 🎉. <br> Your account has been created in the Asset Management System." +
                        "<br> Your username is: <b>" + user.getUsername() +
                        "</b>.<br> Kindly use it to login. <br>" +
                        "<br>" +
                        "Kind Regards, <br> Asset Management Team <br> ✨";

                String templateContent = mailFormat.emailTemplate();
                String fomattedMessage = templateContent.replace("%body%", message);

                System.out.println("Mail Content " + fomattedMessage);

                Mail mail = new Mail();
                mail.setRecipient(user.getEmail());
                mail.setSubject(subject);
                mail.setMessage(fomattedMessage);
                mailBean.sendMail(mail);
                System.out.println("Mail Set Successfully");
                return true;

            }
        } catch (NoSuchAlgorithmException e) {
            throw new UserPasswordEncodingException("Password encoding algorithm failed: " + e.getMessage());
        } catch (UserAlreadyExistsException e) {
            System.out.println("User Registration Error " + e.getMessage());
        } catch (InvalidEmailFormatException ex) {
            System.out.println("Email Verification Error: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        List<User> users = list(new User());
        return users.stream().filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        List<User> users = list(new User());
        return users.stream().filter(user -> user.getEmail().equals(email))
                .findFirst();
    }


}
