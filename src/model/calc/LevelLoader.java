/**
 -A-
 	The information creator is the LevelLoader class and this is where the level created.
 	The information representor is the Level class, this class control all the data of the level.   
 -B-
 	Due to this separation we keep open\close principle and we can change the way level been create
 	or add more ways without changing the abilities of the level, 
 	on the other side we can change or add the functions of the level without knowing how it's create.
 -C-
 	This separation preserves the Liskov principle and we make sure that new classes 
 	are extending the base classes without changing their behavior for example 
 	we can create new type of Load class and they all have the ability to load level. 
 -D-
 	String filename returns filereader object and we can use it only for files, 
 	but using Inputstram we can use every type of inputstaem (including filereader) and get
 	input from more sources like server.
 
 
 
   */
package model.calc;

import java.io.IOException;
import java.io.InputStream;

import common.Level;

public interface LevelLoader {
    public Level loadLevel(InputStream in) throws IOException, ClassNotFoundException;

}
