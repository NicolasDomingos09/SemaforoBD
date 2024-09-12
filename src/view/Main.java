package view;
import controller.ThreadDB;
import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for (int ID = 1; ID <= 21; ID++) {
			Thread t = new ThreadDB(semaforo, ID);
			t.start();
		}
	}
}