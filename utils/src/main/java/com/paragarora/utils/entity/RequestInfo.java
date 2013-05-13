package com.paragarora.utils.entity;

import java.io.Serializable;
import java.util.Locale;

public class RequestInfo implements Serializable {
  private static final long serialVersionUID = -4474651530683053764L;
  private String requestUuid;
  private String type;
  private String path;
  private boolean secure;
  private String domain;
  private int port;
  private Locale locale;
  private String deviceId;
  private String deviceType;
  private String region;
  private String ip;
  private long uid;
  private String method;
  private boolean isOfficeAccess;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public boolean isSecure() {
    return secure;
  }

  public void setSecure(boolean secure) {
    this.secure = secure;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  public boolean isLoginRequired() {
    return path != null && (path.contains("/a/") || path.contains("/v/"));
  }

  public boolean isVerifyRequired() {
    return path != null && path.contains("/v/");
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getRequestUuid() {
    return requestUuid;
  }

  public void setRequestUuid(String requestUuid) {
    this.requestUuid = requestUuid;
  }

  public boolean isOfficeAccess() {
    return isOfficeAccess;
  }

  public void setOfficeAccess(boolean isOfficeAccess) {
    this.isOfficeAccess = isOfficeAccess;
  }
}

