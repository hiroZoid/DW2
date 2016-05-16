/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiroZoid.servlet;

/**
 *
 * @author hiroshi
 */
public enum Helper {
    INSTANCE;

    public boolean isSet(String string) {
        return string != null && !string.equalsIgnoreCase("");
    }
}
