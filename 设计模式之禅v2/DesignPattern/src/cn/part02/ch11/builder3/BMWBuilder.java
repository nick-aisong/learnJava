package cn.part02.ch11.builder3;

import java.util.ArrayList;

//代码清单11-7 宝马车组装者
public class BMWBuilder extends CarBuilder {
    private BMWModel bmw = new BMWModel();

    public CarModel getCarModel() {
        return this.bmw;
    }

    public void setSequence(ArrayList<String> sequence) {
        this.bmw.setSequence(sequence);
    }
}
