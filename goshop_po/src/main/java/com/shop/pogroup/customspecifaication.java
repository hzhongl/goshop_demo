package com.shop.pogroup;

import com.shop.po.specification;
import com.shop.po.specificationOption;

import java.io.Serializable;
import java.util.List;

/*组合po类*/
public class customspecifaication implements Serializable {
    private specification specification;

    private List<specificationOption> specificationOptionList;

    public com.shop.po.specification getSpecification() {
        return specification;
    }

    public void setSpecification(com.shop.po.specification specification) {
        this.specification = specification;
    }

    public List<specificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<specificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }
}
