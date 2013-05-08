/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package formel0api;

import java.util.Date;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Dieter
 */
@ManagedBean
@SessionScoped
public class LoginCtrl {
    @ManagedProperty(value="#{Player}")
    Player player;
    @ManagedProperty(value = "true")
    private boolean displayonline;

    boolean loginfailed = false;

    /** Creates a new instance of LoginCtrl */
    public LoginCtrl() {
    }

    //Getter and Setter
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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


    public int getOnlinePlayers()
    {
        return new Random().nextInt(10) + 1;
    }

    //Login - check password
    public String login()
    {
        if(player.getPassword().equals("secret"))
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

    //Validation of the Playername
    public void validatePlayername(FacesContext ctx, UIComponent component, Object value) throws ValidatorException
    {
        String Playername = (String)value;

        if(!Playername.equals("Markus") && !Playername.equals("Heidi"))
        {
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Wrong Playername!", null);
            throw new ValidatorException(msg);
        }
    }


}
