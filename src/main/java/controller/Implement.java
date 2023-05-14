package controller;

import java.awt.event.KeyEvent;

import model.Bird;
public interface Implement {
    
    public void controller(Bird bird, KeyEvent kevent);
    public void controllerReleased(Bird bird, KeyEvent kevent);
}