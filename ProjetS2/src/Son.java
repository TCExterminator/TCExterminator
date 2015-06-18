import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public class Son {
    private Audio son;
    
    public Son(String son){
    	 try {
			AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/se/"+son+".ogg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void playSoundEffect(){
        son.playAsSoundEffect(1.0f, 1.0f, false);
    }
    
    public void playMusic(){
        son.playAsMusic(1.0f, 1.0f, true);
    }
}
