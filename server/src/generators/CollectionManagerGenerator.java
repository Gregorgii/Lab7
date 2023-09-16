package generators;

import managers.CollectionManager;
import things.StudyGroup;
import util.workingWithCommand.FileManager;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CollectionManagerGenerator {
    private final CollectionManager collectionManager;

    public CollectionManagerGenerator(FileManager fileManager) throws FileNotFoundException{
        collectionManager = new CollectionManager(fileManager);
        Scanner scanner = new Scanner(fileManager.getFile());
        StudyGroup.getStudyGroupGenerator().changeCondition(new FromFileStudyGroupGenerator(scanner));
        int i = 1;
        while (scanner.hasNextLine()){
            System.out.println("Study group number " + i + " is ");
            try{
                StudyGroup.getStudyGroupGenerator().generateStudyGroup();
                System.out.println(collectionManager.add(StudyGroup.getStudyGroupGenerator().getStudyGroup()));
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            i++;
        }
        scanner.close();
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}
