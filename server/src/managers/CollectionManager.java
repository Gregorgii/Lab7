package managers;

import things.StudyGroup;
import util.workingWithCommand.FileManager;

import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionManager {

    private static Integer idCounter = 1;
    private final ZonedDateTime creationDate;
    private LinkedList<StudyGroup> groupCollection = new LinkedList<>();
    private final FileManager fileManager;


    public CollectionManager(FileManager fileManager) throws FileNotFoundException {
        this.fileManager = fileManager;
        this.creationDate = ZonedDateTime.now();
    }

    public LinkedList<StudyGroup> getGroupCollection() {return groupCollection; }

    public FileManager getFileManager() {return fileManager; }

    /**
     * Returns s default file path specified in class.
     * @return path
     */



    public String info() {
        String result = "";
        result += "Information about collection:\n";
        result += "Created at " + creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + '\n';
        result += "Collection type is " + groupCollection.getClass().getName() + '\n';
        result += "Amount of items stored in - " + groupCollection.size() + '\n';
        return result;
    }

    public String show() {
        StringBuilder string = new StringBuilder();
        if (groupCollection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty");
        } else {
            List<StudyGroup> sortedList = groupCollection.stream().sorted().toList();
            sortedList.forEach(sg -> string.append(sg.toString()).append("\n"));
        }
        return string.toString();
    }

    public String add(StudyGroup studyGroup) {
            studyGroup.setId(idCounter++);
            studyGroup.setCreationDate(ZonedDateTime.now());
            groupCollection.add(studyGroup);
            groupCollection = groupCollection
                    .stream()
                    .sorted()
                    .collect(Collectors.toCollection(LinkedList::new));
            return "Study group added successfully";
    }

    public String update(Integer id, StudyGroup element) {
        if (groupCollection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty");
        } else {
            boolean found = false;
            List<StudyGroup> filteredList = groupCollection.stream().filter(sg -> sg.getId().equals(id)).toList();
            if (!filteredList.isEmpty()) {
                element.setId(id);
                groupCollection.remove(filteredList.get(0));
                groupCollection.add(element);
                found = true;
            }
            groupCollection = groupCollection
                    .stream()
                    .sorted()
                    .collect(Collectors.toCollection(LinkedList::new));
            if (!found) {
                throw new IllegalArgumentException("No such element with id = " + id);
            } else {
                return "Element with id  " + id + " updated successfully";
            }
        }
    }

    public String removeById(Integer id) {
        if (groupCollection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty");
        } else {
            boolean found = groupCollection.removeIf(sg -> sg.getId().equals(id));
            if (!found) {
                throw new IllegalArgumentException("No such element with id = " + id);
            } else {
                return "Element with id " + id + " deleted successfully";
            }
        }
    }

    public String clear() {
        if (groupCollection.isEmpty()) {
            return "Collection is empty already";
        } else {
            groupCollection.clear();
            return "Collection cleared successfully";
        }

    }



    public String removeFirst() {
        if (groupCollection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty");
        } else {
            return "First element: " + groupCollection.poll() + "\n deleted successfully";
        }
    }

    public String removeGreater(StudyGroup studyGroup) {
        if (groupCollection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty");
        } else {
            boolean found = groupCollection.removeIf(sg -> sg.compareTo(studyGroup) > 0);
            if (!found) {
                throw new IllegalArgumentException("Elements greater than this not found");
            } else {
                return "Elements greater than this deleted successfully";
            }
        }
    }

    public String removeAnyByShouldBeExpelled(Integer shouldBeExpelled ) {
        if (groupCollection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty");
        } else {
            boolean found = groupCollection.removeIf(sg -> Objects.equals(sg.getShouldBeExpelled(), shouldBeExpelled));
            if (!found) {
                throw new IllegalArgumentException("No such elements with should be expelled = " + shouldBeExpelled);
            } else {
                return "Elements with value of should be expelled = " + shouldBeExpelled + " removed successfully";
            }
        }
    }

    public String countGreaterThanTransferredStudents(Integer countOfTransferredStudents){
        if (groupCollection.isEmpty()){
            throw new IllegalArgumentException("Collection is empty");
        } else{
            int count = (int) groupCollection
                    .stream()
                    .filter(sg -> Objects.equals(sg.getTransferredStudents(), countOfTransferredStudents)).count();
            if (count == 0){
                throw new IllegalArgumentException("No such elements with Transferred Students = " + countOfTransferredStudents);
            } else {
                return "Count of elements with value of Transferred students = " + count;
            }
        }
    }

    public String addIfMax(StudyGroup studyGroup){
        if(groupCollection.isEmpty()){
            studyGroup.setId(idCounter++);
            studyGroup.setCreationDate(ZonedDateTime.now());
            groupCollection.add(studyGroup);
            groupCollection = groupCollection
                    .stream()
                    .sorted()
                    .collect(Collectors.toCollection(LinkedList::new));
            return "Study group added successfully";
        } else {
            List<StudyGroup> filteredList = groupCollection.stream().filter(sg -> sg.getStudentsCount() > studyGroup.getStudentsCount()).toList();
            if (filteredList.isEmpty()){
                studyGroup.setId(idCounter++);
                studyGroup.setCreationDate(ZonedDateTime.now());
                groupCollection.add(studyGroup);
                groupCollection = groupCollection
                        .stream()
                        .sorted()
                        .collect(Collectors.toCollection(LinkedList::new));
                return "Study group added successfully";
            } else {
                throw new IllegalArgumentException("No elements less than the input");
            }

        }
    }

    public String printFieldDescendingSemesterEnum(){
        StringBuilder string = new StringBuilder();
        if (groupCollection.isEmpty()){
            throw new IllegalArgumentException("Collection is empty");
        } else {
            List<StudyGroup> sortedList = groupCollection.stream().sorted().toList();
            sortedList.forEach(studyGroup -> string.append(studyGroup.getSemesterEnum()).append("\n"));
        }
        return string.toString();
    }


}



