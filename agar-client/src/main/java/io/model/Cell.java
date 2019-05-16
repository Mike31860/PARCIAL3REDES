package io.model;

/**
 * Cell
 */
public class Cell {

    private int x;
    private int y;
    private int value;
    private double radius;
    private String user;
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
        this.user=null;
        
    }

    /**
     * @param isUser the isUser to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    public String getUser(){
        return user;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
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

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
