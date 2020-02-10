package EntitySystem;

import com.googlecode.lanterna.TerminalPosition;
import javafx.geometry.Pos;

public class Position {
    private double x,y;

    public Position(double x, double y){
        this.x=x;
        this.y=y;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double getX(){ return x; }
    public double getY(){ return y; }

    public int getXAsInt(){ return (int)Math.round(x); }
    public int getYAsInt(){ return (int)Math.round(y); }

    public Position add(Position p){
        return new Position(x + p.x, y+p.y);
    }

    public Position multiply(double factor){
        return new Position(x *factor, y*factor);
    }


    public TerminalPosition toTerminalPosition(){
        return new TerminalPosition(getXAsInt(), getYAsInt());
    }
    @Override
    public String toString(){
        return "Position(x= " + x + ", y= " + y + ")";
    }


    public boolean equalsInt(Position other){
        return (getXAsInt() == other.getXAsInt())  && (getYAsInt() == other.getYAsInt());
    }
}
