package org.andrebrov.testcases.simpletests;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Webinar
 * Date: 21.05.12
 * Time: 20:31
 */
//Very simple test on TestNG
public class SimpleTest {

    @Test
    public void  testSmth(){
        assertTrue("Failed!!!",true);
    }

}
