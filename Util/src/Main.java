import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        TexturePacker2.process("data/png", "data/png/textures", "textures.pack");

    }
}
