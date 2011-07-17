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

    public String[] getNotesString() {
        return this.notes;
    }

    public void startApp() {
        display = Display.getDisplay(this);
        ap = new AudioPlayer(this, display);
        display.setCurrent(ap);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
