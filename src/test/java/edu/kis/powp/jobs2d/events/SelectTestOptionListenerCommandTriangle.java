package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.CommandFactory;
import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectTestOptionListenerCommandTriangle implements ActionListener {

    private DriverManager driverManager;

    public SelectTestOptionListenerCommandTriangle(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ComplexCommand complexCommand = CommandFactory.drawTriangle(driverManager.getCurrentDriver());
        complexCommand.execute();

    }

}
