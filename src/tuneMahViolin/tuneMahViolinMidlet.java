package tuneMahViolin;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author DaHoC
 */
public class tuneMahViolinMidlet extends MIDlet {

    private Display display;
    AudioPlayer ap;
    private final String[] notes = {
        "G note", "D note", "A note", "E note"
    };
    private simpleImageCanvas canvas;
    private static final String SPLASH_IMAGE = "/media/gawesplash.png";

    public String[] getNotesString() {
        return this.notes;
    }

    public void startApp() {
        display = Display.getDisplay(this);
        this.showSplash();
        ap = new AudioPlayer(this, display);
        display.setCurrent(ap);
    }

    private void showSplash() {
        canvas = new simpleImageCanvas(SPLASH_IMAGE);
        display = Display.getDisplay(this);
        canvas.setFullScreenMode(true);
        display.setCurrent(canvas);
        waitMilliseconds(2500); // Wait some time
    }

    private static void waitMilliseconds(long ms) {
        try {
            Thread.sleep(ms); // ms, e.g for pause to avoid cpu starvation
        } catch (Exception ex) {
            System.err.print("Thread backgrounding failed!");
        }
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
