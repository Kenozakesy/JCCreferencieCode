package sample.Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.event.PaintEvent;

/**
 * Created by Gebruiker on 11-3-2017.
 */
public class Line extends Drawable
{
private int X2;
private int Y2;

    public int GetX2() {return this.X2;}
    public void SetX2(int e) {this.X2 = e;}

    public int GetY2() {return this.Y2;}
    public void SetY2(int e) {this.Y2 = e;}

    public Line(int corX, int corY, Color color, Color Outline, int Linethinkness, int X2, int Y2)
    {
        super(corX, corY, color, Outline, Linethinkness);
        this.X2 = X2;
        this.Y2 = Y2;
    }

    @Override
    public void Draw(GraphicsContext gc)
    {

    }

}
