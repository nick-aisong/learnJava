package cn.part02.ch21.composite6;

import java.util.ArrayList;

//代码清单21-23 树叶节点
public class Leaf extends Component {
    @Deprecated
    public void add(Component component) throws UnsupportedOperationException {
        //空实现,直接抛弃一个"不支持请求"异常
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void remove(Component component) throws UnsupportedOperationException {
        //空实现
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public ArrayList<Component> getChildren() throws UnsupportedOperationException {
        //空实现
        throw new UnsupportedOperationException();
    }
}

//为什么要加个Deprecated注解呢？就是在编译器期告诉调用者，你可以调我这个方法，
//但是可能出现错误哦，我已经告诉你“该方法已经失效”了，你还使用那在运行期也会抛出
//UnsupportedOperationException异常