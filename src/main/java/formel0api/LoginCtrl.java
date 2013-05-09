/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package formel0api;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Dieter
 */
@ManagedBean(name="loginCtrl")
@SessionScoped
public class LoginCtrl {
    @ManagedProperty(value="#{player}")
    Player player;
    @ManagedProperty(value = "true")
    
    private boolean displayonline;
    private boolean loginfailed = false;
    
    private HashMap<String, Player> players = new HashMap<String, Player>();

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

    public int getOnlinePlayers() {
        return new Random().nextInt(10) + 1;
    }

    //Login
    public String login() {
        if(players.containsKey(player.getName())) {
            if(players.get(player.getName()).getPassword().equals(player.getPassword())) {
                loginfailed = false;
                return "/table.xhtml";
            } else {
                loginfailed = true;
                return "/login.xhtml";
            }
        } else {
            loginfailed = true;
            return "/register.xhtml";
        }
    }
    
    //Register
    public void register() {
        
    }

    //Validation of the playerName
    public void validateName(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String name = (String) value;

        if(!players.containsKey(name)) {
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"You are not yet registered!", null);
            throw new ValidatorException(msg);
        } else {
            player = players.get(name);
        }
    }

    //Validation of the playerName
    public void validatePassword(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;

        if(!player.getPassword().equals(password)) {
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN,"Wrong password!", null);
            throw new ValidatorException(msg);
        }
    }
}
