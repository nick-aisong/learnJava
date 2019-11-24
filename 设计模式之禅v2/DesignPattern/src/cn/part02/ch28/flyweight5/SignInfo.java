package cn.part02.ch28.flyweight5;

//代码清单28-1 报考信息
public class SignInfo {
    //报名人员的ID
    private String id;
    //考试地点
    private String location;
    //考试科目
    private String subject;
    //邮寄地址
    private String postAddress;
    //外部状态
    private ExtrinsicState state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public ExtrinsicState getState() {
        return state;
    }

    public void setState(ExtrinsicState state) {
        this.state = state;
    }
}
