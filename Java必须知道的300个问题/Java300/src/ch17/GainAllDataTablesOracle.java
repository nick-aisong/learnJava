package ch17;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

//先在Oracle里创建user
//create user nick identified by "nick";
//grant resource,connect,unlimited tablespace to nick;
public class GainAllDataTablesOracle {
    public static Connection getConn() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver"); // 加载数据库驱动类
        Connection conn = null; // 定义数据库连接
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 声明数据库的URL
        String user = "nick"; // 数据库用户
        String password = "nick"; // 数据库密码
        conn = DriverManager.getConnection(url, user, password); // 建立数据库连接
        return conn;
    }

    public static void main(String[] args) {
        GainAllDataTablesOracle allTables = new GainAllDataTablesOracle();
        allTables.printAllUserTables();
    }

    public void printAllUserTables() {
        Connection conn = null;
        try {
            conn = getConn(); // 获得数据库连接对象
            DatabaseMetaData meta = conn.getMetaData();
            String[] types = {"TABLE"}; // 声明要获得的类型为用户表
            ResultSet rs = meta.getTables(null, null, "%", types); // 获得所有用户表的结果集对象
            //ResultSet rs = meta.getTables(null, "NICK", "%", types);
            // 如果把schemaPattern设置成NICK就是获取对应的user创建的表，而不包含系统表了，或者说不包含其他schema下的表了
            while (rs.next()) {
                System.out.println(rs.getString(3));  // 输出用户表的名称
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close(); // 关闭数据库连接
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
// 看起来Oracle 默认就给user很多表
// 不如直接执行SQL：select table_name from user_tables; 就能查出对应user的所有表

//ResultSet rs = meta.getTables(null, "NICK", "%", types);
// 如果把schemaPattern设置成NICK就是获取对应的user创建的表，而不包含系统表了，或者说不包含其他schema下的表了
/*
DR$NUMBER_SEQUENCE
DR$OBJECT_ATTRIBUTE
DR$POLICY_TAB
WWV_FLOW_DUAL100
WWV_FLOW_FIELD_TEMPLATES
WWV_FLOW_LISTS_OF_VALUES$
WWV_FLOW_LIST_OF_VALUES_DATA
WWV_FLOW_LOV_TEMP
WWV_FLOW_PATCHES
WWV_FLOW_TEMP_TABLE
OGIS_GEOMETRY_COLUMNS
OGIS_SPATIAL_REFERENCE_SYSTEMS
SDO_COORD_AXES
SDO_COORD_AXIS_NAMES
SDO_COORD_OPS
SDO_COORD_OP_METHODS
SDO_COORD_OP_PARAMS
SDO_COORD_OP_PARAM_USE
SDO_COORD_OP_PARAM_VALS
SDO_COORD_OP_PATHS
SDO_COORD_REF_SYS
SDO_COORD_SYS
SDO_CS_SRS
SDO_DATUMS
SDO_DATUMS_OLD_SNAPSHOT
SDO_ELLIPSOIDS
SDO_ELLIPSOIDS_OLD_SNAPSHOT
SDO_PREFERRED_OPS_SYSTEM
SDO_PREFERRED_OPS_USER
SDO_PRIME_MERIDIANS
SDO_PROJECTIONS_OLD_SNAPSHOT
SDO_TOPO_DATA$
SDO_TOPO_RELATION_DATA
SDO_TOPO_TRANSACT_DATA
SDO_TXN_IDX_DELETES
SDO_TXN_IDX_EXP_UPD_RGN
SDO_TXN_IDX_INSERTS
SDO_UNITS_OF_MEASURE
tb_nicktest
AUDIT_ACTIONS
DUAL
IMPDP_STATS
KU$NOEXP_TAB
ODCI_SECOBJ$
ODCI_WARNINGS$
PLAN_TABLE$
PSTUBTBL
STMT_AUDIT_OPTION_MAP
SYSTEM_PRIVILEGE_MAP
TABLE_PRIVILEGE_MAP
WRI$_ADV_ASA_RECO_DATA
DEF$_TEMP$LOB
HELP
OL$
OL$HINTS
OL$NODES
XDB$ACL
XDB$ALL_MODEL
XDB$ANY
XDB$ANYATTR
XDB$ATTRGROUP_DEF
XDB$ATTRGROUP_REF
XDB$ATTRIBUTE
XDB$CHOICE_MODEL
XDB$COMPLEX_TYPE
XDB$ELEMENT
XDB$GROUP_DEF
XDB$GROUP_REF
XDB$SCHEMA
XDB$SEQUENCE_MODEL
XDB$SIMPLE_TYPE
 */