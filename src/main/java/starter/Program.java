package starter;

import interaction.Command;
import interaction.UserInteractionHandler;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import visualization.Visualization;
import model.CellularAutomat;


public class Program {

	static int Width = 80;
	static int Height = 25;

	public void run() {
		System.out.println("run");
		CellularAutomat a = new CellularAutomat(Width, Height);
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

			while(a.getCountObject() != 0 && !isExit) {
				System.out.println("step: " + i);
				v.toReflect(a);
				a.step(v);

				Thread.sleep((long) (speed * 250));

				while(!queue.isEmpty()) {
					Command c = queue.poll();

					switch(c) {
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
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}


	}


	public static void main(String[] args) {
		new Program().run();

	}
}
