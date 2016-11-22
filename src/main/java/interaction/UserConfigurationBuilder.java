package interaction;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import model.entities.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Никита on 20.11.2016.
 */
public class UserConfigurationBuilder {

    private final static String  path= ".\\configuration\\";
    private String configPath;
    Random rand;
    private ArrayList<Cell> cells;

    public UserConfigurationBuilder(String configName){
        this.configPath = path + configName;
        rand = new Random();
        cells = new ArrayList<Cell>();
    }

        public void generate(){
            int bound = 50;
            try {
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
                        Plant plant = new Plant(choosePosition(bound), choosePosition(bound));
                        fillSpecimen(nList,plant);
                }

                //Herbivorous
                nList = doc.getElementsByTagName("herbivorous");
                    if(nList.getLength() != 1){
                        throw new NotImplementedException();
                    }else{
                        Herbivorous hebirvorous = new Herbivorous(choosePosition(bound), choosePosition(bound));
                        fillSpecimen(nList,hebirvorous);
                    }


            }catch(Exception e) {
                e.getStackTrace();

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

            Node nNode = nList.item(0);
            System.out.println("\nCurrent Element :"
                    + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nNode;
                count = Integer.valueOf(e.getAttribute("count"));
                System.out.println("THI?SX/ = " + count);
                health = Integer.getInteger(e.getElementsByTagName("health").item(0).getTextContent());
                youth = Integer.getInteger(e.getElementsByTagName("youth").item(0).getTextContent());
                maturity = Integer.getInteger(e.getElementsByTagName("maturity").item(0).getTextContent());
                oldness = Integer.getInteger(e.getElementsByTagName("oldness").item(0).getTextContent());

                strength = Integer.getInteger(e.getElementsByTagName("strength").item(0).getTextContent());
                hunger = Integer.getInteger(e.getElementsByTagName("hunger").item(0).getTextContent());
                hunger1 = Integer.getInteger(e.getElementsByTagName("firsthunger").item(0).getTextContent());
                hunger2 = Integer.getInteger(e.getElementsByTagName("secondhunger").item(0).getTextContent());
                sight = Integer.getInteger(e.getElementsByTagName("sight").item(0).getTextContent());

                for (int i = 0; i < count; i++) {
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
                    cells.add(spec);
                }

            }


        }

        private int choosePosition(int bound){
            int result = 0;
            result+=rand.nextInt(bound);
            return result;
        }
    }
