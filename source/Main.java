//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main;

import core.Core;
import core.ImageProcessing;
import core.Stdim;

import java.awt.image.BufferedImage;
import java.io.File;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class Main extends Application {

    public static ImageView View;
    static Stdim mainImage = null;
    static Stdim imgBackup;
    static FileChooser fc = new FileChooser();
    FileChooser savefile = new FileChooser();
    public static boolean LONG_OR_BESIDE;
    static String GraghName;
    static String directry;
    public static String scdir;
    static Scene scene;
    public static ComboBox<String> comboBox;
    public static Slider slider;
    static Stdim secondim;
    public static CheckBox overlap;
    public static String nowM = "";
    public static Stage pubStage;
    public static boolean image_pressed = false;
    public static Stage pub_stage;
    public static Color drawcol;
    public static boolean line = false;
    public static double drawX, drawY;
    public static BufferedImage mainimage_bufferedimage;
    public static BufferedImage cleannessImage;
    public static boolean clnsQ;

    public static void main(String[] args){
        Application.launch();
    }

    public void start(Stage stage) throws Exception {

        pub_stage = stage;

        drawcol = Color.WHITE;
        rgb = Core.rgb((int)(drawcol.getRed()*255), (int)(drawcol.getGreen()*255), (int)(drawcol.getBlue()*255));

        this.savefile.setInitialFileName(".jpg");
        pubStage = stage;
        stage.setWidth(800.0D);
        stage.setHeight(500.0D);
        stage.setTitle("akichCanvas");
        fc.setTitle("File select");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("イメージファイル", "*.jpg", "*.png"));
        this.savefile.setTitle("Save");
        MenuBar menu = new MenuBar();
        Menu help = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction((event1) -> {
            this.onClick(stage);
        });
        help.getItems().addAll(about);
        Menu file = new Menu("File");
        MenuItem open = new MenuItem("Open");
        MenuItem create = new MenuItem("Create");
        MenuItem savem = new MenuItem("Save");
        MenuItem quit = new MenuItem("Quit");
        open.setOnAction((event1) ->
            open(stage)
        );
        create.setOnAction(event3 ->
            ImageCreate(stage, 640, 480)
        );
        savem.setOnAction(event1 ->
            savem(stage)
        );
        quit.setOnAction(event1 ->
            System.exit(0)
        );
        file.getItems().addAll(open, create, savem, quit);
        Menu tools = new Menu("Tools");
        Menu gragh = new Menu("Gragh");
        MenuItem beside = new MenuItem("Beside");
        beside.setOnAction((event1) -> {
            LONG_OR_BESIDE = false;
            this.onc(stage);
        });
        MenuItem lng = new MenuItem("Long");
        lng.setOnAction((event1) -> {
            LONG_OR_BESIDE = true;
            this.onc(stage);
        });
        MenuItem openb = new MenuItem("ToolBox");
        openb.setOnAction(event1 -> {
            this.toolBox(stage);
            stage.show();
        });
        tools.getItems().addAll(gragh, openb);
        gragh.getItems().addAll(beside, lng);
        menu.getMenus().addAll(file, tools, help);
        comboBox = new ComboBox();
        comboBox.getItems().addAll("normal", "nega", "gray", "twoway",
                "smooth", "sharping", "edge", "solarization", "postarization", "red only",
                "green only", "blue only", "flip(mirror)", "flip(180)", "flip(90RIGHT)",
                "flip(90LEFT)", "outline", "sepia", "noise", "bright","blend", "psecol",
                "thin");

        comboBox.setEditable(false);
        comboBox.setOnAction((event) -> {
            IP(true);
        });
        slider = new Slider(0.0D, 255.0D, 0.0D);
        slider.setPrefWidth(250.0D);
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50.0D);
        slider.setBlockIncrement(5.0D);

        View = new ImageView();
        View.setOnMouseDragged(event2 -> {

            if(line) {

                x2 = x;
                y2 = y;

                x = (x+ (int) event2.getX()) / 2;
                y = (y + (int) event2.getY()) / 2;

                mainimage_bufferedimage.setRGB(x, y, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y + 1, rgb);
                mainimage_bufferedimage.setRGB(x, y + 1, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y + 1, rgb);

                if(clnsQ){
                    cleannessImage.setRGB(x, y, rgb);
                    cleannessImage.setRGB(x - 1, y - 1, rgb);
                    cleannessImage.setRGB(x, y - 1, rgb);
                    cleannessImage.setRGB(x + 1, y - 1, rgb);
                    cleannessImage.setRGB(x - 1, y, rgb);
                    cleannessImage.setRGB(x + 1, y, rgb);
                    cleannessImage.setRGB(x - 1, y + 1, rgb);
                    cleannessImage.setRGB(x, y + 1, rgb);
                    cleannessImage.setRGB(x + 1, y + 1, rgb);
                }

                x = x2;
                y = y2;

                x = ((3*x) + (int) event2.getX()) / 4;
                y = ((3*y) + (int) event2.getY()) / 4;

                mainimage_bufferedimage.setRGB(x, y, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y + 1, rgb);
                mainimage_bufferedimage.setRGB(x, y + 1, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y + 1, rgb);

                if(clnsQ){
                    cleannessImage.setRGB(x, y, rgb);
                    cleannessImage.setRGB(x - 1, y - 1, rgb);
                    cleannessImage.setRGB(x, y - 1, rgb);
                    cleannessImage.setRGB(x + 1, y - 1, rgb);
                    cleannessImage.setRGB(x - 1, y, rgb);
                    cleannessImage.setRGB(x + 1, y, rgb);
                    cleannessImage.setRGB(x - 1, y + 1, rgb);
                    cleannessImage.setRGB(x, y + 1, rgb);
                    cleannessImage.setRGB(x + 1, y + 1, rgb);
                }

                x = x2;
                y = y2;

                x = (x + ((int)event2.getX()*3)) / 4;
                y = (y + ((int) event2.getY()*3)) / 4;

                mainimage_bufferedimage.setRGB(x, y, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y + 1, rgb);
                mainimage_bufferedimage.setRGB(x, y + 1, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y + 1, rgb);

                if(clnsQ){
                    cleannessImage.setRGB(x, y, rgb);
                    cleannessImage.setRGB(x - 1, y - 1, rgb);
                    cleannessImage.setRGB(x, y - 1, rgb);
                    cleannessImage.setRGB(x + 1, y - 1, rgb);
                    cleannessImage.setRGB(x - 1, y, rgb);
                    cleannessImage.setRGB(x + 1, y, rgb);
                    cleannessImage.setRGB(x - 1, y + 1, rgb);
                    cleannessImage.setRGB(x, y + 1, rgb);
                    cleannessImage.setRGB(x + 1, y + 1, rgb);
                }

                x = x2;
                y = y2;

                x = ((7*x) + (int) event2.getX()) / 8;
                y = ((7*y) + (int) event2.getY()) / 8;

                mainimage_bufferedimage.setRGB(x, y, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y + 1, rgb);
                mainimage_bufferedimage.setRGB(x, y + 1, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y + 1, rgb);

                if(clnsQ){
                    cleannessImage.setRGB(x, y, rgb);
                    cleannessImage.setRGB(x - 1, y - 1, rgb);
                    cleannessImage.setRGB(x, y - 1, rgb);
                    cleannessImage.setRGB(x + 1, y - 1, rgb);
                    cleannessImage.setRGB(x - 1, y, rgb);
                    cleannessImage.setRGB(x + 1, y, rgb);
                    cleannessImage.setRGB(x - 1, y + 1, rgb);
                    cleannessImage.setRGB(x, y + 1, rgb);
                    cleannessImage.setRGB(x + 1, y + 1, rgb);
                }

                x = x2;
                y = y2;

                x = (x + ((int)event2.getX()*7)) / 8;
                y = (y + ((int) event2.getY()*7)) / 8;

                mainimage_bufferedimage.setRGB(x, y, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y - 1, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y, rgb);
                mainimage_bufferedimage.setRGB(x - 1, y + 1, rgb);
                mainimage_bufferedimage.setRGB(x, y + 1, rgb);
                mainimage_bufferedimage.setRGB(x + 1, y + 1, rgb);

                if(clnsQ){
                    cleannessImage.setRGB(x, y, rgb);
                    cleannessImage.setRGB(x - 1, y - 1, rgb);
                    cleannessImage.setRGB(x, y - 1, rgb);
                    cleannessImage.setRGB(x + 1, y - 1, rgb);
                    cleannessImage.setRGB(x - 1, y, rgb);
                    cleannessImage.setRGB(x + 1, y, rgb);
                    cleannessImage.setRGB(x - 1, y + 1, rgb);
                    cleannessImage.setRGB(x, y + 1, rgb);
                    cleannessImage.setRGB(x + 1, y + 1, rgb);
                }

            }

            line = true;

            x = (int)event2.getX();
            y = (int)event2.getY();

            mainimage_bufferedimage.setRGB(x, y, rgb);
            mainimage_bufferedimage.setRGB(x-1, y-1, rgb);
            mainimage_bufferedimage.setRGB(x, y-1, rgb);
            mainimage_bufferedimage.setRGB(x+1, y-1, rgb);
            mainimage_bufferedimage.setRGB(x-1, y, rgb);
            mainimage_bufferedimage.setRGB(x+1, y, rgb);
            mainimage_bufferedimage.setRGB(x-1, y+1, rgb);
            mainimage_bufferedimage.setRGB(x, y+1, rgb);
            mainimage_bufferedimage.setRGB(x+1, y+1, rgb);
            View.setImage(change(mainImage));

            if(clnsQ){
                cleannessImage.setRGB(x, y, rgb);
                cleannessImage.setRGB(x - 1, y - 1, rgb);
                cleannessImage.setRGB(x, y - 1, rgb);
                cleannessImage.setRGB(x + 1, y - 1, rgb);
                cleannessImage.setRGB(x - 1, y, rgb);
                cleannessImage.setRGB(x + 1, y, rgb);
                cleannessImage.setRGB(x - 1, y + 1, rgb);
                cleannessImage.setRGB(x, y + 1, rgb);
                cleannessImage.setRGB(x + 1, y + 1, rgb);
            }


        });
        View.setOnMouseReleased(event -> line = false);
        SliderT slt = new SliderT();
        slt.start();
        HBox imfield = new HBox();
        imfield.setPadding(new Insets(30.0D, 30.0D, 30.0D, 30.0D));
        imfield.setAlignment(Pos.BOTTOM_CENTER);
        imfield.getChildren().addAll(View);
        VBox root = new VBox();

        root.getChildren().addAll(menu, imfield);

        stage.setScene(scene = new Scene(root));


        scene.setOnKeyPressed(event -> {
            if(event.isShiftDown()) {
                image_pressed = true;
            }
        });

        scene.setOnKeyReleased(event -> {
            image_pressed = false;
        });
        toolBox(stage);



        stage.show();
    }

    public static synchronized void IP(boolean bool) {
        if(mainImage != null) {
            String howto;
            if(bool) {
                howto = (String)comboBox.getValue();
            } else {
                howto = "";
            }

            if(!overlap.isSelected() || overlap.isSelected() && howto == nowM) {
                loadImage(directry);
            }


            switch(howto) {
                case "normal":
                    ResetImage();
                    nowM = howto;
                    break;
                case "nega":
                    ImageProcessing.negp(mainImage);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "gray":
                    ImageProcessing.gray(mainImage);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "twoway":
                    slider.setMax(128.0D);
                    slider.setMin(-128.0D);
                    slider.setMajorTickUnit(32.0D);
                    ImageProcessing.twoway(mainImage, (int)slider.getValue(), 0);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "smooth":
                    ImageProcessing.smooth(mainImage);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "sharping":
                    slider.setMax(40.0D);
                    slider.setMajorTickUnit(20.0D);
                    ImageProcessing.sharping(mainImage, (int)slider.getValue());
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "edge":
                    slider.setMax(50.0D);
                    slider.setMin(-50.0D);
                    slider.setMajorTickUnit(25.0D);
                    ImageProcessing.edge(mainImage, (int)slider.getValue(), 2);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "solarization":
                    ImageProcessing.solari(mainImage);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "postarization":
                    ImageProcessing.post(mainImage);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "red only":
                    ImageProcessing.separ(0, new Stdim[]{mainImage, mainImage});
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "green only":
                    ImageProcessing.separ(1, new Stdim[]{mainImage, mainImage});
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "blue only":
                    ImageProcessing.separ(2, new Stdim[]{mainImage, mainImage});
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "flip(mirror)":
                    ImageProcessing.flip(mainImage, 1);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "flip(180)":
                    ImageProcessing.flip(mainImage, 0);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "outline":
                    slider.setMax(50.0D);
                    slider.setMajorTickUnit(25.0D);
                    ImageProcessing.outline(mainImage, (int)slider.getValue(), Core.Color(0, 255, 0));
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "sepia":
                    ImageProcessing.sepia(mainImage);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "noise":
                    ImageProcessing.noise(mainImage);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "bright":
                    slider.setMax(255.0D);
                    slider.setMin(-255.0D);
                    slider.setMajorTickUnit(50.0D);
                    ImageProcessing.bright(mainImage, (int)slider.getValue());
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "blend":
                    File open = fc.showOpenDialog(pubStage);
                    scdir = open.getPath().toString();
                    secondim = Core.readStdim(scdir);
                    mainImage = ImageProcessing.blend(mainImage, secondim, 1, 1);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "flip(90RIGHT)":
                    ImageProcessing.flip(mainImage, 4);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "flip(90LEFT)":
                    ImageProcessing.flip(mainImage, 3);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "psecol":
                    ImageProcessing.psecol(mainImage);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                case "thin":
                    ImageProcessing.thin(mainImage, ImageProcessing.THIN_BLACKLINE);
                    View.setImage(change(mainImage));
                    nowM = howto;
                    break;
                default:
            }
        }
    }

    private void ImagePressed(MouseEvent event){
        image_pressed = true;
    }

    public static int x, x2, y, y2, rgb;

    private void ImageMoved(MouseEvent event){
        x = (int)event.getX();
        y = (int)event.getY();

        mainimage_bufferedimage.setRGB(x, y, rgb);
        mainimage_bufferedimage.setRGB(x-1, y-1, rgb);
        mainimage_bufferedimage.setRGB(x, y-1, rgb);
        mainimage_bufferedimage.setRGB(x+1, y-1, rgb);
        mainimage_bufferedimage.setRGB(x-1, y, rgb);
        mainimage_bufferedimage.setRGB(x+1, y, rgb);
        mainimage_bufferedimage.setRGB(x-1, y+1, rgb);
        mainimage_bufferedimage.setRGB(x, y+1, rgb);
        mainimage_bufferedimage.setRGB(x+1, y+1, rgb);
        View.setImage(change(mainImage));
        /*
        if(count > 5) {
            View.setImage(change(mainImage));
            count = 0;
        }
        count++;
        */
    }
    public static void drawPoint(Stdim img, double x, double y, Color col){
        int r = (int)(col.getRed()*255);
        int g = (int)(col.getGreen()*255);
        int b = (int)(col.getBlue()*255);
        int rgb = Core.rgb(r, g, b);
        img.getImg().setRGB((int)x, (int)y, rgb);
        img.getImg().setRGB((int)x-1, (int)y-1, rgb);
        img.getImg().setRGB((int)x, (int)y-1, rgb);
        img.getImg().setRGB((int)x+1, (int)y-1, rgb);
        img.getImg().setRGB((int)x-1, (int)y, rgb);
        img.getImg().setRGB((int)x+1, (int)y, rgb);
        img.getImg().setRGB((int)x-1, (int)y+1, rgb);
        img.getImg().setRGB((int)x, (int)y+1, rgb);
        img.getImg().setRGB((int)x+1, (int)y+1, rgb);

        View.setImage(change(mainImage));
    }

    private void ImageReleased(MouseEvent event){
        image_pressed = false;
    }

    void onClick(Stage stage) {
        MsgBox msgBox = new MsgBox(stage, "about",
                "akichi Projects\nakichCanvas version 0.3.0\n\nPowered by\nJava8 & JavaFX\nakichi Projects "+Core.AKICHIJ_VERSION+"\n\n");
        msgBox.show();
    }

    void onc(Stage stage) {
        MsgBox msgBox = new MsgBox(stage, "Gragh", this.drawG());
        msgBox.show();
    }

    private void open(Stage stage) {
        File open = this.fc.showOpenDialog(stage);
        if(open != null) {
            directry = open.getPath().toString();
            loadImage(directry);
            loadImage(directry);
            if(mainImage.getWidth() <= 1366 && mainImage.getHeight() <= 768) {
                stage.setWidth((double)(mainImage.getWidth() + 60));
                stage.setHeight((double)(mainImage.getHeight() + 120));
                View.setImage(change(mainImage));
            } else {
                stage.setWidth(1600.0D);
                stage.setHeight(900.0D);
                View.setImage(change(mainImage));
                View.fitWidthProperty().bind(scene.widthProperty().multiply(0.8D));
                View.fitHeightProperty().bind(scene.heightProperty().multiply(0.8D));
            }
            mainimage_bufferedimage = mainImage.getImg();
        }

    }

    public static void loadImage(String filename) {
        mainImage = Core.readStdim(filename);
        imgBackup = mainImage.clone();
    }

    Stdim img;
    public void saveImage(String dir) {
        if(clnsQ){
            img = new Stdim();
            img.setImg(cleannessImage);
            Core.saveim(img, dir, reco_extension(dir));
        }else {
            Core.saveim(mainImage, dir, reco_extension(dir));
        }
    }

    private String reco_extension(String dir){
        int index = dir.lastIndexOf('.');
        return dir.substring(index+1);
    }

    private void savem(Stage stage) {
        File importFile = this.savefile.showSaveDialog(stage);
        if(importFile != null) {
            directry = importFile.getPath().toString();
            this.saveImage(directry);
        }

    }

    public static WritableImage change(Stdim image) {
        WritableImage newimg = SwingFXUtils.toFXImage(image.clone().getImg(), (WritableImage)null);
        return newimg;
    }

    public BorderPane drawG() {
        NumberAxis xA;
        NumberAxis yA;
        StackedAreaChart chart;
        int r;
        Series g;
        int b, R, G, B, root, x, c;
        int var14;
        BorderPane var15;
        Series var16;
        Series var17;
        Series var18;
        BorderPane var19;
        if(LONG_OR_BESIDE) {
            xA = new NumberAxis(0.0D, (double)mainImage.getWidth(), (double)(mainImage.getWidth() / 10));
            yA = new NumberAxis(0.0D, 255.0D, 25.0D);
            chart = new StackedAreaChart(xA, yA);
            if(mainImage.getImg().getType() == 1) {
                r = 0;
                g = new Series();
                g.setName("Gray");

                for(b = 0; b < mainImage.getWidth(); ++b) {
                    for(R = 0; R < mainImage.getHeight(); ++R) {
                        G = mainImage.getImg().getRGB(b, R);
                        r += Core.g(G);
                    }

                    r /= mainImage.getHeight();
                    g.getData().add(new Data(Integer.valueOf(b), Integer.valueOf(r)));
                    r = 0;
                }

                chart.getData().add(g);
                chart.setTitle(GraghName);
                var15 = new BorderPane();
                var15.setCenter(chart);
                return var15;
            } else {
                b = 0;
                var14 = 0;
                r = 0;
                var16 = new Series();
                var16.setName("Red");

                for(G = 0; G < mainImage.getWidth(); ++G) {
                    for(B = 0; B < mainImage.getHeight(); ++B) {
                        root = mainImage.getImg().getRGB(G, B);
                        r += Core.r(root);
                    }

                    r /= mainImage.getHeight();
                    var16.getData().add(new Data(Integer.valueOf(G), Integer.valueOf(r)));
                    r = 0;
                }

                var17 = new Series();
                var17.setName("Green");

                for(B = 0; B < mainImage.getWidth(); ++B) {
                    for(root = 0; root < mainImage.getHeight(); ++root) {
                        x = mainImage.getImg().getRGB(B, root);
                        var14 += Core.g(x);
                    }

                    var14 /= mainImage.getHeight();
                    var17.getData().add(new Data(Integer.valueOf(B), Integer.valueOf(var14)));
                    var14 = 0;
                }

                var18 = new Series();
                var18.setName("Blue");

                for(root = 0; root < mainImage.getWidth(); ++root) {
                    for(x = 0; x < mainImage.getHeight(); ++x) {
                        c = mainImage.getImg().getRGB(root, x);
                        b += Core.b(c);
                    }

                    b /= mainImage.getHeight();
                    var18.getData().add(new Data(Integer.valueOf(root), Integer.valueOf(b)));
                    b = 0;
                }

                chart.getData().add(var16);
                chart.getData().add(var17);
                chart.getData().add(var18);
                chart.setTitle(GraghName);
                var19 = new BorderPane();
                var19.setCenter(chart);
                return var19;
            }
        } else {
            xA = new NumberAxis(0.0D, (double)mainImage.getHeight(), (double)(mainImage.getHeight() / 10));
            yA = new NumberAxis(0.0D, 255.0D, 25.0D);
            chart = new StackedAreaChart(xA, yA);
            if(mainImage.getImg().getType() == 1) {
                r = 0;
                g = new Series();
                g.setName("Gray");

                for(b = 0; b < mainImage.getHeight(); ++b) {
                    for(R = 0; R < mainImage.getWidth(); ++R) {
                        G = mainImage.getImg().getRGB(R, b);
                        r += Core.g(G);
                    }

                    r /= mainImage.getWidth();
                    g.getData().add(new Data(Integer.valueOf(b), Integer.valueOf(r)));
                    r = 0;
                }

                chart.getData().add(g);
                chart.setTitle(GraghName);
                var15 = new BorderPane();
                var15.setCenter(chart);
                return var15;
            } else {
                b = 0;
                var14 = 0;
                r = 0;
                var16 = new Series();
                var16.setName("Red");

                for(G = 0; G < mainImage.getHeight(); ++G) {
                    for(B = 0; B < mainImage.getWidth(); ++B) {
                        root = mainImage.getImg().getRGB(B, G);
                        r += Core.r(root);
                    }

                    r /= mainImage.getWidth();
                    var16.getData().add(new Data(Integer.valueOf(G), Integer.valueOf(r)));
                    r = 0;
                }

                var17 = new Series();
                var17.setName("Green");

                for(B = 0; B < mainImage.getHeight(); ++B) {
                    for(root = 0; root < mainImage.getWidth(); ++root) {
                        x = mainImage.getImg().getRGB(root, B);
                        var14 += Core.g(x);
                    }

                    var14 /= mainImage.getWidth();
                    var17.getData().add(new Data(Integer.valueOf(B), Integer.valueOf(var14)));
                    var14 = 0;
                }

                var18 = new Series();
                var18.setName("Blue");

                for(root = 0; root < mainImage.getHeight(); ++root) {
                    for(x = 0; x < mainImage.getWidth(); ++x) {
                        c = mainImage.getImg().getRGB(x, root);
                        b += Core.b(c);
                    }

                    b /= mainImage.getWidth();
                    var18.getData().add(new Data(Integer.valueOf(root), Integer.valueOf(b)));
                    b = 0;
                }

                chart.getData().add(var16);
                chart.getData().add(var17);
                chart.getData().add(var18);
                chart.setTitle(GraghName);
                var19 = new BorderPane();
                var19.setCenter(chart);
                return var19;
            }
        }
    }

    void toolBox(Stage stage) {
        MsgBox msgBox = new MsgBox(stage);
        msgBox.show();
    }

    void ImageCreate(Stage stage, int default_width, int default_height){
        MsgBox msgBox = new MsgBox(stage, default_width, default_height);
        msgBox.show();
    }

    public static void ResetImage() {
        mainImage = imgBackup.clone();
        View.setImage(change(mainImage));
    }
    public static void BreakImage(){
        View.setImage(null);
    }
}




