package com.test.paysend.util;

import com.test.paysend.dto.Extra;

import java.util.List;

/**
 * Class for defining utility functions for XMl parsing
 */
public class XmlUtils {

    public static String findAttributeValueByProperty(String attributeName, List<Extra> extras) {
        for (Extra extra : extras) {
            if(extra.getName().equals(attributeName)) {
                return extra.getValue();
            }
        }
        return null;
    }

}
