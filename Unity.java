/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Rubim
 */
public interface Unity {

    Random R = new Random();

    
    void spawn(String x, String y);
    String getCurrentX();
    String getCurrentY();
    String getName();
    void setCurrentLoc(String x, String y);
    void kill();
}