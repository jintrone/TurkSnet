// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.mit.cci.turksnet.domain;

import edu.mit.cci.turksnet.domain.Role;
import edu.mit.cci.turksnet.domain.User;

privileged aspect UserRole_Roo_JavaBean {
    
    public User UserRole.getUserEntry() {
        return this.userEntry;
    }
    
    public void UserRole.setUserEntry(User userEntry) {
        this.userEntry = userEntry;
    }
    
    public Role UserRole.getRoleEntry() {
        return this.roleEntry;
    }
    
    public void UserRole.setRoleEntry(Role roleEntry) {
        this.roleEntry = roleEntry;
    }
    
}