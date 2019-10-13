package cn.ch02.ch02_2;

// 1.创建一个电影院类Cinema,增加两个long属性vacanciesCinema1和vacanciesCinema2。
public class Cinema {
    private long vacanciesCinemal;
    private long vacanciesCinema2;

    //  2.再为电影院类Cinema增加两个对象属性controlCinema1和controlCinema2
    private final Object controlCinemal, controlCinema2;

    // 3.实现电影院类Cinema的构造器，并初始化这4个属性
    public Cinema() {
        controlCinemal = new Object();
        controlCinema2 = new Object();
        vacanciesCinemal = 20;
        vacanciesCinema2 = 20;
    }

    // 4.实现sellTickets1()方法，当第一个电影院有票卖出的时候将调用这个方法
    // 它使用controlCinema1对象控制同步代码块的访问
    public boolean sellTickets1(int number) {
        synchronized (controlCinemal) {
            if (number < vacanciesCinemal) {
                vacanciesCinemal -= number;
                return true;
            } else {
                return false;
            }
        }
    }
    // 5.实现sellTickets2()方法，当第二个电影院有票卖出的时候将调用这个方法
    // 它使用controlCinema2对象控制同步代码块的访问
    public boolean sellTickets2(int number) {
        synchronized (controlCinema2) {
            if (number < vacanciesCinema2) {
                vacanciesCinema2 -= number;
                return true;
            } else {
                return false;
            }
        }
    }
    // 6.实现returnTickets1()方法，当第一个电影院有票退回的时候将调用这个方法
    // 它使用controlCinema1对象控制同步代码块的访问
    public boolean returnTickets1(int number) {
        synchronized (controlCinemal) {
            vacanciesCinemal += number;
            return true;
        }
    }
    // 7.实现returnTickets2()方法，当第二个电影院有票退回的时候将调用这个方法
    // 它使用controlCinema2对象控制同步代码块的访问
    public boolean returnTickets2(int number) {
        synchronized (controlCinema2) {
            vacanciesCinema2 += number;
            return true;
        }
    }

    // 8.实现另外两个方法，它们返回各自电影院的票数
    public long getVacanciesCinema1() {
        return vacanciesCinemal;
    }

    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }
}