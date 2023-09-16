

public abstract class AbstractServerCommand {
    private final String name;
    private final String description;

    public AbstractServerCommand(AbstractCommandBuilder builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

    public abstract String executeCommand();

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }

    public static class AbstractCommandBuilder {
        private String name;
        private String description;

        public AbstractCommandBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AbstractCommandBuilder withDescription(String description) {
            this.description = description;
            return this;
        }
    }
}
