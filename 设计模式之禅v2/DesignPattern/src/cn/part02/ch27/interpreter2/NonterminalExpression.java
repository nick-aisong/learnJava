package cn.part02.ch27.interpreter2;

//代码清单27-10 非终结符表达式
public class NonterminalExpression extends Expression {
    //每个非终结符表达式都会对其他表达式产生依赖
    public NonterminalExpression(Expression... expression) {
    }

    public Object interpreter(Context ctx) {
        //进行文法处理
        return null;
    }
}
