package managers;

import things.StudyGroup;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class CollectionManager {
    private final Date creationDate;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    private LinkedList<StudyGroup> groupCollection;

    public CollectionManager()  {
       creationDate = new Date();
    }
    public void setGroupCollection(LinkedList<StudyGroup> studyGroups){
        try{
            writeLock.lock();
            groupCollection = studyGroups;
            groupCollection = groupCollection
                    .stream()
                    .sorted()
                    .collect(Collectors.toCollection(LinkedList::new));
        } finally {
            writeLock.unlock();
        }
    }

    public LinkedList<StudyGroup> getUsersElements(List<Integer> idList) {
        try {
            readLock.lock();
            LinkedList<StudyGroup> studyGroups = new LinkedList<>();
            for (StudyGroup studyGroup : groupCollection) {
                if (idList.contains(studyGroup.getId())) {
                    studyGroups.add(studyGroup);
                }
            }
            return studyGroups.isEmpty() ? null : studyGroups;
        } finally {
            readLock.unlock();
        }
    }

    public LinkedList<StudyGroup> getAlienElements(List<Integer> idList) {
        try {
            readLock.lock();
            LinkedList<StudyGroup> studyGroups = new LinkedList<>();
            for (StudyGroup  studyGroup: groupCollection) {
                if (!idList.contains(studyGroup.getId())) {
                    studyGroups.add(studyGroup);
                }
            }
            return studyGroups.isEmpty() ? null : studyGroups;
        } finally {
            readLock.unlock();
        }
    }

    public LinkedList<StudyGroup> getGroupCollection(){
        try{
            readLock.lock();
            return groupCollection;
        } finally {
            readLock.unlock();
        }
    }


    public String info() {
        String result = "";
        result += "Information about collection:\n";
        result += "Created at " + creationDate + '\n';
        result += "Collection type is " + groupCollection.getClass().getName() + '\n';
        result += "Amount of items stored in - " + groupCollection.size() + '\n';
        return result;
    }



    public String add(StudyGroup studyGroup) {
     try {
         writeLock.lock();
         groupCollection.add(studyGroup);
         groupCollection = groupCollection
                 .stream()
                 .sorted()
                 .collect(Collectors.toCollection(LinkedList::new));
         return "Study group added successfully";
     } finally {
         writeLock.unlock();
     }
    }

    public String update(Integer id, StudyGroup element) {
       try {
           writeLock.lock();
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
    } finally {
            writeLock.unlock();
       }
       }


    public Integer getFirstId(List<Integer> idList) {
        try {
            readLock.lock();
            for (StudyGroup studyGroup : groupCollection) {
                if (idList.contains(studyGroup.getId())) {
                    return studyGroup.getId();
                }
            }
            return null;
        } finally {
            readLock.unlock();
        }
    }

    public List<Integer> returnIdsOfGreater(StudyGroup studyGroup, List<Integer> idList) {
        try {
            readLock.lock();
            List<StudyGroup> studyGroups = getUsersElements(idList);
            studyGroups = studyGroups.stream().filter(sg -> sg.compareTo(studyGroup) < 0).collect(Collectors.toList());
            List<Integer> idListLower = new ArrayList<>();
            for (StudyGroup sg : studyGroups) {
                idListLower.add(sg.getId());
            }
            return idListLower;
        } finally {
            readLock.unlock();
        }
    }

    public List<Integer> returnIdsOfShouldBeExpelled(StudyGroup studyGroup, List<Integer> idList) {
        try {
            readLock.lock();
            List<StudyGroup> studyGroups = getUsersElements(idList);
            studyGroups = studyGroups.stream().filter(sg -> sg.getShouldBeExpelled().compareTo(studyGroup.getShouldBeExpelled()) == 0).collect(Collectors.toList());
            List<Integer> idListLower = new ArrayList<>();
            for (StudyGroup sg : studyGroups) {
                idListLower.add(sg.getId());
            }
            return idListLower;
        } finally {
            readLock.unlock();
        }
    }

    public String removeById(Integer id) {
        try {
            writeLock.lock();
            if (groupCollection.isEmpty()) {
                throw new IllegalArgumentException("Коллекция пуста");
            } else {
                boolean found = groupCollection.removeIf(sg -> sg.getId().equals(id));
                if (!found) {
                    throw new IllegalArgumentException("Элементов со значением id = " + id + " не найдено");
                } else {
                    return "Элемент со значением id = " + id + " успешно удален";
                }
            }
        } finally {
            writeLock.unlock();
        }

    }


    public String getShouldBeExpelled(Integer shouldBeExpelled ) {
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


    public Long countGreaterThanTransferred(Integer transferredStudents, List<Integer> idList) {
        try {
            readLock.lock();
            List<StudyGroup> studyGroups = getUsersElements(idList);
            studyGroups = studyGroups.stream().filter(sg -> sg.getTransferredStudents().compareTo(transferredStudents) == 0).collect(Collectors.toList());
            List<Integer> idListGreater = new ArrayList<>();
            for (StudyGroup sg : studyGroups) {
                idListGreater.add(sg.getId());
            }
            return idListGreater.stream().count();
        } finally {
            readLock.unlock();
        }
    }

    public boolean isMax(StudyGroup groupToCompare, List<Integer> idList){
            try {
                readLock.lock();
                List<StudyGroup> studyGroups = getUsersElements(idList);
                studyGroups = studyGroups.stream().filter(sg -> sg.getTransferredStudents().compareTo(groupToCompare.getTransferredStudents()) > 0).collect(Collectors.toList());
                List<Integer> idListGreater = new ArrayList<>();
                for (StudyGroup sg : studyGroups) {
                    idListGreater.add(sg.getId());
                }
                if (idListGreater.isEmpty()) {
                    return true;
                } else{
                        return false;
                    }
            } finally {
                readLock.unlock();
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



