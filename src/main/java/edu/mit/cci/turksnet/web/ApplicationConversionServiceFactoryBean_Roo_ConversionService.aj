// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.mit.cci.turksnet.web;

import edu.mit.cci.turksnet.Experiment;
import edu.mit.cci.turksnet.Node;
import edu.mit.cci.turksnet.Results;
import edu.mit.cci.turksnet.SessionLog;
import edu.mit.cci.turksnet.Session_;
import edu.mit.cci.turksnet.Worker;
import edu.mit.cci.turksnet.domain.Role;
import edu.mit.cci.turksnet.domain.User;
import edu.mit.cci.turksnet.domain.UserRole;
import java.lang.String;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    Converter<Role, String> ApplicationConversionServiceFactoryBean.getRoleConverter() {
        return new Converter<Role, String>() {
            public String convert(Role source) {
                return new StringBuilder().append(source.getRoleName()).append(" ").append(source.getRoleDescription()).toString();
            }
        };
    }
    
    Converter<Node, String> ApplicationConversionServiceFactoryBean.getNodeConverter() {
        return new Converter<Node, String>() {
            public String convert(Node source) {
                return new StringBuilder().append(source.getPrivateData_()).append(" ").append(source.getPublicData_()).append(" ").append(source.getStatus()).toString();
            }
        };
    }
    
    Converter<SessionLog, String> ApplicationConversionServiceFactoryBean.getSessionLogConverter() {
        return new Converter<SessionLog, String>() {
            public String convert(SessionLog source) {
                return new StringBuilder().append(source.getNodePrivateData()).append(" ").append(source.getNodePublicData()).append(" ").append(source.getType()).toString();
            }
        };
    }
    
    Converter<Experiment, String> ApplicationConversionServiceFactoryBean.getExperimentConverter() {
        return new Converter<Experiment, String>() {
            public String convert(Experiment source) {
                return new StringBuilder().append(" ").append(source.getProperties()).append(" ").append(source.getNetwork()).toString();
            }
        };
    }
    
    Converter<Results, String> ApplicationConversionServiceFactoryBean.getResultsConverter() {
        return new Converter<Results, String>() {
            public String convert(Results source) {
                return new StringBuilder().append(source.getNode()).append(" ").append(source.getReceived()).append(" ").append(source.getTurkerId()).toString();
            }
        };
    }
    
    Converter<User, String> ApplicationConversionServiceFactoryBean.getUserConverter() {
        return new Converter<User, String>() {
            public String convert(User source) {
                return new StringBuilder().append(source.getFirstName()).append(" ").append(source.getLastName()).append(" ").append(source.getEmailAddress()).toString();
            }
        };
    }
    
    Converter<UserRole, String> ApplicationConversionServiceFactoryBean.getUserRoleConverter() {
        return new Converter<UserRole, String>() {
            public String convert(UserRole source) {
                return new StringBuilder().append(source.toString()).toString();
            }
        };
    }
    
    Converter<Session_, String> ApplicationConversionServiceFactoryBean.getSession_Converter() {
        return new Converter<Session_, String>() {
            public String convert(Session_ source) {
                return new StringBuilder().append(" ").append(source.getLog()).append(" ").append(source.getNetwork()).toString();
            }
        };
    }
    
    Converter<Worker, String> ApplicationConversionServiceFactoryBean.getWorkerConverter() {
        return new Converter<Worker, String>() {
            public String convert(Worker source) {
                return new StringBuilder().append(source.getUsername()).append(" ").append(source.getPassword()).append(" ").toString();
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getRoleConverter());
        registry.addConverter(getNodeConverter());
        registry.addConverter(getSessionLogConverter());
        registry.addConverter(getExperimentConverter());
        registry.addConverter(getResultsConverter());
        registry.addConverter(getUserConverter());
        registry.addConverter(getUserRoleConverter());
        registry.addConverter(getSession_Converter());
        registry.addConverter(getWorkerConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
