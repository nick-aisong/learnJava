package cn.nks.factory;

public class NvWa {

	public static void main(String[] args) {
		AbstractHumanFactory YinYangLu = new HumanFactory();

		WhiteHuman whiteHuman = YinYangLu.createHuman(WhiteHuman.class);
		whiteHuman.getColor();
		whiteHuman.talk();

		BlackHuman blackHuman = YinYangLu.createHuman(BlackHuman.class);
		blackHuman.getColor();
		blackHuman.talk();

		YellowHuman yellowHuman = YinYangLu.createHuman(YellowHuman.class);
		yellowHuman.getColor();
		yellowHuman.talk();

	}
}
