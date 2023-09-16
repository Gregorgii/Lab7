package commands.serverCommands;
import managers.*;

import java.io.IOException;

public class SaveCommand extends  AbstractServerCommand{

    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager){
        super(new AbstractCommandBuilder()
                .withName("save")
                .withDescription("save collection to file"));
        this.collectionManager = collectionManager;
    }

    @Override
    public String executeCommand(){
        try{
            collectionManager.getFileManager().fileWriter(collectionManager);
            return "Collection saved successfully";
        } catch (IOException e){
            System.out.println(e.getMessage());
            return "Something went wrong. Check access modification ";
        }
    }
}
