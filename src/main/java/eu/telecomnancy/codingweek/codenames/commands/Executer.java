package eu.telecomnancy.codingweek.codenames.commands;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Executer {
    
    private final ArrayDeque<Command> historic = new ArrayDeque<>();
    private final ArrayList<Command> commands = new ArrayList<>();

    public ArrayDeque<Command> getHistoric() {
        return this.historic;
    }
    public void clearHistoric() {
        this.historic.clear();
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }
    public void addComands(List<Command> commands) {
        this.commands.addAll(commands);
    }
    public void cancelCommands() {
        this.commands.clear();
    }

    public void executeAll() {
        for (var command : commands) {
            command.execute();
            historic.push(command);
        }
        commands.clear();
    }
    public void execute(int n) {
        for (int i = 0; i < n && i < commands.size(); i++) {
            commands.get(i).execute();
            commands.remove(i);
            historic.push(commands.get(i));
        }
    }

}
