package com.taotaox.common.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.taotaox.common.util.excel.ExcelTool;
import com.taotaox.common.util.web.ObjectUtils;

/**
 * Created by yachao on 18/1/20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelEntity implements Serializable {
    private static final long serialVersionUID = -5973214519842917966L;

    /**
     * key = sheetName, value = rows list <key=fieldName,value=cell value>
     */
    private LinkedHashMap<String, List<LinkedHashMap<String, Object>>> sheetsWithData = new LinkedHashMap<>();

    private List<String> sheetNames = new ArrayList<>();

    public List<String> getSheetNames() {
        if (ObjectUtils.isEmpty(sheetNames)) {
            List<String> newSheetNames = sheetsWithData.entrySet().parallelStream().map(Map.Entry::getKey).collect(Collectors.toList());
            sheetNames.addAll(newSheetNames);
        }
        return sheetNames;
    }

    public boolean isExceedXlsRowsLimited() {
        boolean exceedXlsRowsLimited = false;
        for (Map.Entry<String, List<LinkedHashMap<String, Object>>> entry : sheetsWithData.entrySet()) {
            if (entry.getValue().size() > ExcelTool.EXCEL_ROWS_LIMIT) {
                exceedXlsRowsLimited = true;
                break;
            }
        }
        return exceedXlsRowsLimited;
    }
}
