package model;

import view.Window;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import proxy.ProxyImage;
public class Bird extends GameObject {

    private ProxyImage proxyImage;
    private Cot[] tube;
    public Bird(int x, int y){
        super(x, y);
        if(proxyImage == null) {
            proxyImage = new ProxyImage("/anh/b.png");
        }
        this.image = proxyImage.loadImage().getImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.x -= width;
        this.y -= height;
        tube = new Cot[1];
        tube[0] = new Cot(900, Window.HEIGHT - 60);
        this.dy = 4;
    }
//    private Image[] sprite = new Image[2];
//    public Bird(int x, int y){
//        super(x, y);
//        proxyImage = new ProxyImage("/anh/b.png");
//        sprite[0] = proxyImage.loadImage().getImage();
//        proxyImage = new ProxyImage("/anh/chimroi.png");
//        sprite[1] = proxyImage.loadImage().getImage();
//        this.image = sprite[0];
//        this.width = image.getWidth(null);
//        this.height = image.getHeight(null);
//        this.x -= width;
//        this.y -= height;
//        tube = new Cot[1];
//        tube[0] = new Cot(900, Window.HEIGHT - 60);
//        this.dy = 4;
//    }
    @Override
    public void tick() {
        if(dy < 5) {
            dy += 2;
        }
        this.y += dy;
        tube[0].tick();
        checkWindowBorder();
    }
    public void jump() {
        if(dy > 0) {
            dy = 0;
        }
        dy -= 15;
    }
//    public void jump() {
//        if(dy > 0) {
//            dy = 0;
//        }
//        dy -= 15;
//        if (dy < 0) {
//            this.image = sprite[1]; // 
//        } else {
//            this.image = sprite[0]; // Chuyển sang
//        }
//    }
    private void checkWindowBorder() {
        if(this.x > Window.WIDTH) {
            this.x = Window.WIDTH;
        }
        if(this.x < 0) {
            this.x = 0;
        }
        if(this.y > Window.HEIGHT - 50) {
            this.y = Window.HEIGHT - 50;
        }
        if(this.y < 0) {
            this.y = 0;
        }
    }

    @Override
    public void render(Graphics2D g, ImageObserver obs) {
        g.drawImage(image, x, y, obs);
        tube[0].render(g, obs);
    }
    
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
