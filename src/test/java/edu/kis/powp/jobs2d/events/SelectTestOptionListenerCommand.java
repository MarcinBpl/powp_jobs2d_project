package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestOptionListenerCommand implements ActionListener {

    private DriverManager driverManager;

    public SelectTestOptionListenerCommand(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SetPositionCommand setPositionCommand = new SetPositionCommand(driverManager.getCurrentDriver(), 60, 60);
        OperateToCommand operateToCommand = new OperateToCommand(driverManager.getCurrentDriver(), -50, -50);

        setPositionCommand.execute();
        operateToCommand.execute();

    }

}
