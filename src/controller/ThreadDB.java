package controller;
import java.util.Random;
import java.util.concurrent.Semaphore;


public class ThreadDB extends Thread{
	
	Semaphore semaforo;
	int ID;
	int min, max, mod;
	
	public ThreadDB(Semaphore semaforo, int ID) {
		this.semaforo = semaforo;
		this.ID = ID;
	}
	
	private void calc(){
		mod = ID % 3;
		Random rand = new Random();
		
		tempo();
		
		try {
			System.out.println("Thread #" + ID + " calculando");
			Thread.sleep(rand.nextInt(min, max));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void tempo() {
		switch(mod) {
			case 0:
				min = 200;
				max = 1000;
				break;
			case 1:
				min = 500;
				max = 1500;
				break;
			case 2:
				min = 1000;
				max = 2000;
				break;
		}
	}
	
	private void acessoBD() {
		try {
			System.out.println("Thread #" + ID + " acessando banco de dados");
			if(mod == 1)
				Thread.sleep(1000);
			else 
				Thread.sleep(1500);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		calc();
		
		try {
			semaforo.acquire();
			acessoBD();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}
}
