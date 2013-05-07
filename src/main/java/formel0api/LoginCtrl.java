/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package formel0api;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.Date;
import java.util.Random;
import javax.faces.validator.ValidatorException;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Dieter
 */
@ManagedBean
@SessionScoped
public class LoginCtrl {
    @ManagedProperty(value="#{User}")
    User user;
    @ManagedProperty(value = "true")
    private boolean displayonline;

    boolean loginfailed = false;

    /** Creates a new instance of LoginCtrl */
    public LoginCtrl() {
    }

    //Getter and Setter
    public User getUser() {
        return user;
    }

    public void setUser(User User) {
        this.user = User;
    }

    public Date getDatetime() {
        return new Date();
    }

    public boolean isLoginfailed() {
        return loginfailed;
    }

    public void setLoginfailed(boolean loginfailed) {
        this.loginfailed = loginfailed;
    }

    public boolean isDisplayonline() {
        return displayonline;
    }

    public void setDisplayonline(boolean displayonline) {
        this.displayonline = displayonline;
    }


    public int getOnlineUsers()
    {
        return new Random().nextInt(10) + 1;
    }

    //Login - check password
    public String login()
    {
        if(user.getPassword().equals("secret"))
        {
            loginfailed = false;
            return "/store_main.xhtml";
        }

        else
        {
            loginfailed = true;
            return "/login.xhtml";
        }
    }

    //Checks if the display checkbox changed
    public void displayChanged(ValueChangeEvent e){
        Boolean show = (Boolean) e.getNewValue();
        if(show != null)
            displayonline = show;

        FacesContext.getCurrentInstance().renderResponse();
    }

    //Validation of the username
    public void validateUsername(FacesContext ctx, UIComponent component, Object value) throws ValidatorException
    {
        String username = (String)value;

        if(!username.equals("Markus") && !username.equals("Heidi"))
        {
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Wrong username!", null);
            throw new ValidatorException(msg);
        }
    }


}
