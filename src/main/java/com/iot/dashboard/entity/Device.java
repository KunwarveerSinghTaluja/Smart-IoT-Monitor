package com.iot.dashboard.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String name;
    private String type;
    private String status;
    private String location;
    private String ipAddress;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;

    private Double lastValue;
    private String lastUnit;

    public Device() {
        this.lastSeen = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public LocalDateTime getLastSeen() { return lastSeen; }
    public void setLastSeen(LocalDateTime lastSeen) { this.lastSeen = lastSeen; }

    public Double getLastValue() { return lastValue; }
    public void setLastValue(Double lastValue) { this.lastValue = lastValue; }

    public String getLastUnit() { return lastUnit; }
    public void setLastUnit(String lastUnit) { this.lastUnit = lastUnit; }
}