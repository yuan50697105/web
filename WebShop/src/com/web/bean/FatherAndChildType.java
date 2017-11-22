package com.web.bean;

import java.util.List;

public class FatherAndChildType {

    private GoodsType fType;
    private List<GoodsType> cTypes;

    @Override
    public String toString() {
        return "FatherAndChildType [fType=" + fType + ", cTypes=" + cTypes + "]";
    }

    public GoodsType getfType() {
        return fType;
    }

    public void setfType(GoodsType fType) {
        this.fType = fType;
    }

    public List<GoodsType> getcTypes() {
        return cTypes;
    }

    public void setcTypes(List<GoodsType> cTypes) {
        this.cTypes = cTypes;
    }

}
