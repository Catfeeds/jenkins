package com.hfocean.uavportal.core.airapply.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
* 
* @author Leslie.Lam
* @create 2017-06-24 10:39
**/
public class LandingInfo {

    @Valid
    @NotNull
    private LandingVo master;//主起降场

    @Valid
    private LandingVo slave;//备起降场

    public LandingVo getMaster() {
        return master;
    }

    public void setMaster(LandingVo master) {
        this.master = master;
    }

    public LandingVo getSlave() {
        return slave;
    }

    public void setSlave(LandingVo slave) {
        this.slave = slave;
    }
}
