/*************************************************************************
 *  Compilation:  javac OODemo.java
 *  Execution:    java OODemo 
 *  Dependencies: Draw.java
 *
 *  To illustrate a simple way to "objectifying" the Draw package.  Note
 *  that any design process often requires (human) backtracking. 
 *
 *  The following concepts are illustrated
 *  - inheritance
 *  - protected fields
 * 
 *************************************************************************/

import java.awt.Color;
import java.util.*;  // for HashMap

// A figure is any type of drawing object.


// this is a client, to test and demonstrate the figure/circle classes

class OODemo {   

    public static void main(String[] args) {

	Draw canvasA = new Draw();
	Draw canvasB = new Draw();

	canvasA.setXscale(-1.5, 1.5);
	canvasA.setYscale(-1.5, 1.5);

	canvasB.setXscale(-1.5, 1.5);
	canvasB.setYscale(-1.5, 1.5);

	Circle circleA = new Circle(canvasA);
	Circle circleB = new Circle(canvasB);
	Circle circleC = new Circle(canvasA);  // same canvas as circleA

	circleA.setRadius(1);
	circleA.setFilled();
	circleA.setColor(Color.BLACK);
	circleA.draw();

	circleC.setRadius(.5);
	circleC.setFilled();
	circleC.setColor(Color.YELLOW);
	circleC.draw();

	circleB.setRadius(.2);
	circleB.setFilled();
	circleB.setColor(Color.RED);
	circleB.draw();

	// this is just a loop to allow me time to separate the canvases
	while (true) {
	    if (canvasB.mousePressed())
		break;
	}

	// we can move by using the class supplied moveTo()
	circleB.moveTo(-1,1,50);

	// this is just a loop to allow me time to talk
	while (true) {
	    if (canvasB.mousePressed())
		break;
	}

	// or we can move by calculating the new positions for each step
	int steps = 40;
	for (int i = 0; i < steps; i++) {
	    canvasB.show(steps);
	    circleB.move(circleB.getX() + 1.0 / steps,circleB.getY());
	}

	// more time to talk
	while (true) {
	    if (canvasA.mousePressed())
		break;
	}

	// this shows that animation isn't easy -- need to maintain
	// a representation of all the objects on the canvas and
	// repaint frequently

	double mX = canvasA.mouseX();  // get the x coordinate of the mouse

	if (mX > 0) { 
	    // if we just move the circle, we leave a trail of unpainted space
	    circleC.moveTo(-1,1,20);
	}
	else {
	    // need to repaint the objects underneath
	    steps = 100;
	    for (int i = 0; i < steps; i++) {
		canvasA.show(20);
		circleA.draw();
		circleC.move(circleC.getX() - 1.0/steps,
			     circleC.getY() + 1.0/steps);
	    }
	    circleA.draw();
	    circleC.draw();

	    canvasA.show(20);
	}
    }
}


