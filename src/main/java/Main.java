import duke.Duke;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        // Start Sora
        try {
            Duke duke = new Duke();
            duke.startContinuousUserPrompt();
        } catch(IOException e){
            // Exit application
            System.exit(-1);
        }
    }
}
