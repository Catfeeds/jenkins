package com.hfocean.uavportal.core.airplan.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 
 */
@Entity
@Table(name="tb_plan_airspace_ref")
public class PlanAirspaceRefPO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    /**
     * 空域信息表id
     */
    @Column(name = "airspace_id")
    private String airspaceId;

    /**
     * 飞行计划id
     */
    @Column(name = "plan_id")
    private String planId;

    public PlanAirspaceRefPO() {
    }

    public PlanAirspaceRefPO(String airspaceId, String planId) {
        this.airspaceId = airspaceId;
        this.planId = planId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirspaceId() {
        return airspaceId;
    }

    public void setAirspaceId(String airspaceId) {
        this.airspaceId = airspaceId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
}