package com.kingja.qiang.update;

/**
 * Description:TODO
 * Create Time:2018/8/1 0001 下午 4:03
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class VersionInfo {
    /*当前版本编号*/
    private long versionCode;
    /*当前版本代号*/
    private String versionName;
    /*当前版本介绍*/
    private String content;
    /*是否最新版本 1是 0否*/
    private int isLatest;
    /*最新版本下载地址*/
    private String latestDownload;
    /*最新版本更新介绍*/
    private String latestContent;
    /*最新版本编号*/
    private long latestVersionCode;
    /*最新版本代号*/
    private String latestVersionName;
    /*是否强制更新 1是 0否"*/
    private int isForced;

    public long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(long versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsLatest() {
        return isLatest;
    }

    public void setIsLatest(int isLatest) {
        this.isLatest = isLatest;
    }

    public String getLatestDownload() {
        return latestDownload;
    }

    public void setLatestDownload(String latestDownload) {
        this.latestDownload = latestDownload;
    }

    public String getLatestContent() {
        return latestContent;
    }

    public void setLatestContent(String latestContent) {
        this.latestContent = latestContent;
    }

    public long getLatestVersionCode() {
        return latestVersionCode;
    }

    public void setLatestVersionCode(long latestVersionCode) {
        this.latestVersionCode = latestVersionCode;
    }

    public String getLatestVersionName() {
        return latestVersionName;
    }

    public void setLatestVersionName(String latestVersionName) {
        this.latestVersionName = latestVersionName;
    }

    public int getIsForced() {
        return isForced;
    }

    public void setIsForced(int isForced) {
        this.isForced = isForced;
    }
}
