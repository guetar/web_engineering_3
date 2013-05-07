/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package formel0api;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dieter
 */
@ManagedBean(name="customer")
@SessionScoped
public class User {
    //@ManagedProperty(value = "Markus")
    private String name;
    private String password;

    /** Creates a new instance of Customer */
    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}