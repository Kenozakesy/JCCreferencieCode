package sample.Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.event.PaintEvent;

/**
 * Created by Gebruiker on 11-3-2017.
 */
public class Rectangle extends Drawable {

    private int width;
    private int height;

    public int GetWidth() {return this.width;}
    public void SetWidth(int e) {this.width = e;}

    public int GetHeight() {return this.height;}
    public void SetHeight(int e) {this.height = e;}

    public Rectangle(int corX, int corY, Color color, Color Outline, int Linethinkness, int width, int height) {
        super(corX, corY, color, Outline, Linethinkness);
        this.width = width;
        this.height = height;
    }

    @Override
    public void Draw(GraphicsContext gc)
    {


    }

}


