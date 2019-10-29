package cn.part02.ch11.builder;

import java.util.ArrayList;

/**
 * Created by NKS on 2017/9/16.
 */
public abstract class CarBuilder {

    public abstract void setSequence(ArrayList<String> sequence);

    public abstract CarModel getCarModel();

}
