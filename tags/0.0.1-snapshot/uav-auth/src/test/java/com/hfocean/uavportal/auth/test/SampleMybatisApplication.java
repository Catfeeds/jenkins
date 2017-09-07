package com.hfocean.uavportal.auth.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.hfocean.uavportal.AuthAutoConfiguration;
import com.hfocean.uavportal.auth.system.user.dao.ISysUserDao;
import com.hfocean.uavportal.auth.system.user.param.UserList;


//@SpringBootApplication
//@Import(AuthAutoConfiguration.class)
public class SampleMybatisApplication implements CommandLineRunner {

    private final ISysUserDao iSysUserDao;

    public SampleMybatisApplication(ISysUserDao iSysUserDao) {
        this.iSysUserDao = iSysUserDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleMybatisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	UserList u = new UserList();
    	u.setStatus(1);
        System.out.println(this.iSysUserDao.findUser(u));
    }

}