class MsgBox extends Stage {
    public MsgBox(Window wnd, String WindowName, String Text) {
        this.setTitle(WindowName);
        this.initStyle(StageStyle.UTILITY);
        this.initOwner(wnd);
        this.initModality(Modality.APPLICATION_MODAL);
        Label lb = new Label();
        lb.setPrefWidth(250.0D);
        lb.setText(Text);
        Button close = new Button("close");
        close.setPrefWidth(80.0D);
        close.setOnAction((event) -> {
            this.close();
        });
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
        root.setSpacing(20.0D);
        root.getChildren().addAll(new Node[]{lb, close});
        this.setScene(new Scene(root));
    }

    public MsgBox(Window wnd, String WindowName, BorderPane val) {
        this.setTitle(WindowName);
        this.initStyle(StageStyle.UTILITY);
        this.initOwner(wnd);
        this.initModality(Modality.APPLICATION_MODAL);
        Button close = new Button("close");
        close.setPrefWidth(80.0D);
        close.setOnAction((event) -> {
            this.close();
        });
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
        root.setSpacing(20.0D);
        root.getChildren().addAll(new Node[]{val, close});
        this.setScene(new Scene(root));
    }

    public MsgBox(Window wnd) {
        setTitle("ToolBox");
        initStyle(StageStyle.UTILITY);
        initOwner(wnd);
        initModality(Modality.APPLICATION_MODAL);
        Button reset = new Button("Reset");
        reset.setPrefWidth(120.0D);
        reset.setOnAction((event) -> {
            Main.ResetImage();
        });

        Button delete = new Button("Delete");
        delete.setPrefWidth(120.0D);
        delete.setOnAction(event -> {
            Main.clnsQ = false;
            Main.BreakImage();
        });
        Label std = new Label("ImageProcessing");
        VBox stdg = new VBox();
        stdg.setAlignment(Pos.CENTER);
        stdg.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
        stdg.getChildren().addAll(std, Main.comboBox);
        Main.overlap = new CheckBox("Overlap  ");
        HBox under = new HBox();
        under.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
        under.setAlignment(Pos.CENTER);
        under.getChildren().addAll(Main.overlap, reset, delete);

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setPrefHeight(35);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(190.0D, 20.0D, 190.0D, 20.0D));
        root.setSpacing(30.0D);
        root.getChildren().addAll(stdg, Main.slider, colorPicker, under);
        colorPicker.setOnAction(event -> {
            Main.drawcol = colorPicker.getValue();
            Main.rgb = Core.rgb((int)(Main.drawcol.getRed()*255), (int)(Main.drawcol.getGreen()*255), (int)(Main.drawcol.getBlue()*255));
        });
        setScene(new Scene(root));
    }

    public MsgBox(Window wnd, int default_width, int default_height) {

        /*///////////////////////////////////////////////*/

        //default setting

        setTitle("Create New Image");
        initStyle(StageStyle.UTILITY);
        initOwner(wnd);
        initModality(Modality.APPLICATION_MODAL);

        /*///////////////////////////////////////////////*/

        /*///////////////////////////////////////////////*/

        //items setting


        //get width value
        VBox width = new VBox();
        Label widthL = new Label("Width");
        TextField widthT = new TextField();
        widthT.setText(String.valueOf(default_width));
        width.setAlignment(Pos.CENTER);
        width.setPadding(new Insets(10, 10, 10, 10));
        width.setSpacing(10);
        width.getChildren().addAll(widthL, widthT);

        //get height value
        VBox height = new VBox();
        Label heightL = new Label("Height");
        TextField heightT = new TextField();
        heightT.setText(String.valueOf(default_height));
        height.setAlignment(Pos.CENTER);
        height.setPadding(new Insets(10, 10, 10, 10));
        height.setSpacing(10);
        height.getChildren().addAll(heightL, heightT);

        HBox input = new HBox();
        input.setAlignment(Pos.CENTER);
        input.setPadding(new Insets(10, 10, 10, 10));
        input.setSpacing(10);
        input.getChildren().addAll(width, height);

        HBox buttons = new HBox();
        CheckBox cleanness = new CheckBox("Cleanness");
        Button create = new Button("Create");
        create.setOnAction(event -> {
            if(cleanness.isSelected()) {
                Main.cleannessImage = Core.getCleannessImage(Integer.valueOf(widthT.getText()),
                        Integer.valueOf(heightT.getText())).getImg();
                Main.clnsQ = true;
            }
            Main.mainImage = Core.getWhiteStdim(Integer.valueOf(widthT.getText()),
                    Integer.valueOf(heightT.getText()));
            Main.imgBackup = Main.mainImage.clone();
            Main.mainimage_bufferedimage = Main.mainImage.getImg();
            Main.View.setImage(Main.change(Main.mainImage));
            this.close();
        });

        Button cancel = new Button("Cancel");
        cancel.setOnAction(event -> this.close());

        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10, 10, 10, 10));
        buttons.setSpacing(10);
        buttons.getChildren().addAll(cleanness, create, cancel);

        /*///////////////////////////////////////////////*/

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(input, buttons);

        setScene(new Scene(root));

    }
}



class SliderT extends Thread {
    SliderT() {
    }

    public void run() {
        int defaultValue = 0;

        while (true) {
            int newdefault = (int) Main.slider.getValue();

            try {
                Thread.sleep(10L);
            } catch (InterruptedException var4) {

            }

            if (defaultValue != newdefault) {
                Main.IP(true);
                defaultValue = newdefault;
            }
        }
    }
}
/*

class WindowSizeManeger extends Thread{
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            if (Main.mainImage != null) {
                if ((double) Main.mainImage.getHeight() > Main.pubStage.getHeight() + 10.0D) {
                    Main.View.fitHeightProperty().bind(Main.scene.heightProperty().multiply(0.9D));
                }

                if ((double) Main.mainImage.getWidth() > Main.pubStage.getWidth() + 50.0D) {
                    Main.View.fitWidthProperty().bind(Main.scene.widthProperty().multiply(0.9D));
                }
            }
        }
    }
}
*/
