package ak.processing.main;
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.math.BigInteger;
import java.util.Timer;

public class Main extends PApplet {

    /*
    public void setup() {

        size(800,600);
        background(0xffffffff);
    }

    public void draw() {
        for(int x = width/-2;x < 400; x++)
        {
            for(int y= height/-2; y < height;y++)
            {
                if(x == 1/y) // y = x*x, y - x*x > 0
                {
                    set(x+width/2, y+height/2, 0x000000ff);
                }else
                {
                    set(x+width/2, y+height/2, 0xffffffff);
                }
            }
        }
    }

    */

    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "Main" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }

    BigInteger y = new BigInteger("960939379918958884971672962127852754715004339660129306651505519271702802395266424689642842174350718121267153782770623355993237280874144307891325963941337723487857735749823926629715517173716995165232890538221612403238855866184013235585136048828693337902491454229288667081096184496091705183454067827731551705405381627380967602565625016981482083418783163849115590225610003652351370343874461848378737238198224849863465033159410054974700593138339226497249461751545728366702369745461014655997933798537483143786841806593422227898388722980000748404719");
    int x = 0;

    int lastSec;
    long lastMilis;
    public void setup() {

        size(424, 68);
        background(0xffffffff);
        scale(2);

        lastSec = second();
        lastMilis = millis();
    }



    public void draw()
    {
        if(lastSec != second()){
            lastSec = second();
            renderGraph();
        }

    }

    public void keyPressed() {
        if (keyCode == 38) {
            y = y.add(BigInteger.valueOf(1));
        }

        if (keyCode == 40) {
            y = y.subtract(BigInteger.valueOf(1));
        }
    }

    public void update(){
        y = y.add(BigInteger.valueOf(17));
    }

    public void renderGraph(){
        for(int ix = 0; ix <= width; ix++)
        {
            for(int iy = 0; iy < height; iy++)
            {
                if(getTuppersPix(ix, y.add(BigInteger.valueOf(iy))))
                {
                    for(int ys = 0; ys < 4; ys++)
                    {
                        for(int xs = 0; xs < 4; xs++)
                        {
                            set(((width - 4) - 4 * ix) + xs, (4 * iy) + ys, color(0, 0,0));
                        }
                    }
                }
                else
                {
                    for(int ys = 0; ys < 4; ys++)
                    {
                        for(int xs = 0; xs < 4; xs++)
                        {
                            set(((width - 4) - 4 * ix) + xs, (4 * iy) + ys, color(255, 255, 255));
                        }
                    }
                }
            }
        }
    }



    public boolean getTuppersPix(int x1, BigInteger y){
        BigInteger x = BigInteger.valueOf(x1);
        return 0.5 < y.divideAndRemainder(BigInteger.valueOf(17))[0].divideAndRemainder(BigInteger.valueOf(2).pow(BigInteger.valueOf(17).multiply(x).add(y.mod(BigInteger.valueOf(17))).intValue()))[0].mod(BigInteger.valueOf(2)).doubleValue();
    }

    public boolean getXSquared(int x, BigInteger y){
        return false;
    }


}
