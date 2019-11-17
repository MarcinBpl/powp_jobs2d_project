package edu.kis.powp.jobs2d.command;

import java.util.List;

public class ComplexCommand implements DriverCommand{

    private List<DriverCommand> commandList;

    public ComplexCommand(List<DriverCommand> commandList){
        this.commandList = commandList;
    }

    @Override
    public void execute() {
        commandList.forEach(DriverCommand::execute);
    }
}
