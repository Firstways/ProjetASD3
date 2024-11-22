package Test;
import static org.junit.Assert.*;
import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;
import  Projet.*;

public class TestQuadTree {
    @Test
    public void test_null_case(){


        "a".equals("a");
    }

    @Test
    public void test_SearchQuadTree_point_no_true(){
        Point p1 = new Point(100,100);
        Quadtree quad_final = new Quadtree(null,null,null,null,p1);

        Point p2 = new Point(50,50);
        Dual dual =quad_final.searchQTree(p2);
        assertTrue(dual.quad.getNo()==(null));
    }


    @Test
    public void test_SearchQuadTree_point_ne_true(){
        Point p1 = new Point(100,100);
        Point p2 = new Point(50,50);
        Point p3 = new Point(75,15);


        Quadtree regionA = new Quadtree(null,null,null,null,p2);

        Quadtree quad_final = new Quadtree(regionA,null,null,null,p1);
        Dual dual =quad_final.searchQTree(p3);

        assertTrue(dual.quad.getNe()==(null));

    }

    @Test
    public void test_addqtree_quadtree_point_bd_true(){
        Color[] colors_region1 = { Color.RED, Color.GREEN, Color.BLUE, Color.GRAY};
        Color[] colors_region2 = { Color.YELLOW, Color.PINK, Color.ORANGE, Color.CYAN};

        Point p1 = new Point(100,100,colors_region1);
        Point p2 = new Point(50,150,colors_region2);

        Quadtree quad_test = new Quadtree(null, null, null, null, p1);
        quad_test.addQTree(p2);

        Quadtree quad2 = new Quadtree(null, null, null, null, p2);

        Quadtree quad_recherche= new Quadtree(null,null , quad2, null, p1);



        assertTrue(quad_test.equals(quad_recherche));

    }


    @Test
    public void test_addqtree_quadtree_point_hd_bg_1_true(){
        
        Point p1 = new Point(100,100);

        Point p2 = new Point(150,50);
        Point p3 = new Point(10,160);

        Quadtree quad_test = new Quadtree(null, null, null, null, p1);
        quad_test.addQTree(p2);
        quad_test.addQTree(p3);

        Quadtree quad3_recherche= new Quadtree(null, null, null, null, p3);
        Quadtree quad2_recherche= new Quadtree(null, null, null, null, p2);

        Quadtree quad_recherche= new Quadtree(null, quad2_recherche, quad3_recherche, null, p1);



        assertTrue(quad_test.equals(quad_recherche));

    }


    @Test
    public void test_addqtree_quadtree_point_hg_hd_true(){
        Point p1 = new Point(100,100);

        Point p2 = new Point(50,50);
        Point p3 = new Point(75,25);

        Quadtree quad_test = new Quadtree(null, null, null, null, p1);
        quad_test.addQTree(p2);
        quad_test.addQTree(p3);

        Quadtree quad3_recherche= new Quadtree(null, null, null, null, p3);
        Quadtree quad2_recherche= new Quadtree(null, quad3_recherche, null, null, p2);
        Quadtree quad_recherche= new Quadtree(quad2_recherche,null, null, null, p1);



        assertTrue(quad_test.equals(quad_recherche));

    }

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
        Point p1 = new Point(500,500,colors_region);
        Point p2 = new Point(100,100,colors_region);
        Point p3 = new Point(750,200,colors_region);
        Point p4 = new Point(800,750,colors_region);
        Point p5 = new Point(250,750,colors_region);

        Point[] points = {p2,p3,p4,p5};

        Quadtree quad_test = new Quadtree(null,null,null,null,p1);
        quad_test.buildQTree(points);


        Quadtree so  = new Quadtree(null,null,null,null,p5);

        Quadtree se  = new Quadtree(null,null,null,null,p4);

        Quadtree ne = new Quadtree(null,null,null,null,p3);

        Quadtree no = new Quadtree(null,null,null,null,p2);

        Quadtree quad_to_test = new Quadtree(no,ne,so,se,p1);

        assertTrue(quad_to_test.equals(quad_test));
    }

    @Test 
    public void test_buildQuadTree__2__true(){
        Color[] colors_region = { Color.RED, Color.GREEN, Color.BLUE, Color.GRAY};
        Point p1 = new Point(800,100,colors_region);
        Point p2 = new Point(500,500,colors_region);
        Point p3 = new Point(600,400,colors_region);
        Point p4 = new Point(700,450,colors_region);

        Point[] points = {p2,p3,p4};

        Quadtree quad_test = new Quadtree(null,null,null,null,p1);
        quad_test.buildQTree(points);

        Quadtree se  = new Quadtree(null,null,null,null,p4);

        Quadtree ne = new Quadtree(null,null,null,se,p3);


        Quadtree so  = new Quadtree(null,ne,null,null,p2);

        Quadtree quad_to_test = new Quadtree(null,null,so,null,p1);

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


    @Test
     public void test_compressQtree_true_no(){
        Color[] colors_region1 = { Color.RED, Color.GREEN, Color.BLUE, Color.GRAY};
        Color[] colors_region2 = { Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN};

        Point p1 = new Point(500,500,colors_region1);
        Point p2 = new Point(250,250,colors_region2);
        Quadtree quad_to_test = new Quadtree(p1);
        quad_to_test.addQTree(p2);

        quad_to_test.compressQTree(p2);

        Quadtree quad = new Quadtree(p1);

        assertTrue(quad.equals(quad_to_test));
    }
}
