package cn.nks.iteratorImpl;

import java.util.ArrayList;

/**
 * Created by NKS on 2017/9/16.
 */
public class ProjectIterator implements IProjectIterator {

    private ArrayList<IProject> projectList = new ArrayList<IProject>();

    private int currentItem = 0;

    public ProjectIterator(ArrayList<IProject> projectList) {
        this.projectList = projectList;
    }

    @Override
    public boolean hasNext() {
        boolean b = true;
        if (this.currentItem >= projectList.size() || this.projectList.get(this.currentItem) == null) {
            b = false;
        }
        return b;
    }

    @Override
    public IProject next() {
        return (IProject) this.projectList.get(this.currentItem++);
    }

    @Override
    public void remove() {
        //暂时没有使用到
    }
}
