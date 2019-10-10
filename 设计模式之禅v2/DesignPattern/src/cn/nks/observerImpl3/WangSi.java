package cn.nks.observerImpl3;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by NKS on 2017/9/19.
 */
public class WangSi implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("王斯：观察到韩非子活动，自己也开始活动了...");
        this.cry(arg.toString());
        System.out.println("王斯：真真的哭死了...\n");
    }

    private void cry(String context){
        System.out.println("王斯：因为"+context+"，——所以我悲伤呀！");
    }
}
