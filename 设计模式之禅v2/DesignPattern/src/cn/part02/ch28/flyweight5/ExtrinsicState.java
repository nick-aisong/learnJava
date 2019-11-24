package cn.part02.ch28.flyweight5;

//代码清单28-13 外部状态类
public class ExtrinsicState {
    //考试科目
    private String subject;
    //考试地点
    private String location;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExtrinsicState) {
            ExtrinsicState state = (ExtrinsicState) obj;
            return state.getLocation().equals(location) && state.getSubject().equals(subject);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return subject.hashCode() + location.hashCode();
    }
}

//注意，一定要覆写equals和hashCode方法，否则它作为HashMap中的key值是根本没有意
//义的，只有hashCode值相等，并且equals返回结果为true，两个对象才相等，也只有在这种情
//况下才有可能从对象池中查找获得对象