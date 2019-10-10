package cn.nks.builder;

import java.util.ArrayList;

/**
 * Created by NKS on 2017/9/16.
 */
public abstract class CarBuilder {

    public abstract void setSequence(ArrayList<String> sequence);

    public abstract CarModel getCarModel();

}
