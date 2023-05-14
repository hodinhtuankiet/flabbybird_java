package model;

import java.awt.Graphics2D;

import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import view.Window;
public class TubeColumn {

    private int base = Window.HEIGHT - 60;

    private List<Cot> tubes;
    private Random random;
    private int points = 0;
    private int speed = 5;
    private int changeSpeed = speed;
    
    public TubeColumn(int base, List<Cot> tubes, Random random, int points, int speed, int changeSpeed) {
		this.base = base;
		this.tubes = tubes;
		this.random = random;
		this.points = points;
		this.speed = speed;
		this.changeSpeed = changeSpeed;
	}

	public TubeColumn() {
        tubes = new ArrayList<Cot>();
        random = new Random();
        initTubes();
    }

	public void initTubes() {
	    Thread thread = new Thread() {
	        @Override
	        public void run() {
	            int last = base;
	            int randWay = random.nextInt(10);

	            for (int i = 0; i < 20; i++) {

	                Cot tempTube = new Cot(900, last);
	                tempTube.setDx(speed);
	                last = tempTube.getY() - tempTube.getHeight();
	                if (i < randWay || i > randWay + 4) {
	                    tubes.add(tempTube);
	                }
	            }
	        }
	    };
	    thread.start();
	}

    public void tick() {

        for (int i = 0; i < tubes.size(); i++) {
            tubes.get(i).tick();

            if (tubes.get(i).getX() < 0) {
                tubes.remove(tubes.get(i));
            }
        }
        if (tubes.isEmpty()) {
            this.points += 1;
            if (changeSpeed == points) {
                this.speed += 1;
                changeSpeed += 5;
                System.out.println(speed);
                
            }
            initTubes();
        }

    }

    public void render(Graphics2D g, ImageObserver obs) {
        for (int i = 0; i < tubes.size(); i++) {
            tubes.get(i).render(g, obs);
        }
    }

    public List<Cot> getTubes() {
        return tubes;
    }

    public void setTubes(List<Cot> tubes) {
        this.tubes = tubes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
