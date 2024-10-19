package Test;
import static org.junit.Assert.*;

import org.junit.Test;
import  Projet.*;

public class TestQuadTree {
    @Test
    public void test_null_case(){


        "a".equals("a");
    }

    @Test
    public void test_quadtree_point_hg_true(){
        Point p1 = new Point(100,100);
        Quadtree quad_final = new Quadtree(null,null,null,null,p1);

        Point p2 = new Point(50,50);
        assertTrue(quad_final.searchQTree(quad_final, p2)==(quad_final.getNo()));

    }

    @Test
    public void test_quadtree__point_bd_true(){
        Point p1 = new Point(100,100);
        Quadtree quad_final = new Quadtree(null,null,null,null,p1);

        Point p2 = new Point(150,150);
        assertTrue(quad_final.searchQTree(quad_final, p2)==(quad_final.getSe()));

    }
    @Test
    public void test_quadtree__point_hg_hd_bg_true(){
        Point p1 = new Point(100,100);
        Point p2 = new Point(50,50);
        Point p3 = new Point(75,15);
        Point p4 = new Point(60,34);

        Quadtree regionAB = new Quadtree(null,null,null,null,p3);

        Quadtree regionA = new Quadtree(null,regionAB,null,null,p2);

        Quadtree quad_final = new Quadtree(regionA,null,null,null,p1);

        assertTrue(quad_final.searchQTree(quad_final, p4)==(regionAB.getSe()));

    }

    @Test
    public void test_quadtree__point_hg_hd_true(){
        Point p1 = new Point(100,100);
        Point p2 = new Point(50,50);
        Point p3 = new Point(75,15);


        Quadtree regionA = new Quadtree(null,null,null,null,p2);

        Quadtree quad_final = new Quadtree(regionA,null,null,null,p1);

        assertTrue(quad_final.searchQTree(quad_final, p3)==(regionA.getNe()));

    }
}
