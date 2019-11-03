package cn.part02.ch11.builder2;

import java.util.ArrayList;

//代码清单11-6 奔驰车组装者
public class BenzBuilder extends CarBuilder {
    private BenzModel benz = new BenzModel();

    public CarModel getCarModel() {
        return this.benz;
    }

    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }
}