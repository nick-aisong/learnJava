package cn.nks.ch01_3;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("---------------------");
		Thread task = new PrimeGenerator();
		task.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		task.interrupt();
		System.out.println("---------------------");
	}

}
