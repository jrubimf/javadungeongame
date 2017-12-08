/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Rubim
 */
public class Dungeon {

    private Scanner sc = new Scanner(System.in);

    Random r = new Random();

    private int height;
    private int length;

    private int total;

    private List<Vampire> vampires;
    private Player player;
    private int moves;
    private boolean vampiresMove = true;

    private String[][] worldMap;
    private String playerLocation = "0.0";

    public Dungeon(int length, int height, int total, int moves, boolean vampiresMove) {
        //World Map
        this.height = height;
        this.length = length;
        this.total = total;
        this.worldMap = new String[height][length];

        //Vampire
        this.vampires = new ArrayList<Vampire>();

        for (int i = 0; i < total; i++) {
            this.vampires.add(new Vampire());
        }

        //Player
        this.player = new Player();

        //Extra
        this.moves = moves;
        this.vampiresMove = vampiresMove;
    }

    //Drawmap
    public void drawMap() {
        for (int i = 0; i < this.height; i++) {
            for (int t = 0; t < (this.length); t++) {
                worldMap[i][t] = ".";
            }
        }
        boolean exists = false;
        for (int i = 0; i < vampires.size(); i++) {
            String randomCoordsX = String.valueOf(1 + (int) (Math.random() * ((this.length - 2) + 1)));
            String randomCoordsY = String.valueOf(1 + (int) (Math.random() * ((this.height - 2) + 1)));

            //spawn
            for (Vampire vampire : vampires) {
                if (vampire.getCurrentX().equals(randomCoordsX) && vampire.getCurrentY().equals(randomCoordsY)) {
                    i--;
                    exists = true;
                }
            }
            if (exists) {
            } else {
                exists = false;
                vampires.get(i).spawn(randomCoordsX, randomCoordsY);
            }
        }
        player.spawn("0", "0");
    }

    //Is there anyone on this location?
    public String drawDots(String x, String y) {

        for (Vampire vampire : vampires) {
            if (vampire.getCurrentX().equals(x) && vampire.getCurrentY().equals(y)) {
                return "v";
            }
        }
        if (player.getCurrentX().equals(x) && player.getCurrentY().equals(y)) {
            return "@";
        }
        return ".";
    }

    public void print() {
        String i_t = "";
        for (int y = 0; y < this.length; y++) {
            for (int x = 0; x < this.height; x++) {
                System.out.print(drawDots(String.valueOf(x), String.valueOf(y)));
            }
            System.out.print("\n");
        }
    }

    public void unityCollision(Player a) {

        for (int i = 0; i < vampires.size(); i++) {
            if (a.equals(vampires.get(i))) {
                vampires.remove(i);
            }
        }
    }

    public void movePlayer(char direction, Unity object) {
        move(direction, object);
        if (vampiresMove) {
            for (Vampire vampire : vampires) {
                int randomNumber = 1 + (int) (Math.random() * ((4 - 1) + 1));

                switch (randomNumber) {
                    case 1:
                        move('w', vampire);
                    case 2:
                        move('s', vampire);
                    case 3:
                        move('a', vampire);
                    case 4:
                        move('d', vampire);
                }
            }
        }

    }

    public void move(char direction, Unity object) {
        String moveDirectionX = object.getCurrentX();
        String moveDirectionY = object.getCurrentY();

        switch (direction) {
            case 'w':
                if (Integer.valueOf(object.getCurrentY()) - 1 < 0) {
                    break;
                }
                moveDirectionY = (String.valueOf(Integer.valueOf(object.getCurrentY()) - 1));
                break;
            case 's':
                if (Integer.valueOf(object.getCurrentY()) + 1 >= getHeight()) {
                    break;
                }
                moveDirectionY = String.valueOf(Integer.valueOf(object.getCurrentY()) + 1);
                break;
            case 'a':
                if (Integer.valueOf(object.getCurrentX()) - 1 < 0) {
                    break;
                }
                moveDirectionX = (String.valueOf(Integer.valueOf(object.getCurrentX()) - 1));
                break;
            case 'd':
                if (Integer.valueOf(object.getCurrentX()) + 1 >= getLength()) {
                    break;
                }
                moveDirectionX = (String.valueOf(Integer.valueOf(object.getCurrentX()) + 1));
        }

        boolean blockMove = false;

        for (int i = 0; i < vampires.size(); i++) {
            if ((moveDirectionX.equals(vampires.get(i).getCurrentX())) && (moveDirectionY.equals(vampires.get(i).getCurrentY()))) {
                blockMove = true;
            }
        }

        if (!blockMove || object.getName().equals("@")) {
            object.setCurrentLoc(moveDirectionX, moveDirectionY);
        } else {
            blockMove = false;
        }

    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public void run() {
        drawMap();
        while (moves > 0) {
            System.out.println(moves);
            System.out.println("");
            System.out.println(player);
            for (Vampire vampire : vampires) {
                System.out.println(vampire);
            }
            System.out.println("");
            print();

            if (vampires.isEmpty()) {
                break;
            }

            String typed = sc.nextLine();
            char[] typedChar = typed.toCharArray();
            
            for (char c : typedChar) {
                movePlayer(c, player);
            }
            unityCollision(player);
            moves--;
        }
        if (vampires.isEmpty()) {
            System.out.println("YOU WIN");
        } else {
            System.out.println("YOU LOSE");
        }
    }

}
