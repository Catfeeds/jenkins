package com.hfocean.uavportal.core.airapply.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author
 */
@Entity
@Table(name="tb_air_apply_landing_ref")
public class AirApplyLandingRefPO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    /**
     * 空域申请表id
     */
    @Column(name = "air_apply_id")
    private String airApplyId;

    /**
     * 起降场id
     */
    @Column(name = "landing_id")
    private String landingId;

    public AirApplyLandingRefPO() {
    }

    public AirApplyLandingRefPO(String airApplyId, String landingId) {
        this.airApplyId = airApplyId;
        this.landingId = landingId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirApplyId() {
        return airApplyId;
    }

    public void setAirApplyId(String airApplyId) {
        this.airApplyId = airApplyId;
    }

    public String getLandingId() {
        return landingId;
    }

    public void setLandingId(String landingId) {
        this.landingId = landingId;
    }
}