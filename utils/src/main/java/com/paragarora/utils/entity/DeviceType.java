package com.paragarora.utils.entity;
/**
 * The Enum DeviceType. It contains the device name and device group. Device
 * group is mainly to group configuration based on device group.
 */
public enum DeviceType {

  WEB("web", "webgroup"), IPAD_WEB("ipadweb", "ipadgroup"), IPAD("ipad", "ipadgroup"),
  IOS("ios", "iosgroup"), IOS_WEB("iosweb", "iosgroup"), WIN_8("win8", "wingroup"),
  MOBILE_WEB("mobile", "mobilegroup"), ANDROID4("android4", "mobilegroup"), ROKU("roku", "rokugroup");

  /** The id. */
  private String id;

  /** The group. */
  private String group;

  /**
   * Instantiates a new device type.
   *
   * @param id
   *          the id
   * @param group
   *          the group
   */
  private DeviceType(String id, String group) {
    this.id = id;
    this.group = group;
  }

  public String getId() {
    return id;
  }

  public String getGroup() {
    return group;
  }

  /**
   * From value.
   *
   * @param deviceType
   *          the device type
   * @return the device type
   */
  public static DeviceType fromValue(String deviceType) {
    if (deviceType == null) {
      return WEB;
    }
    for (DeviceType temp : values()) {
      if (deviceType.equalsIgnoreCase(temp.getId())) {
        return temp;
      }
    }
    return WEB;
  }
}

