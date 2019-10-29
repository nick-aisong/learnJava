package cn.part02.ch08.factoryMethod;

public class NvWa {
    public static void main(String[] args) {
        //声明阴阳八卦炉
        AbstractHumanFactory YinYangLu = new HumanFactory();
        //女娲第一次造人，火候不足，于是白人产生了
        System.out.println("--造出的第一批人是白色人种--");
        Human whiteHuman = YinYangLu.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();
        //女娲第二次造人，火候过足，于是黑人产生了
        System.out.println("\n--造出的第二批人是黑色人种--");
        Human blackHuman = YinYangLu.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();
        //第三次造人，火候刚刚好，于是黄色人种产生了
        System.out.println("\n--造出的第三批人是黄色人种--");
        Human yellowHuman = YinYangLu.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}

//--造出的第一批人是白色人种--
//白色人种的皮肤颜色是白色的！
//白色人种会说话，一般都是但是单字节。
//
//--造出的第二批人是黑色人种--
//黑色人种的皮肤颜色是黑色的！
//黑人会说话，一般人听不懂。
//
//--造出的第三批人是黄色人种--
//黄色人种的皮肤颜色是黄色的！
//黄色人种会说话，一般说的都是双字节。