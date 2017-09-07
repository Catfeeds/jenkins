package com.hfocean.uavportal.weixin.notify.repository;


import com.hfocean.uavportal.weixin.notify.po.MsgTemp;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Leslie.Lam
 * @create 2017-06-29 15:46
 **/
public interface MsgTempRepository extends JpaRepository<MsgTemp,Integer> {
}
