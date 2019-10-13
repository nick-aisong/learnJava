package cn.ch01.ch01_1;

public class Main {

	public static void main(String[] args) {
		
		for (int i = 1; i <= 10; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}
}

// 当一个程序的所有线程都运行完成时，更明确的说，当所有非守护(non-daemon)线程
// 都运行完成的时候，这个Java程序将宣告结束。如果初始线程(执行main(方法的线程)
// 结束了，其余的线程仍将继续执行直到它们运行结束。如果某一-个线程调用了System.exit()
// 指令来结束程序的执行，所有的线程都将结束

// Thread-0: 1 * 1 = 1
// Thread-0: 1 * 2 = 2
// Thread-0: 1 * 3 = 3
// Thread-0: 1 * 4 = 4
// Thread-0: 1 * 5 = 5
// Thread-0: 1 * 6 = 6
// Thread-0: 1 * 7 = 7
// Thread-0: 1 * 8 = 8
// Thread-0: 1 * 9 = 9
// Thread-9: 10 * 1 = 10
// Thread-9: 10 * 2 = 20
// Thread-9: 10 * 3 = 30
// Thread-9: 10 * 4 = 40
// Thread-9: 10 * 5 = 50
// Thread-9: 10 * 6 = 60
// Thread-9: 10 * 7 = 70
// Thread-9: 10 * 8 = 80
// Thread-9: 10 * 9 = 90
// Thread-9: 10 * 10 = 100
// Thread-6: 7 * 1 = 7
// Thread-6: 7 * 2 = 14
// Thread-6: 7 * 3 = 21
// Thread-8: 9 * 1 = 9
// Thread-8: 9 * 2 = 18
// Thread-8: 9 * 3 = 27
// Thread-8: 9 * 4 = 36
// Thread-8: 9 * 5 = 45
// Thread-8: 9 * 6 = 54
// Thread-8: 9 * 7 = 63
// Thread-8: 9 * 8 = 72
// Thread-8: 9 * 9 = 81
// Thread-7: 8 * 1 = 8
// Thread-7: 8 * 2 = 16
// Thread-7: 8 * 3 = 24
// Thread-7: 8 * 4 = 32
// Thread-7: 8 * 5 = 40
// Thread-7: 8 * 6 = 48
// Thread-7: 8 * 7 = 56
// Thread-7: 8 * 8 = 64
// Thread-7: 8 * 9 = 72
// Thread-7: 8 * 10 = 80
// Thread-5: 6 * 1 = 6
// Thread-4: 5 * 1 = 5
// Thread-4: 5 * 2 = 10
// Thread-4: 5 * 3 = 15
// Thread-4: 5 * 4 = 20
// Thread-4: 5 * 5 = 25
// Thread-4: 5 * 6 = 30
// Thread-3: 4 * 1 = 4
// Thread-3: 4 * 2 = 8
// Thread-3: 4 * 3 = 12
// Thread-3: 4 * 4 = 16
// Thread-3: 4 * 5 = 20
// Thread-3: 4 * 6 = 24
// Thread-3: 4 * 7 = 28
// Thread-3: 4 * 8 = 32
// Thread-3: 4 * 9 = 36
// Thread-3: 4 * 10 = 40
// Thread-2: 3 * 1 = 3
// Thread-2: 3 * 2 = 6
// Thread-2: 3 * 3 = 9
// Thread-2: 3 * 4 = 12
// Thread-2: 3 * 5 = 15
// Thread-1: 2 * 1 = 2
// Thread-2: 3 * 6 = 18
// Thread-2: 3 * 7 = 21
// Thread-2: 3 * 8 = 24
// Thread-2: 3 * 9 = 27
// Thread-2: 3 * 10 = 30
// Thread-4: 5 * 7 = 35
// Thread-4: 5 * 8 = 40
// Thread-4: 5 * 9 = 45
// Thread-4: 5 * 10 = 50
// Thread-5: 6 * 2 = 12
// Thread-5: 6 * 3 = 18
// Thread-5: 6 * 4 = 24
// Thread-5: 6 * 5 = 30
// Thread-8: 9 * 10 = 90
// Thread-6: 7 * 4 = 28
// Thread-0: 1 * 10 = 10
// Thread-6: 7 * 5 = 35
// Thread-5: 6 * 6 = 36
// Thread-5: 6 * 7 = 42
// Thread-5: 6 * 8 = 48
// Thread-5: 6 * 9 = 54
// Thread-5: 6 * 10 = 60
// Thread-1: 2 * 2 = 4
// Thread-6: 7 * 6 = 42
// Thread-6: 7 * 7 = 49
// Thread-6: 7 * 8 = 56
// Thread-6: 7 * 9 = 63
// Thread-6: 7 * 10 = 70
// Thread-1: 2 * 3 = 6
// Thread-1: 2 * 4 = 8
// Thread-1: 2 * 5 = 10
// Thread-1: 2 * 6 = 12
// Thread-1: 2 * 7 = 14
// Thread-1: 2 * 8 = 16
// Thread-1: 2 * 9 = 18
// Thread-1: 2 * 10 = 20
