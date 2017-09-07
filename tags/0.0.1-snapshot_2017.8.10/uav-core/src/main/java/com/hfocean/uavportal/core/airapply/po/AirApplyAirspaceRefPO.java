package com.hfocean.uavportal.core.airapply.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author
 */
@Entity
@Table(name="tb_air_apply_airspace_ref")
public class AirApplyAirspaceRefPO implements Serializable {
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
     * 空域信息表id
     */
    @Column(name = "airspace_id")
    private String airspaceId;

    public AirApplyAirspaceRefPO() {
    }

    public AirApplyAirspaceRefPO(String airApplyId, String airspaceId) {
        this.airApplyId = airApplyId;
        this.airspaceId = airspaceId;
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

    public String getAirspaceId() {
        return airspaceId;
    }

    public void setAirspaceId(String airspaceId) {
        this.airspaceId = airspaceId;
    }
}