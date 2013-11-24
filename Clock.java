import java.awt.Color;

/*************************************************************************
 * Eric Ruggieri
 * assign 4
 * Date submitted 10/17/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation: javac Clock.java, ClockTester.java
 *  Execution: java ClockTester
 * 
 *************************************************************************/
public class Clock{

	private double hourangle, minuteangle;
	private Line secondLine;
	private int secondcounter, minutecounter;
	private Circle outline;
	private Draw canvas;
	
	public Clock(Draw canvasin) 
	{// TODO Auto-generated constructor stub
		canvas = canvasin;
		outline = new Circle(canvasin);
		canvas.setCanvasSize(500, 500);
		canvas.setXscale(0, 500);
		canvas.setYscale(0, 500);
		outline.setRadius(200);
		outline.setLocation(250, 250);
		outline.draw();
		canvas.show();
		addNumbers();
		secondLine = new Line(canvasin, 250, 250, Math.PI/2);
		hourangle = Math.PI/2;
		minuteangle = Math.PI/2;
		secondcounter = 0;
		minutecounter = 0;
		show();
	}

	private void addNumbers() 
	{// TODO Auto-generated method stub
		Text hold;
		for(int i = 1; i <= 12; i++)
		{
			if(i != 12)
			{
				hold = new Text(canvas, outline.theX + Math.cos(Math.PI/2-((2*Math.PI)/12 * i)) * 188, outline.theY + Math.sin(Math.PI/2-((2*Math.PI)/12 * i)) * 188, "" + i);
				hold.draw();
			}
			else
			{
				hold = new Text(canvas, outline.theX + 0, outline.theY + Math.sin(Math.PI/2-((2*Math.PI)/12 * i)) * 188, "" + i);
				hold.draw();
			}
		}
	}

	public void tick()
	{
		secondcounter++;
		canvas.setPenColor(canvas.WHITE);
		canvas.setPenRadius(2.0);
		outline.theCanvas.line(outline.theX, outline.theY, outline.theX + Math.cos(secondLine.getAngle()) * 190, outline.theY + Math.sin(secondLine.getAngle()) * 190);
		secondLine.setAngle(secondLine.getAngle() - (2*Math.PI)/60.0);
		show();
	}

	public void show()
	{
		canvas.setPenColor(canvas.BLACK);
		canvas.setPenRadius();
		outline.draw();
		addNumbers();
		if(secondcounter % 60 == 0 && secondcounter != 0)
		{
			minuteangle -= (2*Math.PI)/60.0;
			minutecounter++;
			if(minutecounter % 12 == 0)
				hourangle -= (2*Math.PI)/60.0;
		}
		int hour = minutecounter / 60 % 12;
		if (hour == 0)
			hour = 12;
		if(secondcounter / 60 % 60 < 10)
			outline.theCanvas.text(235, 470, "Time is: " + hour + ": 0" + secondcounter / 60 % 60);
		else
			outline.theCanvas.text(242, 470, "Time is: " + hour + ": " + secondcounter / 60 % 60);
		outline.theCanvas.line(outline.theX, outline.theY, outline.theX + Math.cos(hourangle) * 140, outline.theY + Math.sin(hourangle) * 140);
		outline.theCanvas.line(outline.theX, outline.theY, outline.theX + Math.cos(minuteangle) * 160, outline.theY + Math.sin(minuteangle) * 160);
		outline.theCanvas.line(outline.theX, outline.theY, outline.theX + Math.cos(secondLine.getAngle()) * 180, outline.theY + Math.sin(secondLine.getAngle()) * 180);
		outline.theCanvas.show(1000);
	}
}


class Line extends Figure{
	double anglevalue;
	public Line(Draw canvas, double x, double y, double angle) {
		// TODO Auto-generated constructor stub
		super(canvas);
		anglevalue = angle;
	}
	
	public void draw()
	{
		super.draw();
		theCanvas.line(theX, theY, theX + Math.cos(anglevalue) * 180, theY + Math.sin(anglevalue) * 180);
	}
	
	public void setAngle(double angle)
	{
		anglevalue = angle;
	}
	
	public double getAngle()
	{
		return anglevalue;
	}
}


class Text extends Figure{
	String texttodraw;
	public Text(Draw canvas, double X, double Y, String text) 
	{
		// TODO Auto-generated constructor stub
		super(canvas);
		theX = X;
		theY = Y;
		texttodraw = text;
	}
	
	public void draw()
	{
		super.draw();
		theCanvas.text(theX, theY, texttodraw);
	}
	
	public void setText(String text)
	{
		texttodraw = text;
	}

}

class Figure {

    protected double theX;   // the x-coordinate
    protected double theY;   // the y-coordinate

    private Color theColor; // the color of the figure
    protected Draw theCanvas;  // the canvas on which the figure is 
                               // to be drawn

    public Figure(Draw canvas) {

	theX = 0;
	theY = 0;

	theCanvas = canvas;

	theCanvas.setPenColor(theCanvas.BLACK);
    }

    public double getX() { return theX; }
    public double getY() { return theY; }

    public void setLocation(double x, double y) {
	// set the x,y coordinates of the figure
	theX = x;
	theY = y;
    }

    public void setColor(Color color) {
	theColor = color;
    }

    public Color getColor() {
	return theColor;
    }

    public void draw() {
	// set the pen color to the current color specified
	// in theColor, but leave the actual drawing to the
	// subclass object
	theCanvas.setPenColor(theColor);
    }

    public void show(int delay) {
	theCanvas.show(delay);
    }
}

// A circle is a particular type of Figure
class Circle extends Figure {

    // its unique features:
    protected double theRadius;  // the radius
    private boolean filled;    // filled or not
    
    public Circle(Draw canvas) {
	super(canvas);  // make sure to create the *whole* object
	theRadius = 1;  // default radius is 1
	filled = false; // default is not filled
    }

    public void setRadius(double radius) {
	theRadius = radius;
    }

    public double getArea() {
	return Math.pow(theRadius,2) * Math.PI;
    }

    public void setFilled() {
	filled = true;
    }

    public void unSetFilled() {
	filled = false;
    }

    public void move(double x, double y) {
	// intended for making small moves
	Color savedColor = getColor();
	
	this.setRadius(theRadius + .01);
	this.setColor(Color.WHITE);
	this.draw();
	
	this.setLocation(x,y);
	this.setRadius(theRadius - .01);
	this.setColor(savedColor);
	this.draw();
    }

    public void moveTo(double x, double y, int steps) {
	// smoothly move to x and y in the specified number of small steps
	//
	// this is for illustration, but may not be the most useful design

	double distX = Math.abs(x - theX);
	double distY = Math.abs(y - theY);

	double dx = // the x distance to travel in each step
	    (x > theX ? distX / steps: -distX / steps);
	double dy = // the y distance to travel in each step
	    (y > theY ? distY / steps: -distY / steps);

	for (int i = 0; i < steps; i++) {
	    theCanvas.show(20);

	    Color savedColor = getColor();

	    this.setRadius(theRadius + .01);
	    this.setColor(Color.WHITE);
	    this.draw();

	    this.setLocation(theX + dx,theY + dy);
	    this.setRadius(theRadius - .01);
	    this.setColor(savedColor);
	    this.draw();
	}
    }


    public void draw() {
	super.draw();  // correctly set the pen color

	if (filled)
	    theCanvas.filledCircle(theX, theY, theRadius);
	else
	    theCanvas.circle(theX, theY, theRadius);
    }
}
