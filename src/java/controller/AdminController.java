/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Administrator
 */
public class AdminController {

    /**
     * Creates a new instance of AdminController
     */
    private int selectedMenu = 1;
    private final int BOOK = 1;
    private final int ORDER = 2;
    private final int CATEGORY = 3;
    private final int AUTHOR = 4;

    public int getSelectedMenu() {
        return selectedMenu;
    }

    public int getBOOK() {
        return BOOK;
    }

    public int getORDER() {
        return ORDER;
    }

    public int getCATEGORY() {
        return CATEGORY;
    }

    public int getAUTHOR() {
        return AUTHOR;
    }

    public void setView(int view) {
        this.selectedMenu = view;
    }
}
