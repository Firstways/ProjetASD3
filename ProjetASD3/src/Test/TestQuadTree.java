package Test;
import static org.junit.Assert.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import  Projet.*;

public class TestQuadTree {
    @Test
    public void test_null_case(){


        "a".equals("a");
    }

    // @Test
    // public void test_quadtree_point_hg_true(){
    //     Point p1 = new Point(100,100);
    //     Quadtree quad_final = new Quadtree(null,null,null,null,p1);

    //     Point p2 = new Point(50,50);
    //     assertTrue(quad_final.searchQTree(quad_final, p2)==(quad_final.getNo()));

    // }

    // @Test
    // public void test_quadtree__point_bd_true(){
    //     Point p1 = new Point(100,100);
    //     Quadtree quad_final = new Quadtree(null,null,null,null,p1);

    //     Point p2 = new Point(150,150);
    //     assertTrue(quad_final.searchQTree(quad_final, p2)==(quad_final.getSe()));

    // }
    // @Test
    // public void test_quadtree__point_hg_hd_bg_true(){
    //     Point p1 = new Point(100,100);
    //     Point p2 = new Point(50,50);
    //     Point p3 = new Point(75,15);
    //     Point p4 = new Point(60,34);

    //     Quadtree regionAB = new Quadtree(null,null,null,null,p3);

    //     Quadtree regionA = new Quadtree(null,regionAB,null,null,p2);

    //     Quadtree quad_final = new Quadtree(regionA,null,null,null,p1);

    //     assertTrue(quad_final.searchQTree(quad_final, p4)==(regionAB.getSe()));

    // }

    // @Test
    // public void test_quadtree__point_hg_hd_true(){
    //     Point p1 = new Point(100,100);
    //     Point p2 = new Point(50,50);
    //     Point p3 = new Point(75,15);


    //     Quadtree regionA = new Quadtree(null,null,null,null,p2);

    //     Quadtree quad_final = new Quadtree(regionA,null,null,null,p1);

    //     assertTrue(quad_final.searchQTree(quad_final, p3)==(regionA.getNe()));

    // }

    @Test
    public void test_addqtree_quadtree_point_hd_true(){
        Point p1 = new Point(100,100);

        Point p2 = new Point(50,50);

        Quadtree quad_test = new Quadtree(null, null, null, null, p1);
        quad_test.addQTree(p2);

        Quadtree quad2 = new Quadtree(null, null, null, null, p2);
        Quadtree quad_recherche= new Quadtree(quad2,null , null, null, p1);



        assertTrue(quad_test.equals(quad_recherche));

    }


    // @Test
    // public void test_addqtree_quadtree_point_hd_bg_1_true(){
    //     Point p1 = new Point(100,100);

    //     Point p2 = new Point(150,50);
    //     Point p3 = new Point(50,150);

    //     Quadtree quad_test = new Quadtree(null, null, null, null, p1);
    //     quad_test.addQTree(p2, quad_test);
    //     quad_test.addQTree(p3, quad_test);

    //     Quadtree quad3_recherche= new Quadtree(null, null, null, null, p3);
    //     Quadtree quad2_recherche= new Quadtree(null, null, null, null, p2);
    //     Quadtree quad_recherche= new Quadtree(null, quad2_recherche, quad3_recherche, null, p1);



    //     assertTrue(quad_test.equals(quad_recherche));

    // }


    // @Test
    // public void test_addqtree_quadtree_point_hg_hd_true(){
    //     Point p1 = new Point(100,100);

    //     Point p2 = new Point(50,50);
    //     Point p3 = new Point(75,25);

    //     Quadtree quad_test = new Quadtree(null, null, null, null, p1);
    //     quad_test.addQTree(p2, quad_test);
    //     quad_test.addQTree(p3, quad_test);

    //     Quadtree quad3_recherche= new Quadtree(null, null, null, null, p3);
    //     Quadtree quad2_recherche= new Quadtree(null, quad3_recherche, null, null, p2);
    //     Quadtree quad_recherche= new Quadtree(null, quad2_recherche, null, null, p1);



    //     assertTrue(quad_test.equals(quad_recherche));

    // }

    @Test 
    public void test_equals_quadtree_h1_true(){
        Point p1 = new Point(100,100);


        Quadtree quad_test = new Quadtree(null,null,null,null,p1);

        Quadtree quad_to_test = new Quadtree(null,null,null,null,p1);

        assertTrue(quad_to_test.equals(quad_test));
    }

    @Test 
    public void test_equals_quadtree_h1_false(){
        Point p1 = new Point(100,100);
        Point p2 = new Point(100,8);


        Quadtree quad_test = new Quadtree(null,null,null,null,p2);

        Quadtree quad_to_test = new Quadtree(null,null,null,null,p1);

        assertFalse(quad_to_test.equals(quad_test));
    }




    @Test 
    public void test_equals_quadtree_h2_true(){
        Point p1 = new Point(100,100);
        Point p2 = new Point(100,8);

        Quadtree quad2_test = new Quadtree(null,null,null,null,p2);

        Quadtree quad_test = new Quadtree(quad2_test,null,null,null,p1);


        Quadtree quad2_to_test = new Quadtree(null,null,null,null,p2);

        Quadtree quad_to_test = new Quadtree(quad2_to_test,null,null,null,p1);

        assertTrue(quad_to_test.equals(quad_test));
    }



    @Test 
    public void test_buildQuadTree__1__true(){
        Color[] colors_region = { Color.RED, Color.GREEN, Color.BLUE, Color.GRAY};
        Point p1 = new Point(200,200,colors_region);
        Point p2 = new Point(100,100,colors_region);
        Point p3 = new Point(250,200,colors_region);
        Point p4 = new Point(200,250,colors_region);
        Point p5 = new Point(200,250,colors_region);

        Point[] points = {p2,p3,p4,p5};

        Quadtree quad_test = new Quadtree(null,null,null,null,p1);
        quad_test.buildQTree(points);


        Quadtree so  = new Quadtree(null,null,null,null,p5);

        Quadtree se  = new Quadtree(null,null,null,null,p4);

        Quadtree ne = new Quadtree(null,null,null,null,p3);

        Quadtree no = new Quadtree(null,null,null,null,p2);

        Quadtree quad_to_test = new Quadtree(no,null,null,null,p1);

        assertTrue(quad_to_test.equals(quad_test));
    }


    /*
     * 
     * A Tester dans la classe main
     */

    @Test
    public void test_to_image_hauteur2(){
        Color[] colors_region1 = { Color.RED, Color.GREEN, Color.BLUE, Color.GRAY};
        Color[] colors_region2 = { Color.YELLOW, Color.PINK, Color.ORANGE, Color.CYAN};
        Color[] colors_region3 = { Color.LIGHT_GRAY, Color.MAGENTA, Color.WHITE, Color.BLACK};

        Point p1 = new Point(500,500,colors_region1);
        Point p2 = new Point(850,150,colors_region2);
        Point p3 = new Point(800,100,colors_region3);


        Quadtree ne_se = new Quadtree(null,null,null,null,p2);

        Quadtree ne = new Quadtree(null,null,null,ne_se,p3);


        Quadtree quad_to_test = new Quadtree(null,ne,null,null,p1);
        Image img = new Image(1000, 1000);

        quad_to_test.toImage("square", img);
        try {
            img.save("square");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
