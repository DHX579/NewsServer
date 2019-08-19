package com.example.demo.model;

import java.util.Date;

public class NewsContent {
    private Integer newsId;

    private Integer typeId;

    private String newsTheme;

    private String newsContentLabel;

    private String newsPic;

    private String newsReleaseName;

    private String newsReleasePic;

    private String newsPress;

    private Date newsTime;

    private Date newsVideoDuration;

    private String remark;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getNewsTheme() {
        return newsTheme;
    }

    public void setNewsTheme(String newsTheme) {
        this.newsTheme = newsTheme == null ? null : newsTheme.trim();
    }

    public String getNewsContentLabel() {
        return newsContentLabel;
    }

    public void setNewsContentLabel(String newsContentLabel) {
        this.newsContentLabel = newsContentLabel == null ? null : newsContentLabel.trim();
    }

    public String getNewsPic() {
        return newsPic;
    }

    public void setNewsPic(String newsPic) {
        this.newsPic = newsPic == null ? null : newsPic.trim();
    }

    public String getNewsReleaseName() {
        return newsReleaseName;
    }

    public void setNewsReleaseName(String newsReleaseName) {
        this.newsReleaseName = newsReleaseName == null ? null : newsReleaseName.trim();
    }

    public String getNewsReleasePic() {
        return newsReleasePic;
    }

    public void setNewsReleasePic(String newsReleasePic) {
        this.newsReleasePic = newsReleasePic == null ? null : newsReleasePic.trim();
    }

    public String getNewsPress() {
        return newsPress;
    }

    public void setNewsPress(String newsPress) {
        this.newsPress = newsPress == null ? null : newsPress.trim();
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

    public Date getNewsVideoDuration() {
        return newsVideoDuration;
    }

    public void setNewsVideoDuration(Date newsVideoDuration) {
        this.newsVideoDuration = newsVideoDuration;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}