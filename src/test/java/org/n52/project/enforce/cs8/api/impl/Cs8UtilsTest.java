package org.n52.project.enforce.cs8.api.impl;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.n52.project.enforce.cs8.utils.Cs8Utils;
import org.springframework.beans.factory.annotation.Autowired;

public class Cs8UtilsTest extends DBTest{
    
    @Autowired
    Cs8Utils cs8Utils;
        
    @Test
    void testReadExcelFile() {
        try {
            cs8Utils.readExcelFile(getClass().getResourceAsStream("demo_data.xlsx"));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
    
}
