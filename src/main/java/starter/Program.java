package starter;

import interaction.Command;
import interaction.ConsoleMenu;
import interaction.UserConfigurationBuilder;
import interaction.UserInteractionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import model.entities.Cell;
import org.xml.sax.SAXException;
import visualization.Visualization;
import model.CellularAutomat;

import javax.xml.parsers.ParserConfigurationException;


public class Program {

    static int Width = 80;
    static int Height = 25;

    public void run(CellularAutomat ca) {

        CellularAutomat a = ca;
        System.out.println("run");
        if (ca == null)
            a = new CellularAutomat(Width, Height);

        Visualization v = Visualization.getVisualization();
        AtomicBoolean isStopped = new AtomicBoolean();

        isStopped.set(false);
        ConcurrentLinkedQueue<Command> queue = new ConcurrentLinkedQueue<Command>();
        UserInteractionHandler r = new UserInteractionHandler(isStopped, queue, v.getConsole());
        Thread keyHandler = new Thread(r);
        keyHandler.start();

        try {

            int i = 0;
            double speed = 1;
            boolean isExit = false;

            while (a.getCountObject() != 0 && !isExit) {
                System.out.println("step: " + i);
                v.toReflect(a);
                a.step(v);

                Thread.sleep((long) (speed * 1000));

                while (!queue.isEmpty()) {
                    Command c = queue.poll();

                    switch (c) {
                        case FASTER:
                            System.out.println("FASTER");
                            speed *= 2;
                            break;
                        case SLOWER:
                            System.out.println("SLOWER");
                            speed /= 2;
                            break;
                        case EXIT:
                            System.out.println("STOP");
                            isExit = true;
                            break;
                        case DUMP:
                            interaction.Serialization.save(a, "cellular.ca");
                            System.out.println("SAVE");
                            break;
                        default:
                            System.out.println("UNKNOWN");
                            break;
                    }
                }

                i++;
            }
            v.toReflect(a);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    public static void main(String[] args) {
        interaction.ConsoleMenu cmenu = new ConsoleMenu();
        int i = 4;
        try {
            while (i != 0 && i != 1 && i != -3) {
                if (i != -2) {
                    System.out.println(cmenu.getDescription());
                }
                    i = cmenu.menu((char) System.in.read());

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        CellularAutomat ca = null;
        if (i == -3) {
            if (cmenu.getCurrent() == 2) {
                interaction.UserConfigurationBuilder UCB
                        = new UserConfigurationBuilder(cmenu.getPath());
                try {
                    UCB.generate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ArrayList<Cell> cells= UCB.getList();
                int width = UCB.getWidth();
                int height = UCB.getHeight();
                ca = new CellularAutomat(width, height, cells);
            }
            if (cmenu.getCurrent() == 1) {
                try {
                    ca = interaction.Serialization.load(cmenu.getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (i == 1 || i == -3)
            new Program().run(ca);


    }
}
