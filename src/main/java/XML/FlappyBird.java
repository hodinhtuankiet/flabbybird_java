package XML;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.Bird;
import model.Cot;
import model.TubeColumn;

import java.io.File;
import java.nio.channels.Pipe;

public class FlappyBird {
    private int speed;
    private int gravity;
    private int jumpPower;
    private int screenWidth;
    private int screenHeight;
    private Bird bird;
    private Cot[] pipes;

    public FlappyBird(File xmlFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("D:\\BaiTap_JAVA\\BaiTapJava\\src\\main\\java\\XML\\Script.xml");

            Element config = (Element) doc.getElementsByTagName("config").item(0);
            this.speed = Integer.parseInt(config.getElementsByTagName("speed").item(0).getTextContent());
            this.gravity = Integer.parseInt(config.getElementsByTagName("gravity").item(0).getTextContent());
            this.jumpPower = Integer.parseInt(config.getElementsByTagName("jumpPower").item(0).getTextContent());

            Element screen = (Element) doc.getElementsByTagName("screen").item(0);
            this.screenWidth = Integer.parseInt(screen.getElementsByTagName("width").item(0).getTextContent());
            this.screenHeight = Integer.parseInt(screen.getElementsByTagName("height").item(0).getTextContent());

            Element birdElement = (Element) doc.getElementsByTagName("bird").item(0);
            int x = Integer.parseInt(birdElement.getElementsByTagName("x").item(0).getTextContent());
            int y = Integer.parseInt(birdElement.getElementsByTagName("y").item(0).getTextContent());
            int width = Integer.parseInt(birdElement.getElementsByTagName("width").item(0).getTextContent());
            int height = Integer.parseInt(birdElement.getElementsByTagName("height").item(0).getTextContent());
            this.bird = new Bird(x, y);

            NodeList pipesList = doc.getElementsByTagName("pipe");
            this.pipes = new Cot[pipesList.getLength()];
            for (int i = 0; i < pipesList.getLength(); i++) {
                Element pipeElement = (Element) pipesList.item(i);
                int pipeX = Integer.parseInt(pipeElement.getElementsByTagName("x").item(0).getTextContent());
                int pipeY = Integer.parseInt(pipeElement.getElementsByTagName("y").item(0).getTextContent());
                int pipeWidth = Integer.parseInt(pipeElement.getElementsByTagName("width").item(0).getTextContent());
                int pipeHeight = Integer.parseInt(pipeElement.getElementsByTagName("height").item(0).getTextContent());
              this.pipes[i] = new Cot(pipeX, pipeY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
