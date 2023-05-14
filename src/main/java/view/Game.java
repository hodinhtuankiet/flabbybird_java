package view;

import controller.Controller;
import controller.DAO;
import model.Bird;
import model.Cot;
import model.TubeColumn;
import model.User;
import proxy.ProxyImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import view.Login;

import javax.swing.JPanel;
import javax.swing.Timer;
public class Game extends JPanel implements ActionListener {
	private Login login;
    private boolean isRunning = false;
    private ProxyImage proxyImage;
    private Image background;
    private Bird bird;
    private TubeColumn tubeColumn;
    private int score;
    private int highScore;

	public Game(Login login) {
        this.login = login;
        proxyImage = new ProxyImage("/anh/gg.jpg");
        background = proxyImage.loadImage().getImage();
        setFocusable(true);
        setDoubleBuffered(false);
        addKeyListener(new GameKeyAdapter());
        Timer timer = new Timer(15, this);
        timer.start();
    }
    public Game() {
        proxyImage = new ProxyImage("/anh/gg.jpg");
        background = proxyImage.loadImage().getImage();
        setFocusable(true);
        setDoubleBuffered(false);
        addKeyListener(new GameKeyAdapter());
        Timer timer = new Timer(15, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        if (isRunning) {
            bird.tick();
            tubeColumn.tick();
            checkColision();
            score++;
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, 0, null);
        if (isRunning) {
            this.bird.render(g2, this);
            this.tubeColumn.render(g2, this);
            g2.setColor(Color.RED);
            g.setFont(new Font("Arial", 1, 20));
            g2.drawString("Your score: " + this.tubeColumn.getPoints(), 10, 20);
        } else {
            g2.setColor(Color.RED);
            g.setFont(new Font("Arial", 1, 20));
            g2.drawString("Press Enter to Start the Game", Window.WIDTH / 2 - 150, Window.HEIGHT / 2);
            g2.drawString("High Score: " + highScore, Window.WIDTH / 2 - 150, 300);
            String name = login.textField.getText();
//            String name1 = "asdsadas";
            g2.drawString("User: " + name.toUpperCase(), Window.WIDTH / 2 - 150, 340);

            g2.setColor(Color.black);
            g.setFont(new Font("Arial", 1, 15));
            //lưu file
            try {
                File file = new File("D:\\BaiTap_JAVA\\java.txt");
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(Integer.toString(highScore));
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g2.setColor(Color.RED);
        g.setFont(new Font("Arial", 1, 20));
        g2.drawString("High Score: " + highScore, Window.WIDTH - 160, 20);

        g.dispose();
    }

    private void restartGame() {
        if (!isRunning) {
            this.isRunning = true;
            this.bird = new Bird(Window.WIDTH / 2, Window.HEIGHT / 2);
            this.tubeColumn = new TubeColumn();
        }
    }
    private void endGame() {
        Thread endGameThread = new Thread(new Runnable() {
            public void run() {
                isRunning = false;
                if (tubeColumn.getPoints() > highScore) {
                    highScore = tubeColumn.getPoints();
                }
                tubeColumn.setPoints(0);
            }
        });

        endGameThread.start();
    }

    private void checkColision() {
        final Rectangle rectBird = this.bird.getBounds();

        for (int i = 0; i < this.tubeColumn.getTubes().size(); i++) {
            Cot tempTube = this.tubeColumn.getTubes().get(i);
            final Rectangle rectTube = tempTube.getBounds();

            Thread collisionThread = new Thread(new Runnable() {
                public void run() {
                    if (rectBird.intersects(rectTube)) {
                        endGame();
                        User user = new User();
                    	user.setName(Login.textField.getText());
                    	user.setScore(highScore);
                    	new DAO().addUser(user);
                    }
                }
            });

            collisionThread.start();
        }
    }

    // Key
    private class GameKeyAdapter extends KeyAdapter {

        private final Controller controller;

        public GameKeyAdapter() {
            controller = new Controller();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            	
                restartGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (isRunning) {
                controller.controllerReleased(bird, e);
            }
        }
    }
}
