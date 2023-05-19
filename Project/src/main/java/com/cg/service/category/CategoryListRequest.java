package com.cg.service.category;

import lombok.Data;

@Data
public class CategoryListRequest {

    private String search;

    private boolean deleted;
}
