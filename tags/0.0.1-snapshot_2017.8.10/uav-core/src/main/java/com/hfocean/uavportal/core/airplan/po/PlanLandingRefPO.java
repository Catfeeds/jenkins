package com.hfocean.uavportal.core.airplan.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 
 */
@Entity
@Table(name="tb_plan_landing_ref")
public class PlanLandingRefPO  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    /**
     * 起降场id
     */
    @Column(name = "landing_id")
    private String landingId;

    /**
     * 飞行计划id
     */
    @Column(name = "plan_id")
    private String planId;

    public PlanLandingRefPO() {
    }

    public PlanLandingRefPO(String landingId, String planId) {
        this.landingId = landingId;
        this.planId = planId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLandingId() {
        return landingId;
    }

    public void setLandingId(String landingId) {
        this.landingId = landingId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
}