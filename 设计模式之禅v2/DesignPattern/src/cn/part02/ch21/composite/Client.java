package cn.part02.ch21.composite;

import java.util.ArrayList;

/**
 * Created by NKS on 2017/9/18.
 */
public class Client {

    public static void main(String[] args) {

        IRoot ceo = new Root("Root", "boss", 1000000);

        IBranch developDep = new Branch("Branch1", "DevManager", 100000);
        IBranch saleDep = new Branch("Branch2", "saleManager", 100000);
        IBranch finaceDep = new Branch("Branch3", "finaceManager", 100000);
        IBranch firstDevGroup = new Branch("B1", "devGroup1", 50000);
        IBranch secondDevGroup = new Branch("B2", "devGroup2", 50000);

        ILeaf a = new Leaf("a", "开发人员", 2000);
        ILeaf b = new Leaf("b", "开发人员", 2000);
        ILeaf c = new Leaf("c", "开发人员", 2000);
        ILeaf d = new Leaf("d", "开发人员", 2000);
        ILeaf e = new Leaf("e", "开发人员", 2000);
        ILeaf f = new Leaf("f", "开发人员", 2000);
        ILeaf g = new Leaf("g", "开发人员", 2000);
        ILeaf h = new Leaf("h", "销售人员", 5000);
        ILeaf i = new Leaf("i", "销售人员", 4000);
        ILeaf j = new Leaf("j", "财务人员", 5000);
        ILeaf k = new Leaf("k", "CEO秘书", 8000);
        ILeaf zhengLaoLiu = new Leaf("郑老六", "研发部副总", 20000);

        ceo.add(developDep);
        ceo.add(saleDep);
        ceo.add(finaceDep);
        ceo.add(k);

        developDep.add(firstDevGroup);
        developDep.add(secondDevGroup);
        developDep.add(zhengLaoLiu);

        firstDevGroup.add(a);
        firstDevGroup.add(b);
        firstDevGroup.add(c);

        secondDevGroup.add(d);
        secondDevGroup.add(e);
        secondDevGroup.add(f);

        saleDep.add(h);
        saleDep.add(i);

        finaceDep.add(j);

        System.out.println(ceo.getInfo());

        getAllSubordinateInfo(ceo.getSubordinateInfo());

    }

    private static void getAllSubordinateInfo(ArrayList subordinateList) {

        int length = subordinateList.size();

        for (int m = 0; m < length; m++) {
            Object s = subordinateList.get(m);
            if (s instanceof Leaf) {
                ILeaf employee = (Leaf) s;
                System.out.println(employee.getInfo());
            } else {
                IBranch branch = (Branch) s;
                System.out.println(branch.getInfo());
                getAllSubordinateInfo(branch.getSubordinateInfo());
            }
        }
    }
}
