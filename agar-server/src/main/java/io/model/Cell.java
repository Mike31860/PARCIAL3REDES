package io.model;

import io.connection.Server;

/**
 * Cell
 */
public class Cell {

    private int x;
    private int y;
    private int value;
    private double radius;
    private int r;
    public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	private int g;
    private int b;

    public Cell(int x, int y, int r, int g, int b, int value, double radius) {
        this.x=x;
        this.y=y;
        this.r=r;
        this.g=g;
        this.b=b;
        this.value=value;
        this.radius=radius;
        
    }
    
    public void setX(int x) {
		this.x = x;
	}
    
    public void setY(int y) {
		this.y = y;
	}

   

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }
    
    public void setRadius(double radius) {
		this.radius = radius;
	}

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
	public void setValue(int value) {
		this.value=value;
		
	}

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
    
    public void move(char direction) {
    	
    	switch (direction) {
		case 'u':
			if( (y-1) - radius >= 0) {
				y-=1;
			}
			break;

		case 'd':
			
			if( (y+1) + radius <= Server.HEIGHT) {
				y+=1;
			}
			break;
			
		case 'l':
			
			if( (x-1 ) - radius >=0 ) {
				x-=1;
			}
			break;
			
		case 'r':
			
			if( (x+1) +radius <= Server.WIDTH ) {
				x+=1;
			}
			break;
		}
    }

    
    //TODO: deberia funcionar, no? pero no lo hace
	
	public boolean eatable(Cell cell) {
		return  x - radius >= cell.x-cell.radius
				&& x + radius <= cell.x+radius
				&& y - radius >= cell.y-cell.radius
				&& y + radius <= cell.y+cell.radius;
	}


}
