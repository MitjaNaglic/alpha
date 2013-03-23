import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        TexturePacker2.process("data/png", "data/png/textures", "textures.pack");
//                TiledMapPacker tiledMapPacker=new TiledMapPacker();
//
//                TexturePacker.Settings settings=new TexturePacker.Settings();
//
//                tiledMapPacker.processMap(new File("data/levels/Level1/"),
//                        new File("data/levels/Level1"),
//                        settings);
    }
}
