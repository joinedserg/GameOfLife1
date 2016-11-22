package model.rules;

import java.util.*;
import java.awt.Point;

import model.entities.Specimen;
import model.entities.Cell;
import model.Board;
import model.RemotenessComparator;

/*
 * Ищет еду и перемещается к ней:-)
 * волновой алгоритм поиска пути
 * на перемещение тратится скорость
 * 
 */
public class RuleMove2Eat extends Rule {
	
	@Override
	public void apply(Specimen c, Board b) {
		
		stepMove2Eat(c, b);
	}
	
	public void stepMove2Eat(Specimen c, Board b) {
		Point finish = null;
		
		List<Cell> eatCandidates = b.getEatCandidate(c);
		eatCandidates.sort(new RemotenessComparator());
		
		if(eatCandidates.isEmpty()) {
			//поблизости еды нет :-(, не хватает скорости, если это растение
			//не хватает скорости + видимости, если хищник
			
			List<Point> listPlaces = b.getEmptyPlaces(c, c.getSight());
			if(!listPlaces.isEmpty()) {
				//если еду не нашел, то передвигаться в случайное пустое место
				//путь туда будет проложен по волновому алгоритму
				int r = new Random().nextInt(listPlaces.size());	
				finish = new Point((int)listPlaces.get(r).getX(),(int)listPlaces.get(r).getY());
				//finish = new Point(0,1);
			}
			else {
				return;
			}
		}
		else {
			//путь к "ближайшей" еде (ближайшей в плане "градиентном"(:-)) плане, а не в плане кратчайшего пути
			finish = new Point(eatCandidates.get(0).getX(), eatCandidates.get(0).getY());
		}
		
		List<Point> path = b.getPathShorterstWay(new Point(c.getX(), c.getY()), finish);		
		if(path == null) {
			//путь не найден :-(
			return;
		}
		
		Iterator itr = path.iterator();
		do { //уменьшается скорость, приближаемся к цели
			Point p = (Point) itr.next();
			c.setX(p.x); c.setY(p.y);
			itr.remove();
		}
		while(c.step() != 0 && itr.hasNext());
		//попытаться пройти этот путь	
		b.clearCell(c);
		b.addCell(c);
		
	}

}
