package dao.implementation;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class TestMain extends Assert{
    @DataProvider
    public Object[][] testData(){
        return new Object[][]{
                {null, null, null, -1, -1, -1, null, 0, 3},
                {"name", null, null, -1, -1, -1, null, 1, 3},
                {"1", null, null, -1, -1, -1, null, 2, 1},
                {"2", "sn", null, -1, -1, -1, null, 3, 1},
                {null, "sn", null, -1, -1, -1, null, 4, 3},
                {"lol", "sn", null, -1, -1, -1, null, 5, 0},
                {null, null, null, -1, -1, -1, "5678@ya.hu", 6, 3},
                {"name1", "sn1", "p1", -1, -1, -1, "5678@ya.hu", 7, 1},
                {null, null, null, -1, -1, 0, null, 8, 1},
                {"2", null, null, -1, -1, 0, null, 9, 0},
                {null, null, null, -1, -1, 1, null, 10, 2},
                {null, null, "3", 254, -1, -1, null, 11, 0},
                {"name", null, null, 234, -1, -1, null, 12, 2},
        };

    }

}
