// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.mit.cci.turksnet.domain;

import edu.mit.cci.turksnet.domain.Role;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect RoleDataOnDemand_Roo_DataOnDemand {
    
    declare @type: RoleDataOnDemand: @Component;
    
    private Random RoleDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Role> RoleDataOnDemand.data;
    
    public Role RoleDataOnDemand.getNewTransientRole(int index) {
        edu.mit.cci.turksnet.domain.Role obj = new edu.mit.cci.turksnet.domain.Role();
        obj.setRoleName("roleName_" + index);
        java.lang.String roleDescription = "roleDescription_" + index;
        if (roleDescription.length() > 200) {
            roleDescription  = roleDescription.substring(0, 200);
        }
        obj.setRoleDescription(roleDescription);
        return obj;
    }
    
    public Role RoleDataOnDemand.getSpecificRole(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Role obj = data.get(index);
        return Role.findRole(obj.getId());
    }
    
    public Role RoleDataOnDemand.getRandomRole() {
        init();
        Role obj = data.get(rnd.nextInt(data.size()));
        return Role.findRole(obj.getId());
    }
    
    public boolean RoleDataOnDemand.modifyRole(Role obj) {
        return false;
    }
    
    public void RoleDataOnDemand.init() {
        data = edu.mit.cci.turksnet.domain.Role.findRoleEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Role' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<edu.mit.cci.turksnet.domain.Role>();
        for (int i = 0; i < 10; i++) {
            edu.mit.cci.turksnet.domain.Role obj = getNewTransientRole(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
