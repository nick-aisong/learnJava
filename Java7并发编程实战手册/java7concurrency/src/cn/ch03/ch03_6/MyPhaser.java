package cn.ch03.ch03_6;

import java.util.concurrent.Phaser;

// 1.创建MyPhaser类并继承Phaser类
public class MyPhaser extends Phaser {
    // 2.覆盖onAdvance()方法。根据传入参数phase的值，调用不同的辅助方法
    // 如果phase是0，将调用studentsArrived()方法
    // 如果phase是1，将调用finishFirstExercise()方法
    // 如果phase是2，将调用finishSecondExercise()方法
    // 如果phase是3，将调用finishExam()方法
    // 如果不是上述值，将返回true表明这个phaser已经终止了
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    // 3.实现辅助方法studentsArrived()。它打印两条信息到控制台，并且返回false表明phaser已经开始执行了
    private boolean studentsArrived() {
        System.out.printf("Phaser: The exam are going to start. The students are ready. \n");
        System.out.printf("Phaser: We have %d students.\n", getRegisteredParties());
        return false;
    }

    // 4.实现辅助方法finishFirstExercise()。它打印两条信息到控制台，并且返回false表明phaser在继续执行中
    private boolean finishFirstExercise() {
        System.out.printf("Phaser: All the students have finished the first exercise.\n");
        System.out.printf("Phaser: It's time for the second one. \n");
        return false;
    }

    // 5.实现辅助方法finishSecondExercise()。它打印两条信息到控制台，并且返回false表明phaser在继续执行中
    private boolean finishSecondExercise() {
        System.out.printf("Phaser: Al1 the students have finished the second exercise. \n");
        System.out.printf("Phaser: It's time for the third one. \n");
        return false;
    }

    // 6.实现辅助方法finishExam()。它打印两条信息到控制台，并且返回true表明phaser已经完成了
    private boolean finishExam() {
        System.out.printf("Phaser: All the students have finished the exam. \n");
        System.out.printf("Phaser: Thank you for your time.\n");
        return true;
    }
}
