/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Administrator
 */
public class LoginController implements Serializable{

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if (username != null && username.equals("admin") && password != null && password.equals("admin")) {
<<<<<<< HEAD
//            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
//                    .getExternalContext().getSession(true);
//            session.setAttribute("login", true);
            return "admin/admin-home-page.xhtml?faces-redirect=true";
=======
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(true);
            session.setAttribute("login", true);
            return "/admin/admin-home-page.jsf";
>>>>>>> 66a842f3f57014d25d2a9279071e73c21de5c2b4
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Đăng nhập thất bại"));
            return "";
        }
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        session.removeAttribute("login");
        return "admin/login";
    }
}
