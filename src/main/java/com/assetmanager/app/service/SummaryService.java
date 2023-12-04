package com.assetmanager.app.service;

import java.util.List;
import java.util.Map;

public interface SummaryService<T> {

    Map<String, Long> countByCategory(List<T> itemList);

    Integer totalCount(List<T> itemList);
}
