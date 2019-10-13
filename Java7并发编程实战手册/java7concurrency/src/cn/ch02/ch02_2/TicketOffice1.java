package cn.ch02.ch02_2;

// 9.实现售票处类TicketOffice1它实现了Runnable接口
public class TicketOffice1 implements Runnable {

    // 10.声明电影院Cinema对象，并且通过构造器对其进行初始化
    private Cinema cinema;

    public TicketOffice1(Cinema cinema) {
        this.cinema = cinema;
    }

    // 11.实现run()方法，它模拟了对两个电影院的操作
    @Override
    public void run() {
        cinema.sellTickets1(3);
        cinema.sellTickets1(2);
        cinema.sellTickets2(2);
        cinema.returnTickets1(3);
        cinema.sellTickets1(5);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
    }
}
