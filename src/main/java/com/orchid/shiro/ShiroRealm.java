package com.orchid.shiro;

import com.google.common.base.Strings;
import com.orchid.entity.Menu;
import com.orchid.entity.Role;
import com.orchid.entity.UserInfo;
import com.orchid.mapper.MenuMapper;
import com.orchid.mapper.RoleMapper;
import com.orchid.mapper.UserInfoMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljg on 2018/4/1.
 */
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;

    /**
     * 权限校验
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登陆用户的信息
        UserInfo principal = (UserInfo)principals.getPrimaryPrincipal();
        List<String> permissionList = new ArrayList<String>();

        //用登陆的用户信息获取用户的角色或权限
        List<Menu> menus = menuMapper.selectByUserId(principal.getId());
        List<Role> roles = roleMapper.getListByUserId(principal.getId());

        //菜单权限
        for(Menu menu:menus){
            String user_permission = menu.getPermission();
            if(!Strings.isNullOrEmpty(user_permission)){
                if(user_permission.indexOf(";")>0){
                    String[] permissions = user_permission.split(";");
                    for(String per:permissions){
                        if(!Strings.isNullOrEmpty(per)){
                            permissionList.add(per);
                        }
                    }
                }else {
                    permissionList.add(user_permission);
                }
            }
        }

        //下载权限
        for(Role role:roles){
            if(role.getIsDownPermission()!=null&&role.getIsDownPermission()==1){
                permissionList.add("grantDownReports");
                break;
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);

        return info;
    }

    /**
     * 验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        //2.得到username
        String username = uToken.getUsername();
        //3.根据username查询数据库得到用户信息
        List<UserInfo> userInfos = userInfoMapper.selectByAccount(username);

        UserInfo userInfo = null;
        if(userInfos.size()!=0){
            userInfo = userInfos.get(0);
        }
        //用户不存在
        if(userInfo==null){
            throw new UnknownAccountException("用户不存在!");
        }

        if(userInfo.getStatus().equals(0)){
            throw new LockedAccountException("登陆用户被禁用!");
        }

        if(userInfo.getIsLock().equals(1)){
            throw new LockedAccountException("登陆用户被锁定!");
        }
        //根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        //以下信息是从数据库中获取的.
        //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        Object principal = userInfo;
        //2). credentials: 密码.
        Object credentials = userInfo.getPassword();
        //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();
        //4). 盐值.
        String salt = "orchid"+new StringBuilder(username).reverse().toString();
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
        return info;
    }
}
