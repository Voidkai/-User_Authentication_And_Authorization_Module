package corp.sap.internal.exp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PrivilegeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public List<Map<String,Object>> getPrivByUser(Integer id){
//        String sql = "SELECT DISTINCT p.* FROM users AS u " +
//                "LEFT JOIN role_user AS ur ON    u.user_id = ur.user_id " +
//                "LEFT JOIN roles AS r ON    r.role_id = ur.role_id " +
//                "LEFT JOIN privilege_role AS rp ON    r.role_id = rp.role_id " +
//                "LEFT JOIN privileges AS p ON    p.privilege_id = rp.privilege_id " +
//                "WHERE    u.user_id = " + id;
//
//        List list = jdbcTemplate.queryForList(sql);
//
//        return list;
//    }

    public List<Integer> getRoleIdByUser(Integer user_id){
        String sql = "select * FROM role_user WHERE user_id = "+ user_id;

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        List<Integer> roleIdList = new ArrayList<>();
        for(Map<String, Object> map : list){
            roleIdList.add((Integer) map.get("role_id"));
        }

        return  roleIdList;
    }

    public List<Integer> getPrivIdByRoleId(List<Integer> roleIdList){
        String sql = "select DISTINCT privilege_id FROM privilege_role WHERE role_id = 0";
        for(Integer i : roleIdList){
            sql = sql + " or role_id = " + i;
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        List<Integer> privIdList = new ArrayList<>();
        for(Map<String, Object> map : list){
            privIdList.add((Integer) map.get("privilege_id"));
        }

        return privIdList;
    }
    public List<String> getPrivCodeByPrivId(List<Integer> privIdList){
        String sql = "select DISTINCT privilege_code FROM privileges WHERE privilege_id = 0";
        for(Integer i : privIdList){
            sql = sql + " or privilege_id = " + i;
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        List<String> privCodeList = new ArrayList<>();
        for(Map<String, Object> map : list){
            privCodeList.add((String) map.get("privilege_code"));
        }

        return privCodeList;
    }

}
