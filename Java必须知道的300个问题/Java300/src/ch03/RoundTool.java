package ch03;

// 问：在程序中经常需要对数值进行舍入处理，而Math类提供的rint()和round()方法，并不能满足实际应用中的所有需要，因此可以自定义一个类来实现四舍五入功能。
// 请问该如何自定义类来实现数值的四舍五入呢?

// 答：为了能够根据需要保留相应的小数位数，并进行四舍五入处理，可以创建一个自定义类RoundTool，在该类中创建能够根据需要进行四舍五入处理的round()方法，以满足不同的需要
public class RoundTool {

    public static String round(double value, int dotNum) {
        String strValue = String.valueOf(value); //转换为字符串
        int pos = strValue.indexOf(".");  //小数点的位置
        int len = strValue.length(); //数值总位数
        int dotLen = len - 1 - pos; //小数的位数
        double endValue = 0.0; //保存运算结果的中间变量
        String endNum = ""; //保存最终结果的变量

        if (dotNum < dotLen) { //需要保留的小数位数小于实际小数位数
            String cNum = strValue.substring(pos + dotNum + 1, pos + dotNum + 2);//获得需要进位的小数位
            int iNum = Integer.valueOf(cNum); //转换为整数

            //获得需要保留的未进行舍入处理的数值
            String sNum = strValue.substring(0, pos + dotNum + 1);
            endValue = Double.valueOf(sNum); //转换为double型
            if (iNum >= 5) { //如果需要舍入的值大于等于5
                String endPos = ""; //存放需要进位的小数值
                String dotValue = ""; //连接小数点后的多个0
                for (int i = 1; i < dotNum; i++) {
                    dotValue = dotValue + "0"; //将小数点后的多个0连接到一起
                }
                endPos = "0." + dotValue + "1"; //需要进位的小数值
                endValue = endValue + Double.valueOf(endPos); //四舍五入处理之后的值
                strValue = String.valueOf(endValue); //处理后的值转换为字符串
                pos = strValue.indexOf("."); //小数点的位置
                len = strValue.length(); //数值总位数
                dotLen = len - 1 - pos; //小数的位数
                if (dotLen < dotNum) { //如果小数位数不足，则补足位数
                    for (int i = pos + dotLen + 1; i < pos + dotNum + 1; i++) {
                        endNum = String.valueOf(endValue) + "0"; //补足小数位数
                    }
                } else { //如果小数位数正好，或超过要求，则进行截位处理
                    endNum = String.valueOf(endValue).substring(0, pos + dotNum + 1);
                }
            } else {
                endNum = strValue.substring(0, strValue.indexOf(".")) +
                        strValue.substring(strValue.indexOf("."), strValue.indexOf(".") + dotNum + 1);
            }
        } else if (dotNum == dotLen) {
            endNum = String.valueOf(value); //小数位数与要求的位数相同，直接转换为字符串
        } else { //如果小数位数不足，则补足位数
            for (int i = 1; i <= dotNum - dotLen; i++) {
                strValue = strValue + "0"; //补足小数位数
            }
            endNum = strValue; //最终的值
        }
        return endNum;
    }

    public static void main(String[] args) {
        System.out.println("数值123.121保留两位小数: \t" + RoundTool.round(123.121, 2));
        System.out.println("数值123.456789保留3位小数: \t" + RoundTool.round(123.456789, 3));
        System.out.println("数值123.1231保留3位小数: \t" + RoundTool.round(123.1231, 3));
        System.out.println("数值123.5保留3位小数: \t\t" + RoundTool.round(123.5, 3));
    }
}

// 数值123.121保留两位小数: 	123.12
// 数值123.456789保留3位小数: 	123.457
// 数值123.1231保留3位小数: 	123.123
// 数值123.5保留3位小数: 		123.500
