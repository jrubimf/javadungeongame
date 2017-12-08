/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rubim
 */
public class Player implements Unity {

    private String name;
    private String currentX;
    private String currentY;

    public Player() {
        this.name = "@";
    }

    public String getName() {
        return name;
    }

    @Override
    public void spawn(String x, String y) {
        currentX = x;
        currentY = y;
    }

    @Override
    public void kill() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCurrentX() {
        return currentX;
    }

    @Override
    public String getCurrentY() {
        return currentY;
    }

    @Override
    public void setCurrentLoc(String x, String y) {
        currentX = x;
        currentY = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this.hashCode() == o.hashCode()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.currentX != null ? this.currentX.hashCode() : 0);
        hash = 37 * hash + (this.currentY != null ? this.currentY.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return name + " " + currentX + " " + currentY;
    }
}
