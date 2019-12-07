package com.example.fooocare;
import com.example.fooocare.Model.BMRModel;
import com.example.fooocare.Model.CaloryCounter;
import com.example.fooocare.Model.Sepakbola;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class BmrCalculatorTest {
    ArrayList<BMRModel> bmrList;
    @Test
    public void addition_isCorrect()
    {
        bmrList  = new ArrayList<BMRModel>();
        //18 -10 tahun Laki-Laki
        bmrList.add(new BMRModel(55,10, 18, 1625, "Laki-Laki"));
        bmrList.add(new BMRModel(60,10, 18, 1713, "Laki-Laki"));
        bmrList.add(new BMRModel(65,10, 18, 1801, "Laki-Laki"));
        bmrList.add(new BMRModel(70,10, 18, 1889, "Laki-Laki"));
        bmrList.add(new BMRModel(75,10, 18, 1977, "Laki-Laki"));
        bmrList.add(new BMRModel(80,10, 18, 2065, "Laki-Laki"));
        bmrList.add(new BMRModel(85,10, 18, 2242, "Laki-Laki"));
        //18-30 tahun laki-laki
        bmrList.add(new BMRModel(55,18, 30, 1514, "Laki-Laki"));
        bmrList.add(new BMRModel(60,18, 30, 1589, "Laki-Laki"));
        bmrList.add(new BMRModel(65,18, 30, 1664, "Laki-Laki"));
        bmrList.add(new BMRModel(70,18, 30, 1739, "Laki-Laki"));
        bmrList.add(new BMRModel(75,18, 30, 1814, "Laki-Laki"));
        bmrList.add(new BMRModel(80,18, 30, 1964, "Laki-Laki"));
        bmrList.add(new BMRModel(85,18, 30, 2039, "Laki-Laki"));
        //4-60 tahun laki-laki
        bmrList.add(new BMRModel(55,30, 60, 1499, "Laki-Laki"));
        bmrList.add(new BMRModel(60,30, 60, 1556, "Laki-Laki"));
        bmrList.add(new BMRModel(65,30, 60, 1613, "Laki-Laki"));
        bmrList.add(new BMRModel(70,30, 60, 1670, "Laki-Laki"));
        bmrList.add(new BMRModel(75,30, 60, 1727, "Laki-Laki"));
        bmrList.add(new BMRModel(80,30, 60, 1785, "Laki-Laki"));
        bmrList.add(new BMRModel(85,30, 60, 1842, "Laki-Laki"));

        //18 -10 tahun Perempuan
        bmrList.add(new BMRModel(40,10, 18, 1224, "Perempuan"));
        bmrList.add(new BMRModel(45,10, 18, 1291, "Perempuan"));
        bmrList.add(new BMRModel(50,10, 18, 1424, "Perempuan"));
        bmrList.add(new BMRModel(55,10, 18, 1491, "Perempuan"));
        bmrList.add(new BMRModel(60,10, 18, 1557, "Perempuan"));
        bmrList.add(new BMRModel(65,10, 18, 1624, "Perempuan"));
        bmrList.add(new BMRModel(70,10, 18, 1691, "Perempuan"));
        //18-30 tahun Perempuan
        bmrList.add(new BMRModel(40,10, 18, 1075, "Perempuan"));
        bmrList.add(new BMRModel(45,10, 18, 1149, "Perempuan"));
        bmrList.add(new BMRModel(50,10, 18, 1223, "Perempuan"));
        bmrList.add(new BMRModel(55,10, 18, 1296, "Perempuan"));
        bmrList.add(new BMRModel(60,10, 18, 1370, "Perempuan"));
        bmrList.add(new BMRModel(65,10, 18, 1444, "Perempuan"));
        bmrList.add(new BMRModel(70,10, 18, 1518, "Perempuan"));
        //4-60 tahun Perempuan
        bmrList.add(new BMRModel(40,10, 18, 1167, "Perempuan"));
        bmrList.add(new BMRModel(45,10, 18, 1207, "Perempuan"));
        bmrList.add(new BMRModel(50,10, 18, 1248, "Perempuan"));
        bmrList.add(new BMRModel(55,10, 18, 1288, "Perempuan"));
        bmrList.add(new BMRModel(60,10, 18, 1329, "Perempuan"));
        bmrList.add(new BMRModel(65,10, 18, 1369, "Perempuan"));
        bmrList.add(new BMRModel(70,10, 18, 1410, "Perempuan"));

        CaloryCounter.bmrList = bmrList;
        float BMR_counted = CaloryCounter.coutnCalory((float)60, (float) 19, "Laki-Laki", (float)160, (float)1.8);
        assertNotEquals(1789.3, BMR_counted, 1);
    }
    @Test
    public void testCountAgendaKalori()
    {
        bmrList  = new ArrayList<BMRModel>();
        //18 -10 tahun Laki-Laki
        bmrList.add(new BMRModel(55,10, 18, 1625, "Laki-Laki"));
        bmrList.add(new BMRModel(60,10, 18, 1713, "Laki-Laki"));
        bmrList.add(new BMRModel(65,10, 18, 1801, "Laki-Laki"));
        bmrList.add(new BMRModel(70,10, 18, 1889, "Laki-Laki"));
        bmrList.add(new BMRModel(75,10, 18, 1977, "Laki-Laki"));
        bmrList.add(new BMRModel(80,10, 18, 2065, "Laki-Laki"));
        bmrList.add(new BMRModel(85,10, 18, 2242, "Laki-Laki"));
        //18-30 tahun laki-laki
        bmrList.add(new BMRModel(55,18, 30, 1514, "Laki-Laki"));
        bmrList.add(new BMRModel(60,18, 30, 1589, "Laki-Laki"));
        bmrList.add(new BMRModel(65,18, 30, 1664, "Laki-Laki"));
        bmrList.add(new BMRModel(70,18, 30, 1739, "Laki-Laki"));
        bmrList.add(new BMRModel(75,18, 30, 1814, "Laki-Laki"));
        bmrList.add(new BMRModel(80,18, 30, 1964, "Laki-Laki"));
        bmrList.add(new BMRModel(85,18, 30, 2039, "Laki-Laki"));
        //4-60 tahun laki-laki
        bmrList.add(new BMRModel(55,30, 60, 1499, "Laki-Laki"));
        bmrList.add(new BMRModel(60,30, 60, 1556, "Laki-Laki"));
        bmrList.add(new BMRModel(65,30, 60, 1613, "Laki-Laki"));
        bmrList.add(new BMRModel(70,30, 60, 1670, "Laki-Laki"));
        bmrList.add(new BMRModel(75,30, 60, 1727, "Laki-Laki"));
        bmrList.add(new BMRModel(80,30, 60, 1785, "Laki-Laki"));
        bmrList.add(new BMRModel(85,30, 60, 1842, "Laki-Laki"));

        //18 -10 tahun Perempuan
        bmrList.add(new BMRModel(40,10, 18, 1224, "Perempuan"));
        bmrList.add(new BMRModel(45,10, 18, 1291, "Perempuan"));
        bmrList.add(new BMRModel(50,10, 18, 1424, "Perempuan"));
        bmrList.add(new BMRModel(55,10, 18, 1491, "Perempuan"));
        bmrList.add(new BMRModel(60,10, 18, 1557, "Perempuan"));
        bmrList.add(new BMRModel(65,10, 18, 1624, "Perempuan"));
        bmrList.add(new BMRModel(70,10, 18, 1691, "Perempuan"));
        //18-30 tahun Perempuan
        bmrList.add(new BMRModel(40,10, 18, 1075, "Perempuan"));
        bmrList.add(new BMRModel(45,10, 18, 1149, "Perempuan"));
        bmrList.add(new BMRModel(50,10, 18, 1223, "Perempuan"));
        bmrList.add(new BMRModel(55,10, 18, 1296, "Perempuan"));
        bmrList.add(new BMRModel(60,10, 18, 1370, "Perempuan"));
        bmrList.add(new BMRModel(65,10, 18, 1444, "Perempuan"));
        bmrList.add(new BMRModel(70,10, 18, 1518, "Perempuan"));
        //4-60 tahun Perempuan
        bmrList.add(new BMRModel(40,10, 18, 1167, "Perempuan"));
        bmrList.add(new BMRModel(45,10, 18, 1207, "Perempuan"));
        bmrList.add(new BMRModel(50,10, 18, 1248, "Perempuan"));
        bmrList.add(new BMRModel(55,10, 18, 1288, "Perempuan"));
        bmrList.add(new BMRModel(60,10, 18, 1329, "Perempuan"));
        bmrList.add(new BMRModel(65,10, 18, 1369, "Perempuan"));
        bmrList.add(new BMRModel(70,10, 18, 1410, "Perempuan"));

        ArrayList<Sepakbola> sepakbola = new ArrayList<>();

        sepakbola.add(new Sepakbola(50,7));
        sepakbola.add(new Sepakbola(60,8));
        sepakbola.add(new Sepakbola(70,9));
        sepakbola.add(new Sepakbola(80,10));
        sepakbola.add(new Sepakbola(90,12));

        CaloryCounter.bmrList = bmrList;
        CaloryCounter.mdlSepakbola = sepakbola;
        float BMR_counted = CaloryCounter.coutnCalory((float)60, (float) 19, "Laki-Laki", (float)160, (float)1.8);
        float kaloriCounting = CaloryCounter.agendaCounter(60,3);
        float total = BMR_counted + kaloriCounting / 7;

        assertEquals(3454.8,total,1);




    }
}
