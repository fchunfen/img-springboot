package com.fenghainan.springboot.entry;

public class Tag {
    private String tagsId;

    private String tagsName;

    public String getTagsId() {
        return tagsId;
    }

    public void setTagsId(String tagsId) {
        this.tagsId = tagsId == null ? null : tagsId.trim();
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName == null ? null : tagsName.trim();
    }
}