/*
 * Coder: Simone LeFavour
 * Date: Sept. 9, 2024
 * Description: Hello, World! Project for Coding III, prints and draws shapes moving on screen 
 */

package com.processing_example ;

import processing.core.* ;

public class Main extends PApplet 
{
      int numShapes = 15 ; // number of shapes on the screen

      // arrays for the position (x, y), speed (xSpeed, ySpeed), size, color 
      float [] x = new float [numShapes] ;  
      float [] y = new float [numShapes] ;  
      float [] xSpeed = new float [numShapes] ;  
      float [] ySpeed = new float [numShapes] ;  
      float [] sizes = new float [numShapes] ;  // size
      int [] shapeColors = new int [numShapes] ;  // colors 

        // sets up processing and prints "hello world" to console
      public static void main (String[] args) 
        {
            PApplet.main ("com.processing_example.Main") ;
            System.out.println ("Hello world!") ;
        } // end void main 
      

      // sets up the size of screen 
      public void settings () 
        {
            size (500, 500) ; 
        } // end void settings

      // background color and shapes
      public void setup () 
      {
        background (100, 150, 200) ; // light blue background
        
        // shape's properties (position, speed, size, color)
        for (int i = 0; i < numShapes; i++) 
          {
              x [i] = random (width) ; // x position
              y [i] = random (height) ; // y position
              xSpeed [i] = random (2, 5) ; // random horizontal speed
              ySpeed [i] = random (2, 5) ; // random vertical speed
              sizes [i] = random (30, 100) ; // random size for each shape
              shapeColors [i] = color (random(255), random(255), random(255)) ; // random initial color
          } // end for 
      } // end void set up 
    
      public void draw () 
      {
        background (100, 150, 200) ; // background

        // loop for shapes to draw and animate 
        for (int i = 0 ; i < numShapes ; i++) 
          {
            fill (shapeColors[i]) ; // set shape color 
            stroke (0) ; // black outline

        // random shapes 
        switch (i % 4) 
          {
            case 0 :
              ellipse (x[i], y[i], sizes[i], sizes[i]) ;  // ellipse
            break ;

            case 1 :
              rect (x[i], y[i], sizes[i], sizes[i]) ; // rectangle
            break ;

            case 2 :
              triangle 
                (
                  x [i], y [i] - sizes [i] / 2,  // top
                  x [i] - sizes [i] / 2, y [i] + sizes [i] / 2, // left corner
                  x [i] + sizes [i] / 2, y [i] + sizes [i] / 2 // right corner
                ) ;  // draws triangle 
            break ;

            case 3:
              drawPolygon (x [i], y [i], sizes [i] / 2, 6) ; // polygon
            break ;
          } // end switch 

          // position of each shape based on its speed
            x [i] += xSpeed [i] ;
            y [i] += ySpeed [i] ;

          // hitting the wall and changing shape direction 

          // horizontal wall 
            if (x [i] > width - sizes[i] / 2 || x [i] < sizes [i] / 2) 
              {
                xSpeed [i] *= -1 ;  // change direction on the x-axis
                shapeColors [i] = color (random(255), random(255), random(255)) ; // changes random color 
                x [i] = constrain (x [i], sizes [i] / 2, width - sizes [i] / 2) ; // doesn't let shape get too close to wall
              } // end if

          // vertical wall 
            if (y [i] > height - sizes [i] / 2 || y [i] < sizes [i] / 2) 
              {
                ySpeed [i] *= -1 ;  //  change direction on the y-axis
                shapeColors [i] = color (random(255), random(255), random(255)) ; // changes random color 
                y [i] = constrain (y [i], sizes [i] / 2, height - sizes [i] / 2) ; // doesn't let shape get too close to wall
              } // end if 
          } // end for 
      } // end void draw 

    // adding in the polygon 
    void drawPolygon (float xCenter, float yCenter, float radius, int npoints) 
      {
        float angle = TWO_PI / npoints ;
        beginShape ();
        for (int i = 0 ; i < npoints; i++) 
          {
            float x = xCenter + cos (angle * i) * radius ;
            float y = yCenter + sin (angle * i) * radius ;
            vertex (x, y) ; // draws corner of polygon
          } // end for 

        endShape (CLOSE) ; // closes shape
      } // end void
} // end main