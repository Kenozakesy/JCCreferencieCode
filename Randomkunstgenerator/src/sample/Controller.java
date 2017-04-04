package sample;

import javax.swing.text.html.ImageView;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.control.TextField;
import sample.Models.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Models.Drawable;
import sample.Models.Rectangle;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Controller
{
    @FXML
    private Canvas cvDraw;

    @FXML
    private ComboBox cbSeename;

    @FXML
    private TextField tbName;

    private ArrayList<Drawable> Drawables = new ArrayList<>();
    private ArrayList<Drawable> Disposable = new ArrayList<>();

    //random functie
    private static Random r = new Random();
    private static Random rand = new Random();

    //Draw a circle
    public void DrawCircle() {

            //make random circle
            int X = rand.nextInt(880) + 1;
            int Y = rand.nextInt(440) + 1;

            int heigth = rand.nextInt(450 - Y) + 1;
            int width = rand.nextInt(880 - X ) + 1;

            //circle aanmaken
            sample.Models.Circle circle = new sample.Models.Circle(X, Y, javafx.scene.paint.Color.BLUE, javafx.scene.paint.Color.BLUE, 2, width, heigth);
            Drawables.add(circle);

            //rectangle aanmaken
            //Rectangle r = new Rectangle(1, 1, javafx.scene.paint.Color.BLUE ,  javafx.scene.paint.Color.BLUE , 3, 4, 4);

            //refreshed de picture box
            GraphicsContext gc = cvDraw.getGraphicsContext2D();
            draw(gc);
    }

    //Draw a rectangle
    public void DrawRectangle() {

        //make random circle
        int X = rand.nextInt(880) + 1;
        int Y = rand.nextInt(440) + 1;
        int heigth = rand.nextInt(450 - Y) + 1;
        int width = rand.nextInt(880 - X) + 1;

        //rectangle aanmaken
        Rectangle rec = new Rectangle(X, Y, javafx.scene.paint.Color.RED ,  javafx.scene.paint.Color.RED , 2, width, heigth);
        Drawables.add(rec);

        //refreshed de picture box
        GraphicsContext gc = cvDraw.getGraphicsContext2D();
        draw(gc);
    }

    //Draw a line
    public void DrawLine() {

        //make random circle
        int Y = rand.nextInt(450) + 1;
        int X = rand.nextInt(880) + 1;
        int X2 = rand.nextInt(880) + 1;
        int Y2 = rand.nextInt(450) + 1;

        //rectangle aanmaken
        Line line = new Line(X, Y, javafx.scene.paint.Color.BLACK ,  javafx.scene.paint.Color.BLACK , 2, X2, Y2);
        Drawables.add(line);

        //refreshed de picture box
        GraphicsContext gc = cvDraw.getGraphicsContext2D();
        draw(gc);
    }

    //Draw a random shape(this is always a rectangle, circle an line then reset)
    public void Drawrandom(){
        //genereerd een nieuwe vorm
        int next = r.nextInt(3);

        Figures figenum = Figures.Rectangle;
        switch(next)
        {
            case 0:
                figenum = Figures.Circle;
                break;
            case 1:
                figenum = Figures.Rectangle;
                break;
            case 2:
                figenum = Figures.Line;
                break;
        }

        int check = 0;
        String model = "class sample.Models."+figenum.toString() ;
        //Checks if form is already generated
        for (Drawable d:Disposable)
        {
            String dClass = d.getClass().toString();
            if(dClass.equals(model))
            {//If so create something else
                check = 1;
                break;
            }
        }

        //werkt niet goed
        if(check == 1)
        {
            Drawrandom();
        }
        //maakt nieuwe nog niet bestaande vorm aan
        else if(figenum.equals(Figures.Circle))
        {
            int X = rand.nextInt(880) + 1;
            int Y = rand.nextInt(440) + 1;

            int heigth = rand.nextInt(450 - Y) + 1;
            int width = rand.nextInt(880 - X ) + 1;

            //circle aanmaken
            sample.Models.Circle cir = new sample.Models.Circle(X, Y, javafx.scene.paint.Color.BLUE, javafx.scene.paint.Color.BLUE, 2, width, heigth);
            Disposable.add(cir);
            Drawables.add(cir);
        }
        else if(figenum.equals(Figures.Rectangle))
        {
            int X = rand.nextInt(880) + 1;
            int Y = rand.nextInt(440) + 1;
            int heigth = rand.nextInt(450 - Y) + 1;
            int width = rand.nextInt(880 - X) + 1;

            //rectangle aanmaken
            Rectangle rec = new Rectangle(X, Y, javafx.scene.paint.Color.RED ,  javafx.scene.paint.Color.RED , 2, width, heigth);
            Disposable.add(rec);
            Drawables.add(rec);
        }
        else if(figenum.equals(Figures.Line))
        {
            int Y = rand.nextInt(450) + 1;
            int X = rand.nextInt(880) + 1;
            int X2 = rand.nextInt(880) + 1;
            int Y2 = rand.nextInt(450) + 1;

            //rectangle aanmaken
            Line line = new Line(X, Y, javafx.scene.paint.Color.BLACK ,  javafx.scene.paint.Color.BLACK , 2, X2, Y2);
            Disposable.add(line);
            Drawables.add(line);
        }

        if(Disposable.size() == 3)
        {
            Disposable.clear();
        }

        GraphicsContext gc = cvDraw.getGraphicsContext2D();
        draw(gc);
    }

    //Clears all things in application (basically a reset)
    public void Delete() {
        Drawables.clear();
        Disposable.clear();

        GraphicsContext gc = cvDraw.getGraphicsContext2D();
        draw(gc);
    }

    //Deletes all circles from the Canvas
    public void DeleteCircles() {

        ArrayList<Drawable> T = new ArrayList<>();

        for (Drawable i: Drawables ) {
            if(i.getClass() == sample.Models.Circle.class)
            {
                T.add(i);
            }
        }
        Drawables.removeAll(T);

        GraphicsContext gc = cvDraw.getGraphicsContext2D();
        draw(gc);
    }

    //Deletes all Rectangles from the Canvas
    public void DeleteRectangles() {
        ArrayList<Drawable> T = new ArrayList<>();

        for (Drawable i: Drawables ) {
            if(i.getClass() == sample.Models.Rectangle.class)
            {
                T.add(i);
            }
        }
        Drawables.removeAll(T);

        GraphicsContext gc = cvDraw.getGraphicsContext2D();
        draw(gc);
    }

    //Deletes all Lines from the Canvas
    public void DeleteLines() {
        ArrayList<Drawable> T = new ArrayList<>();

        for (Drawable i: Drawables ) {
            if(i.getClass() == sample.Models.Line.class)
            {
                T.add(i);
            }
        }
        Drawables.removeAll(T);

        GraphicsContext gc = cvDraw.getGraphicsContext2D();
        draw(gc);
    }

    //Gets drawing names from the database
    public void RefreshDatabase(){

        cbSeename.getItems().clear();
        DatabaseConnection db = new DatabaseConnection();
        cbSeename.getItems().addAll(db.Getnames());
    }

    //Saves image to the database
    public void SaveImage(){

         String name = tbName.getText();

         DatabaseConnection db = new DatabaseConnection();
         db.insertPicture(name);


        for (Drawable d: Drawables)
        {
            if(d.getClass() == sample.Models.Circle.class)
            {
                db.insertDrawable(d);
                //add circle
                sample.Models.Circle i1 = (sample.Models.Circle) d;
                db.insertCircle(i1);
            }
            if(d.getClass() == sample.Models.Rectangle.class)
            {
                db.insertDrawable(d);
                //add rectangle
                sample.Models.Rectangle i1 = (sample.Models.Rectangle) d;
                db.insertRectangle(i1);
            }
            if(d.getClass() == sample.Models.Line.class)
            {
                db.insertDrawable(d);
                //add line
                Line i1 = (sample.Models.Line) d;
                db.insertLine(i1);
            }
        }
    }

    //Gets drawing from the database
    public void GetImage(){

        Drawables.clear();

        String PicName = cbSeename.getSelectionModel().getSelectedItem().toString();

        DatabaseConnection db = new DatabaseConnection();

       //getlistofalldrawables (circles rectangles lines)

        ArrayList<Drawable> list = db.getdrawables(PicName);
        Drawables.addAll(list);

        GraphicsContext gc = cvDraw.getGraphicsContext2D();
        draw(gc);
    }

    //draw function
    private void draw(GraphicsContext gc) {

        gc.clearRect(0, 0, cvDraw.getWidth(), cvDraw.getHeight());

        //draw shit from the list above
        for(sample.Models.Drawable i : Drawables)
        {
            if(i.getClass() == sample.Models.Circle.class)
            {
                sample.Models.Circle i1 = (sample.Models.Circle) i;
                gc.setStroke(i.GetOutline());
                gc.strokeOval(i1.GetCorX(), i1.GetCorY(), i1.GetWidth(), i1.GetHeight());
            }
            if(i.getClass() == sample.Models.Rectangle.class)
            {
                sample.Models.Rectangle i1 = (sample.Models.Rectangle) i;
                gc.setStroke(i.GetOutline());
                gc.strokeRect(i1.GetCorX(), i1.GetCorY(), i1.GetWidth(), i1.GetHeight());
            }
            if(i.getClass() == sample.Models.Line.class)
            {
                Line i1 = (sample.Models.Line) i;
                gc.setStroke(i.GetOutline());
                gc.strokeLine(i1.GetCorX(), i1.GetCorY(), i1.GetX2(), i1.GetY2());
            }
        }





    }

}
