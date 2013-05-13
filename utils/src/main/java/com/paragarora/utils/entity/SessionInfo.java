package com.paragarora.utils.entity;
import java.io.Serializable;

public class SessionInfo implements Serializable {
  private static final long serialVersionUID = -7867396090798594654L;
  private String longSessionId;
  private String authSessionId;
  private String guestSessionId;
  private String impersonatorSessionId;
  private long uid;
  private DeviceType deviceType;
  private String deviceId;
  private Boolean ageAccepted;
  private long createdAt;

  public String getLongSessionId() {
    return longSessionId;
  }

  public void setLongSessionId(String longSessionId) {
    this.longSessionId = longSessionId;
  }

  public String getAuthSessionId() {
    return authSessionId;
  }

  public void setAuthSessionId(String authSessionId) {
    this.authSessionId = authSessionId;
  }

  public String getGuestSessionId() {
    return guestSessionId;
  }

  public void setGuestSessionId(String guestSessionId) {
    this.guestSessionId = guestSessionId;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public DeviceType getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(DeviceType deviceType) {
    this.deviceType = deviceType;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  public Boolean isAgeAccepted() {
    return ageAccepted;
  }

  public void setAgeAccepted(Boolean ageAccepted) {
    this.ageAccepted = ageAccepted;
  }

  public String getImpersonatorSessionId() {
    return impersonatorSessionId;
  }

  public void setImpersonatorSessionId(String impersonatorSessionId) {
    this.impersonatorSessionId = impersonatorSessionId;
  }
}

