package interaction;

import java.util.concurrent.RunnableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;


public class UserInteractionHandler implements Runnable {
	
	AtomicBoolean isStopped;
	ConcurrentLinkedQueue<Command> queue;
	ConsoleSystemInterface cons;
	
	public UserInteractionHandler(AtomicBoolean isStopped, ConcurrentLinkedQueue queue, ConsoleSystemInterface cons) {
		this.isStopped = isStopped;
		this.queue = queue;
		this.cons = cons;
	}
	
	public void run() {
		
		while(!Thread.interrupted() && !isStopped.get()) {
			CharKey k = cons.inkey();
			
			switch(k.code) {
			case CharKey.f:
				queue.add(Command.FASTER);
				break;
			case CharKey.s:
				queue.add(Command.SLOWER);
				break;
			case CharKey.d:
				queue.add(Command.DUMP);
				break;
			case CharKey.e: 
				queue.add(Command.EXIT);
				break;
			default:
				break;
			}
			
			try {
				Thread.currentThread().sleep(10);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
		
}