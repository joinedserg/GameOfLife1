package interaction;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import model.entities.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import static model.entities.Type.PLANT;

/**
 * Created by Никита on 20.11.2016.
 */
public class UserConfigurationBuilder {

    private final static String  path= ".\\configuration\\";
    private String configPath;
    Random rand;
    private int width;
    private int height;
    private int speedSimulation;

    private ArrayList<Cell> cells;
    private int boundx;
    private int boundy;

    public UserConfigurationBuilder(String configName){
        this.configPath = path + configName;
        rand = new Random();
        cells = new ArrayList<Cell>();
        boundy = 24;
        boundx = 79;
        width = 0;
        height = 0;
        speedSimulation = 0;

    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getSpeedSimulatio(){
        return speedSimulation;
    }

    public ArrayList<Cell> getList(){
        return  cells;
    }


        public void generate() throws IOException, SAXException, ParserConfigurationException {

                File inputFile = new File(configPath);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();
                System.out.println("Root element :"
                        + doc.getDocumentElement().getNodeName());

                //Plants
                NodeList nList = doc.getElementsByTagName("plants");


                    if(nList.getLength() != 1){
                        //TODO: maybe make own exception
                        throw new NotImplementedException();
                    }else {
                        Plant plant = new Plant(choosePosition(boundx, 'x'), choosePosition(boundy,'y'));
                        fillSpecimen(nList,plant);
                        int i = 0;
                        i++;
                }

                //Herbivorous
                nList = doc.getElementsByTagName("herbivorous");
                    if(nList.getLength() != 1){
                        throw new NotImplementedException();
                    }else{
                        Herbivorous hebirvorous = new Herbivorous(choosePosition(boundx, 'x'), choosePosition(boundy,'y'));
                        fillSpecimen(nList,hebirvorous);
                    }
                //Predators
                nList = doc.getElementsByTagName("predators");
                if(nList.getLength() != 1){
                    throw new NotImplementedException();
                }else{
                    Predator pred = new Predator(choosePosition(boundx, 'x'), choosePosition(boundy,'y'));
                    fillSpecimen(nList, pred);
                    int t = 0;
                }

                nList = doc.getElementsByTagName("map");
            Node nNode = nList.item(0);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nNode;

                width = Integer.valueOf(e.getElementsByTagName("width").item(0).getTextContent());
                height = Integer.valueOf(e.getElementsByTagName("height").item(0).getTextContent());

                speedSimulation = Integer.valueOf(e.getElementsByTagName("speedSimulation").item(0).getTextContent());
            }
        }

        private void fillSpecimen(NodeList nList, Specimen spec){



            Integer count = 0;
            int health, youth, maturity, oldness = 0;
            int strength = 0;
            int courage = 0;
            int hunger = 0;
            int hunger1 = 0;
            int hunger2 = 0;
            int sight = 0;

            String countS,healthS,youthS,maturityS, courageS, oldnessS,strengthS,hungerS,h1S,h2S, sightS;


            Node nNode = nList.item(0);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nNode;

                countS = e.getAttribute("count");
                count = (countS != null && countS.length() != 0)?Integer.valueOf(countS) : 0;


                    healthS = e.getElementsByTagName("health").item(0).getTextContent();
                    youthS = e.getElementsByTagName("youth").item(0).getTextContent();
                    maturityS = e.getElementsByTagName("maturity").item(0).getTextContent();
                    oldnessS = e.getElementsByTagName("oldness").item(0).getTextContent();
                    try {
                        strengthS = e.getElementsByTagName("strength").item(0).getTextContent();
                    } catch (Exception ex) {
                        strengthS = "0";
                    }
                    try {
                        courageS = e.getElementsByTagName("courage").item(0).getTextContent();
                    } catch (Exception ex) {
                        courageS = "0";
                    }
                    try {
                        hungerS = e.getElementsByTagName("hunger").item(0).getTextContent();
                    } catch (Exception ex) {
                        hungerS = "0";
                    }
                    try {
                        h1S = e.getElementsByTagName("firsthunger").item(0).getTextContent();
                    } catch (Exception ex) {
                        h1S = "0";
                    }
                    try {
                        h2S = e.getElementsByTagName("secondhunger").item(0).getTextContent();
                    } catch (Exception ex) {
                        h2S = "0";
                    }
                    try {
                        sightS = e.getElementsByTagName("sight").item(0).getTextContent();
                    } catch (Exception ex) {
                        sightS = "0";
                    }

                    health = (healthS != null && healthS.length() != 0) ? Integer.valueOf(healthS) : 0;
                    youth = (youthS != null && youthS.length() != 0) ? Integer.valueOf(youthS) : 0;
                    maturity = (maturityS != null && maturityS.length() != 0) ? Integer.valueOf(maturityS) : 0;
                    oldness = (oldnessS != null && oldnessS.length() != 0) ? Integer.valueOf(oldnessS) : 0;

                    strength = (strengthS != null && strengthS.length() != 0) ? Integer.valueOf(strengthS) : 0;
                    hunger = (hungerS != null && hungerS.length() != 0) ? Integer.valueOf(hungerS) : 0;
                    hunger1 = (h1S != null && h1S.length() != 0) ? Integer.valueOf(h1S) : 0;
                    hunger2 = (h2S != null && h2S.length() != 0) ? Integer.valueOf(h2S) : 0;
                    sight = (sightS != null && sightS.length() != 0) ? Integer.valueOf(sightS) : 0;

                    courage = (courageS != null && courageS.length() != 0) ? Integer.valueOf(courageS) : 0;


                    int speed = 10;

                    for (int i = 0; i < count; i++) {
                        switch (spec.getType()) {
                            case PLANT:
                                spec = new Plant(choosePosition(boundx, 'x'), choosePosition(boundy, 'y'));
                                speed = 0;
                                break;
                            case HERBIVORUS:
                                spec = new Herbivorous(choosePosition(boundx, 'x'), choosePosition(boundy, 'y'));
                                break;
                            case PREDATOR:
                                spec = new Predator(choosePosition(boundx, 'x'), choosePosition(boundy, 'y'));
                                break;
                        }
                        spec.setHealth(health);
                        spec.setYouth(youth);
                        spec.setMaturity(maturity);
                        spec.setOldness(oldness);
                        spec.setHunger(hunger);
                        spec.setHungerFirstLimit(hunger1);
                        spec.setHungerSecondLimit(hunger2);
                        spec.setStrength(strength);
                        spec.setSight(sight);
                        spec.setCourage(courage);
                        spec.setSpeed(speed);
                        cells.add(spec);
                    }

            }


        }

        private int choosePosition(int bound, char p){
            int result = 0;
            boolean ok = false;
            while(!ok)
            {
            result=rand.nextInt(bound);
                ok = true;
            for (Cell c: cells) {
                if(p == 'x')
                if(p == 'y')
                    if(c.getY() == result)
                        ok = false;
            }
            }
            return result;
        }
    }
