/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiroZoid.dao;

/**
 *
 * @author hiroshi
 */
public enum DaoHelper {
    INSTANCE;

    public String quote(String str) {
        return str == null ? "NULL" : "'" + str + "'";
    }
}
