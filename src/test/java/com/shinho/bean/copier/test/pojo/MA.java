package com.shinho.bean.copier.test.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MA {
    private Long id;
    private String name;
    private String privateName;
    private int intP;        
    private long longP;        
    private boolean booleanP;
    private char charP;
    private byte byteP;
    private short shortP;
    private float floatP;
    private double doubleP;
    private String stringP;

}

