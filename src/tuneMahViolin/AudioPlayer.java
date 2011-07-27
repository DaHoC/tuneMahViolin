package tuneMahViolin;

import javax.microedition.lcdui.*;
import javax.microedition.media.*;

/**
 *
 * @author, @see http://j2mesamples.blogspot.com/2009/06/playing-audio-files.html
 */
public class AudioPlayer extends List implements CommandListener, PlayerListener {

    tuneMahViolinMidlet midlet;
    private Display display;
    private Command play, exit;
    private Player player;
    private int playing = -1; // -1 meaning playing no note, 0 first note, etc.
    private String[] notes;
    private String[] noteFiles = {
        "violinGString.mp3", "violinDString.mp3", "violinAString.mp3", "violinEString.mp3"
    };

    public AudioPlayer(tuneMahViolinMidlet midlet, Display display) {
        super("Select string to tune", List.IMPLICIT, midlet.getNotesString(), null);
        this.midlet = midlet;
        this.notes = midlet.getNotesString();
        this.display = Display.getDisplay(midlet);
        play = new Command("Play", Command.OK, 0);
        exit = new Command("Exit", Command.EXIT, 0);
        addCommand(play);
        addCommand(exit);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == play || c == List.SELECT_COMMAND) {
            try {
                if (playing == -1) {
                    playAudio(this.getSelectedIndex());
                    playing = this.getSelectedIndex();
                } else {
                    player.stop();
                    player.close();
                    if (this.getSelectedIndex() != playing) { // if note is different than currently playing one, play the new one
                        playAudio(this.getSelectedIndex());
                        playing = this.getSelectedIndex();
                    } else {
                        playing = -1;
                    }
                }
            } catch (Exception e) {
            }
        } else if (c == exit) {
            if (player != null) {
                player.close();
                playing = -1;
            }
            midlet.destroyApp(false);
        }
    }

    public void playerUpdate(Player player, String event, Object eventData) {
    }

    public void playAudio(int index) {
        try {
            player = Manager.createPlayer(getClass().getResourceAsStream("/media/" + this.noteFiles[index]), "audio/mpeg");
            player.addPlayerListener(this);
            player.setLoopCount(-1);
/*
            VolumeControl vc = (VolumeControl) player.getControl("VolumeControl");
            if (vc != null) {
                vc.setLevel(100);
            }
*/
            player.prefetch();
            player.realize();
            player.start();
        } catch (Exception e) {
        }
    }
}
