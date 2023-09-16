package util.workingWithCommand;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AvailableCommands {

    public static final Set<String> COMMANDS_WITHOUT_ARGS = new HashSet<>();
    public static final Set<String> COMMANDS_WITH_ID_ARG = new HashSet<>();
    public static final Set<String> COMMANDS_WITH_STUDY_GROUP_ARG = new HashSet<>();
    public static final Set<String> COMMANDS_WITH_STUDY_GROUP_AND_ID_ARG = new HashSet<>();
    public static final Set<String> COMMANDS_WITH_TRANSFERRED_ARG = new HashSet<>();
    public static final Set<String> COMMANDS_WITH_EXPELLED_ARG = new HashSet<>();


    static {
        Collections.addAll(
                COMMANDS_WITHOUT_ARGS,
                "help",
                "info",
                "show",
                "clear",
                "exit",
                "remove first",
                "print_field_descending_semester");
        Collections.addAll(COMMANDS_WITH_STUDY_GROUP_ARG,
                "add",
                "remove_greater",
                "add_if_max");
        Collections.addAll(COMMANDS_WITH_ID_ARG,
                "remove_by_id");
        Collections.addAll(COMMANDS_WITH_STUDY_GROUP_AND_ID_ARG,
                "update");
        Collections.addAll(COMMANDS_WITH_TRANSFERRED_ARG,
                "count_greater_than_transferred");
        Collections.addAll(COMMANDS_WITH_EXPELLED_ARG,
                "remove_any_by_should_be_expelled");

    }

    private AvailableCommands(){
    }
}
