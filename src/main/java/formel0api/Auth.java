/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package formel0api;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.xml.soap.MessageFactory;

/**
 *
 * @author Dieter
 */
@ManagedBean(name="auth")
@SessionScoped
public class Auth {
    @ManagedProperty(value="#{player}")
    Player player;
    
    private boolean newPlayer = false;
    private boolean wrongPwd = false;
    private boolean terms = false;
    
    private ResourceBundle bundle;
    private HashMap<String, Player> players = new HashMap<String, Player>();

    public Auth() {
        player = new Player("Hans", "Moser128");
        players.put(player.getName(), player); 
    }

    //Getter and Setter
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setNewPlayer(boolean newPlayer) {
        this.newPlayer = newPlayer;
    }
    
    public boolean isNewPlayer() {
        return newPlayer;
    }
    
    public void setWrongPwd(boolean wrongPwd) {
        this.wrongPwd = wrongPwd;
    }
    
    public boolean isWrongPwd() {
        return wrongPwd;
    }
    
    public void setTerms(boolean terms) {
        this.terms = terms;
    }
    
    public boolean getTerms() {
        return terms;
    }
    
    private void reset() {
        newPlayer = wrongPwd = terms = false;
    }

	private ResourceBundle getBundle() {
		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context, "msg");
		}
		return bundle;
	}

	private String getValue(String key) {

		String result = null;
		try {
			result = getBundle().getString(key);
		} catch (MissingResourceException e) {
			result = "???" + key + "???";
		}
		return result;
	}
    
    // Validate checkbox
    public void validateCheckBox(FacesContext context, UIComponent component,Object o) {  
        HtmlSelectBooleanCheckbox checkBox = (HtmlSelectBooleanCheckbox) component;
        if(checkBox.getSubmittedValue().equals("false") ) {
            FacesMessage msg = new FacesMessage(
            FacesMessage.SEVERITY_WARN, getValue("noterms"), null);
            throw new ValidatorException(msg);
        }  
    }

    // Login
    public String login() {
        reset();
        if(players.containsKey(player.getName())) {
            String pwd = players.get(player.getName()).getPwd();
            if(pwd.equals(player.getPwd())) {
                return "/table.xhtml?faces-redirect=true";
            } else {
                wrongPwd = true;
                return "/login.xhtml?faces-redirect=true";
            }
        } else {
            newPlayer = true;
            return "/login.xhtml?faces-redirect=true";
        }
    }
    
    // Register
    public String register() {
        reset();
        if(!players.containsKey(player.getName())) {
            Player p = new Player(player);
            players.put(p.getName(), p);
            return "/table.xhtml?faces-redirect=true";
        }
        return "/register.xhtml?faces-redirect=true";
    }
}
