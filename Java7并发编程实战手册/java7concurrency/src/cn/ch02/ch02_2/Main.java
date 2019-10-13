package cn.ch02.ch02_2;

// 这个范例模拟了有两个屏幕和两个售票处的电影院
// 一个售票处卖出的一张票，只能用于其中一个电影院，不能同时用于两个电影院
// 因此每个电影院的剩余票数是独立的属性

// 15.实现范例的主程序，创建一个带有main()方法的主类Main
public class Main {
    public static void main(String[] args) {
        // 16.声明并创建电影院Cinema对象
        Cinema cinema = new Cinema();

        // 17.创建售票处类TicketOffice1对象，并用它作为传入参数创建线程
        TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
        Thread thread1 = new Thread(ticketOffice1, "TicketOffice1");

        // 18.创建售票处类TicketOffice2对象，并用它作为传入参数创建线程
        TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
        Thread thread2 = new Thread(ticketOffice2, "TicketOffice2");

        // 19.启动这两个线程
        thread1.start();
        thread2.start();

        // 20.等待这两个线程运行结束
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 21.将两个电影院的余票信息打印到控制台
        System.out.printf("Room 1 Vacancies: %d\n", cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies: %d\n", cinema.getVacanciesCinema2());
    }
}

// Room 1 Vacancies: 5
// Room 2 Vacancies: 6