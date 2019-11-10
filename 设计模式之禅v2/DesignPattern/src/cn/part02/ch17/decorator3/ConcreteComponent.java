package cn.part02.ch17.decorator3;

//代码清单17-11 具体构件
public class ConcreteComponent extends Component {
    //具体实现
    @Override
    public void operate() {
        System.out.println("do Something");
    }
}