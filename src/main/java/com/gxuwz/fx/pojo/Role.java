package com.gxuwz.fx.pojo;

import java.util.Set;

public class Role {
    private int id;
    private String rolename;
    /**
     * 角色对应权限集合
     */
    private Set<Permission> permissions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
