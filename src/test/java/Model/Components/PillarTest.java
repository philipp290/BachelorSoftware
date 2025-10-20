package Model.Components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PillarTest {
    //Die Referenz-Werte wurden mit einem externen Programm berechnet
    @Test
    public void distanceAbove20Test(){
        Pillar p = new Pillar(1,-74.006000,40.712800,Shadow.HOCH);
        double result = p.distanceTo(-74.006000, 40.712989);
        System.out.print(result);
        assertEquals(result,21, 0.1);
    }
    @Test
    public void distanceAt20Test(){
        Pillar p = new Pillar(1,13.405000,52.520000,Shadow.HOCH);
        double result = p.distanceTo(13.405000, 52.5201799);
        System.out.print(result);
        assertEquals(result,20, 0.1);
    }
    @Test
    public void distanceUnder20Test(){
        Pillar p = new Pillar(1, 13.405000,52.520000,Shadow.HOCH);
        double result = p.distanceTo(13.405000,  52.5201709);
        System.out.print(result);
        assertEquals(result,19, 0.1);
    }
    @Test
    public void distanceBigTest(){
        Pillar p = new Pillar(1, 11.576124,48.137255,Shadow.HOCH);
        double result = p.distanceTo(11.567424,  48.227000);
        System.out.print(result);
        assertEquals(result,10000, 0.1);
    }
}