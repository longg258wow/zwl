package com.枚举;

import java.lang.reflect.Method;

public enum PlateEnum {

    homeAdv(100, "homeRecommend", "banner", "首页广告", ""),
    homeButtonList(101, "homeRecommend", "buttonList", "首页按钮", ""),
    homeStudyPlan(102, "homeRecommend", "studyPanel", "首页学习计划", ""),
    homeSelected(103, "homeRecommend", "list", "编辑精选", "showMore"),
    homeNew(104, "homeRecommend", "list", "新课速递", "showMore"),
    homeGood(105, "homeRecommend", "grid", "首页推荐", ""),
    homeFollow(120, "homeRecommend", "grid", "首页关注", ""),
    homeCategory(140, "homeRecommend", "grid", "首页类别", ""),

    boutiqueTimeDiscount(200, "boutiqueRecommend", "grid", "今日特惠", "showMore"),
    boutiqueTop(201, "boutiqueRecommend", "list", "榜单TOP", "showMore"),
    boutiqueCategory(220, "boutiqueRecommend", "list", "精选类别", "showMore"),

    audioAdv(300, "audioHome", "banner", "电台广告", "showMore"),
    audioNew(301, "audioHome", "grid", "最新", "showMore"),

    goodPackageRecommend(400, "goodPackageHome", "grid", "推荐课单", ""),
    goodPackageCategory(420, "goodPackageHome", "grid", "课单类别", "");


    private Integer id;
    private String page;
    private String display;
    private String title;
    private String ext;


    PlateEnum(Integer id, String page, String display, String title, String ext) {
        this.id = id;
        this.page = page;
        this.display = display;
        this.title = title;
        this.ext = ext;

    }

    public static PlateEnum getPlateEnum(Integer id) throws Exception {
        Class plateEnumClass = PlateEnum.class;
        Method getId = plateEnumClass.getMethod("getId");
        PlateEnum[] objects = (PlateEnum[]) plateEnumClass.getEnumConstants();
        for (PlateEnum obj : objects) {
            if (id.equals(getId.invoke(obj))) {
                return obj;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}

