package cn.part02.ch28.flyweight2;

//代码清单28-6 场景类
public class Client {
    public static void main(String[] args) {
        //初始化对象池
        for (int i = 0; i < 4; i++) {
            String subject = "科目" + i;
            //初始化地址
            for (int j = 0; j < 30; j++) {
                String key = subject + "考试地点" + j;
                SignInfoFactory.getSignInfo(key);
            }
        }
        SignInfo signInfo = SignInfoFactory.getSignInfo("科目1考试地点1");
    }
}

//科目0考试地点0----建立对象，并放置到池中
//科目0考试地点1----建立对象，并放置到池中
//科目0考试地点2----建立对象，并放置到池中
//科目0考试地点3----建立对象，并放置到池中
//科目0考试地点4----建立对象，并放置到池中
//科目0考试地点5----建立对象，并放置到池中
//科目0考试地点6----建立对象，并放置到池中
//科目0考试地点7----建立对象，并放置到池中
//科目0考试地点8----建立对象，并放置到池中
//科目0考试地点9----建立对象，并放置到池中
//科目0考试地点10----建立对象，并放置到池中
//科目0考试地点11----建立对象，并放置到池中
//科目0考试地点12----建立对象，并放置到池中
//科目0考试地点13----建立对象，并放置到池中
//科目0考试地点14----建立对象，并放置到池中
//科目0考试地点15----建立对象，并放置到池中
//科目0考试地点16----建立对象，并放置到池中
//科目0考试地点17----建立对象，并放置到池中
//科目0考试地点18----建立对象，并放置到池中
//科目0考试地点19----建立对象，并放置到池中
//科目0考试地点20----建立对象，并放置到池中
//科目0考试地点21----建立对象，并放置到池中
//科目0考试地点22----建立对象，并放置到池中
//科目0考试地点23----建立对象，并放置到池中
//科目0考试地点24----建立对象，并放置到池中
//科目0考试地点25----建立对象，并放置到池中
//科目0考试地点26----建立对象，并放置到池中
//科目0考试地点27----建立对象，并放置到池中
//科目0考试地点28----建立对象，并放置到池中
//科目0考试地点29----建立对象，并放置到池中
//科目1考试地点0----建立对象，并放置到池中
//科目1考试地点1----建立对象，并放置到池中
//科目1考试地点2----建立对象，并放置到池中
//科目1考试地点3----建立对象，并放置到池中
//科目1考试地点4----建立对象，并放置到池中
//科目1考试地点5----建立对象，并放置到池中
//科目1考试地点6----建立对象，并放置到池中
//科目1考试地点7----建立对象，并放置到池中
//科目1考试地点8----建立对象，并放置到池中
//科目1考试地点9----建立对象，并放置到池中
//科目1考试地点10----建立对象，并放置到池中
//科目1考试地点11----建立对象，并放置到池中
//科目1考试地点12----建立对象，并放置到池中
//科目1考试地点13----建立对象，并放置到池中
//科目1考试地点14----建立对象，并放置到池中
//科目1考试地点15----建立对象，并放置到池中
//科目1考试地点16----建立对象，并放置到池中
//科目1考试地点17----建立对象，并放置到池中
//科目1考试地点18----建立对象，并放置到池中
//科目1考试地点19----建立对象，并放置到池中
//科目1考试地点20----建立对象，并放置到池中
//科目1考试地点21----建立对象，并放置到池中
//科目1考试地点22----建立对象，并放置到池中
//科目1考试地点23----建立对象，并放置到池中
//科目1考试地点24----建立对象，并放置到池中
//科目1考试地点25----建立对象，并放置到池中
//科目1考试地点26----建立对象，并放置到池中
//科目1考试地点27----建立对象，并放置到池中
//科目1考试地点28----建立对象，并放置到池中
//科目1考试地点29----建立对象，并放置到池中
//科目2考试地点0----建立对象，并放置到池中
//科目2考试地点1----建立对象，并放置到池中
//科目2考试地点2----建立对象，并放置到池中
//科目2考试地点3----建立对象，并放置到池中
//科目2考试地点4----建立对象，并放置到池中
//科目2考试地点5----建立对象，并放置到池中
//科目2考试地点6----建立对象，并放置到池中
//科目2考试地点7----建立对象，并放置到池中
//科目2考试地点8----建立对象，并放置到池中
//科目2考试地点9----建立对象，并放置到池中
//科目2考试地点10----建立对象，并放置到池中
//科目2考试地点11----建立对象，并放置到池中
//科目2考试地点12----建立对象，并放置到池中
//科目2考试地点13----建立对象，并放置到池中
//科目2考试地点14----建立对象，并放置到池中
//科目2考试地点15----建立对象，并放置到池中
//科目2考试地点16----建立对象，并放置到池中
//科目2考试地点17----建立对象，并放置到池中
//科目2考试地点18----建立对象，并放置到池中
//科目2考试地点19----建立对象，并放置到池中
//科目2考试地点20----建立对象，并放置到池中
//科目2考试地点21----建立对象，并放置到池中
//科目2考试地点22----建立对象，并放置到池中
//科目2考试地点23----建立对象，并放置到池中
//科目2考试地点24----建立对象，并放置到池中
//科目2考试地点25----建立对象，并放置到池中
//科目2考试地点26----建立对象，并放置到池中
//科目2考试地点27----建立对象，并放置到池中
//科目2考试地点28----建立对象，并放置到池中
//科目2考试地点29----建立对象，并放置到池中
//科目3考试地点0----建立对象，并放置到池中
//科目3考试地点1----建立对象，并放置到池中
//科目3考试地点2----建立对象，并放置到池中
//科目3考试地点3----建立对象，并放置到池中
//科目3考试地点4----建立对象，并放置到池中
//科目3考试地点5----建立对象，并放置到池中
//科目3考试地点6----建立对象，并放置到池中
//科目3考试地点7----建立对象，并放置到池中
//科目3考试地点8----建立对象，并放置到池中
//科目3考试地点9----建立对象，并放置到池中
//科目3考试地点10----建立对象，并放置到池中
//科目3考试地点11----建立对象，并放置到池中
//科目3考试地点12----建立对象，并放置到池中
//科目3考试地点13----建立对象，并放置到池中
//科目3考试地点14----建立对象，并放置到池中
//科目3考试地点15----建立对象，并放置到池中
//科目3考试地点16----建立对象，并放置到池中
//科目3考试地点17----建立对象，并放置到池中
//科目3考试地点18----建立对象，并放置到池中
//科目3考试地点19----建立对象，并放置到池中
//科目3考试地点20----建立对象，并放置到池中
//科目3考试地点21----建立对象，并放置到池中
//科目3考试地点22----建立对象，并放置到池中
//科目3考试地点23----建立对象，并放置到池中
//科目3考试地点24----建立对象，并放置到池中
//科目3考试地点25----建立对象，并放置到池中
//科目3考试地点26----建立对象，并放置到池中
//科目3考试地点27----建立对象，并放置到池中
//科目3考试地点28----建立对象，并放置到池中
//科目3考试地点29----建立对象，并放置到池中
//科目1考试地点1---直接从池中取得
