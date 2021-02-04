package cn.andanyoung.admin4j.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {

  // 获取IP地址
  public static String getIpAddr(HttpServletRequest request) throws UnknownHostException {
    String ipAddress = request.getHeader("x-forwarded-for");
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("Proxy-Client-IP");
    }
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getRemoteAddr();
      if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
        // 根据网卡取本机配置的IP
        InetAddress inet = null;
        inet = InetAddress.getLocalHost();
        ipAddress = inet.getHostAddress();
      }
    }
    // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
    if (ipAddress != null && ipAddress.length() > 15) {
      // "***.***.***.***".length() = 15
      if (ipAddress.indexOf(",") > 0) {
        ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
      }
    }
    return ipAddress;
  }

  // 获取IP地址
  public static Long getIpAddrNumber(HttpServletRequest request) {

    try {
      return ip2Number(getIpAddr(request));
    } catch (UnknownHostException e) {
      System.out.println(e.getMessage());
      return 0L;
    }
  }

  // IP 转 Long
  public static Long ip2Number(String ip) {
    Long num = 0L;
    if (ip == null) {
      return num;
    }

    try {
      ip = ip.replaceAll("[^0-9\\.]", ""); // 去除字符串前的空字符
      String[] ips = ip.split("\\.");
      if (ips.length == 4) {
        num =
                Long.parseLong(ips[0], 10) * 256L * 256L * 256L
                        + Long.parseLong(ips[1], 10) * 256L * 256L
                        + Long.parseLong(ips[2], 10) * 256L
                        + Long.parseLong(ips[3], 10);
        num = num >>> 0;
      }
    } catch (NullPointerException ex) {
      System.out.println(ip);
    }

    return num;
  }

  // 将十进制整数形式转换成127.0.0.1形式的ip地址
  public static String numberToIP(long longIp) {
    StringBuffer sb = new StringBuffer("");
    // 直接右移24位
    sb.append(String.valueOf((longIp >>> 24)));
    sb.append(".");
    // 将高8位置0，然后右移16位
    sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
    sb.append(".");
    // 将高16位置0，然后右移8位
    sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
    sb.append(".");
    // 将高24位置0
    sb.append(String.valueOf((longIp & 0x000000FF)));
    return sb.toString();
  }
}
