package sample.Models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.event.PaintEvent;

/**
 * Created by Gebruiker on 11-3-2017.
 */
public abstract class Drawable {

private int corX;
private int corY;
private Color color;
private Color Outline;
private int Linethinkness;

    public int GetCorX()
        {
             return this.corX;
        }
    public void SetCorX(int e)
        {
            this.corX = e;
        }

    public int GetCorY()
    {
        return this.corY;
    }
    public void SetCorY(int e)
    {
        this.corY = e;
    }

    public Color GetColor()
    {
        return this.color;
    }
    public void SetColor(Color e)
    {
        this.color = e;
    }

    public Color GetOutline()
    {
        return this.Outline;
    }
    public void SetOutline(Color e)
    {
        this.Outline = e;
    }

    public int GetLinethinkness()
    {
        return this.Linethinkness;
    }
    public void SetLinethinkness(int e)
    {
        this.Linethinkness = e;
    }


    public Drawable(int corX, int corY, Color color, Color Outline, int linethinkness)
    {
        this.corX = corX;
        this.corY = corY;
        this.color = color;
        this.Outline = Outline;
        this.Linethinkness = linethinkness;
    }

    public abstract void Draw(GraphicsContext gc);
}
