package controller;

import java.awt.event.KeyEvent;


import model.Bird;
public class Controller implements Implement {

    public void controller(Bird bird, KeyEvent kevent) {
    }

    public void controllerReleased(Bird bird, KeyEvent kevent) {
        if(kevent.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.jump();
        }
    }
}