package ch17;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class GainAllDataTablesPostgreSQL {
    public static Connection getConn() throws Exception {
        Class.forName("org.postgresql.Driver"); // 加载数据库驱动类
        Connection conn = null; // 定义数据库连接
        String url = "jdbc:postgresql://localhost:5432/idmdb"; // 声明数据库的URL
        String user = "postgres"; // 数据库用户
        String password = "postgres"; // 数据库密码
        conn = DriverManager.getConnection(url, user, password); // 建立数据库连接
        return conn;
    }

    public static void main(String[] args) {
        GainAllDataTablesPostgreSQL allTables = new GainAllDataTablesPostgreSQL();
        allTables.printAllUserTables();
    }

    public void printAllUserTables() {
        Connection conn = null;
        try {
            conn = getConn(); // 获得数据库连接对象
            DatabaseMetaData meta = conn.getMetaData();
            String[] types = {"TABLE"}; // 声明要获得的类型为用户表
            ResultSet rs = meta.getTables(null, null, "%", types); // 获得所有用户表的结果集对象
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

/*
abstract_group
abstract_group_metadata
abstract_group_representation
abstract_user
abstract_user_metadata
abstract_user_profile
abstract_user_representation
attributes_mapping
audit_filter
audit_log
auth_group
auth_group_config
authentication_type
authentication_user_key_name
authtype_samlconfig
base_lock
baseauthconfiguration
calculated_group_criterion
calculated_group_rep
certificate_configuration
database_group_rep
database_user
database_user_metadata
database_user_token_store
db_configuration
detached_idm_configuration
groups
idp_token_store
jaas_configuration
jaas_login_module_file
keystoneconfiguration
ldap_configuration
ldap_group_rep
ldap_ou_group_rep
ldap_roster_history
metadata
oauth_configuration
organizations
password_history
password_policy
permission
permission_role
roles
roles_groups
saml_configuration
saml_message_store
schema_version
secureid_configuration
seeded_specific_version
system_resource_config
token_store
trust
user_group_database_rep
user_group_rep
ws_trust_configuration
 */