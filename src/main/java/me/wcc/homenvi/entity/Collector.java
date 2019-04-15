package me.wcc.homenvi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * 终端采集节点
 *
 * @author ttchengwang@foxmail.com 2019-04-12 19:17:00
 */
@ApiModel("终端采集节点")
@Table(name = "collector")
public class Collector {

    public static final String FIELD_ID = "id";
    public static final String FIELD_IDENTIFIER = "identifier";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_IP_ADDRESS = "ipAddress";
    public static final String FIELD_MAC_ADDRESS = "macAddress";
    public static final String FIELD_DNS_ADDRESS = "dnsAddress";
    public static final String FIELD_GATEWAY_ADDRESS = "gatewayAddress";
    public static final String FIELD_SUBNET_MASK_ADDRESS = "subnetMaskAddress";
    public static final String FIELD_LAST_ONLINE_TIME = "lastOnlineTime";

//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String identifier;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private String description;
    private String ipAddress;
    private String macAddress;
    private String dnsAddress;
    private String gatewayAddress;
    private String subnetMaskAddress;
    private Date lastOnlineTime;

//
// 非数据库字段
// ------------------------------------------------------------------------------

//
// getter/setter
// ------------------------------------------------------------------------------


    /**
     * @return 主键ID
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 唯一标识码
     */
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return 名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 密匙
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return ip地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return mac地址
     */
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * @return dns地址
     */
    public String getDnsAddress() {
        return dnsAddress;
    }

    public void setDnsAddress(String dnsAddress) {
        this.dnsAddress = dnsAddress;
    }

    /**
     * @return 网关地址
     */
    public String getGatewayAddress() {
        return gatewayAddress;
    }

    public void setGatewayAddress(String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
    }

    /**
     * @return 子网掩码
     */
    public String getSubnetMaskAddress() {
        return subnetMaskAddress;
    }

    public void setSubnetMaskAddress(String subnetMaskAddress) {
        this.subnetMaskAddress = subnetMaskAddress;
    }

    /**
     * @return 最后上线时间
     */
    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }
}
