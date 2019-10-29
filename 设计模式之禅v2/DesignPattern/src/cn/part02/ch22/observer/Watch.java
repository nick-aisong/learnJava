package cn.part02.ch22.observer;

/**
 * Created by NKS on 2017/9/19.
 */
class Watch extends Thread {
    private HanFeiZi hanFeiZi;
    private LiSi liSi;
    private String type;

    public Watch(HanFeiZi _hanfeizi, LiSi _liSi, String _type) {
        this.hanFeiZi = _hanfeizi;
        this.liSi = _liSi;
        this.type = _type;
    }

    @Override
    public void run() {
        while (true) {
            if (this.type.equalsIgnoreCase("breakfast")) {
                if (this.hanFeiZi.isHaveBreakfast()) {
                    this.liSi.update("韩非子在吃饭");
                    this.hanFeiZi.setHaveBreakfast(false);
                }
            } else {
                if (this.hanFeiZi.isHaveFun()) {
                    this.liSi.update("韩非子在娱乐");
                    this.hanFeiZi.setHaveFun(false);
                }
            }
        }
    }
}
