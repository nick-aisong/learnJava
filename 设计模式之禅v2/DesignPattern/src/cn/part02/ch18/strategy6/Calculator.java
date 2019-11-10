package cn.part02.ch18.strategy6;

//代码清单18-18 策略枚举
public enum Calculator {
    //加法运算
    ADD("+") {
        public int exec(int a, int b) {
            return a + b;
        }
    },
    //减法运算
    SUB("-") {
        public int exec(int a, int b) {
            return a - b;
        }
    };
    String value = "";

    //定义成员值类型
    private Calculator(String _value) {
        this.value = _value;
    }

    //获得枚举成员的值
    public String getValue() {
        return this.value;
    }

    //声明一个抽象函数
    public abstract int exec(int a, int b);
}

//它是一个枚举
//它是一个浓缩了的策略模式的枚举