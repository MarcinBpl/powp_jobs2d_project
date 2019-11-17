package edu.kis.powp.jobs2d;

import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.drivers.adapter.AbstractDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.Job2dAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.LineDrawerAdapter;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener2;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListenerJane;
import edu.kis.powp.jobs2d.events.SelectTestOptionListenerCommand;
import edu.kis.powp.jobs2d.events.SelectTestOptionListenerCommandRectangle;
import edu.kis.powp.jobs2d.events.SelectTestOptionListenerCommandTriangle;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DefaultDrawerFrame;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.events.SelectChangeVisibleOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;

public class TestJobs2dPatterns {

	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 *
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
			DriverFeature.getDriverManager());

		application.addTest("Figure Joe 1", selectTestFigureOptionListener);

		SelectTestFigureOptionListener2 selectTestFigureOptionListener2 = new SelectTestFigureOptionListener2(
			DriverFeature.getDriverManager());
		application.addTest("Figure Joe 2", selectTestFigureOptionListener2);

		SelectTestFigureOptionListenerJane selectTestFigureOptionListenerJane = new SelectTestFigureOptionListenerJane(
			DriverFeature.getDriverManager());
		application.addTest("Figure Jane", selectTestFigureOptionListenerJane);

		SelectTestOptionListenerCommand selectTestOptionListenerCommand = new SelectTestOptionListenerCommand(
			DriverFeature.getDriverManager());
		application.addTest("Command", selectTestOptionListenerCommand);

		SelectTestOptionListenerCommandRectangle selectTestOptionListenerCommandRectangle = new SelectTestOptionListenerCommandRectangle(
			DriverFeature.getDriverManager());
		application.addTest("Command Rectangle", selectTestOptionListenerCommandRectangle);

		SelectTestOptionListenerCommandTriangle selectTestOptionListenerCommandTriangle = new SelectTestOptionListenerCommandTriangle(
			DriverFeature.getDriverManager());
		application.addTest("Command Triangle", selectTestOptionListenerCommandTriangle);
	}

	/**
	 * Setup driver manager, and set default driver for application.
	 * 
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		Job2dDriver loggerDriver = new LoggerDriver();

		DriverFeature.addDriver("Logger Driver", loggerDriver);
		DriverFeature.getDriverManager().setCurrentDriver(loggerDriver);

		Job2dDriver testDriver = new Job2dAdapter(DrawerFeature.getDrawerController());
		DriverFeature.addDriver("Basic Line Simulator", testDriver);

		Job2dDriver testDriver2 = new LineDrawerAdapter(LineFactory.getDottedLine());
		DriverFeature.addDriver("Dotted Line Simulator", testDriver2);

		Job2dDriver testDriver3 = new LineDrawerAdapter(LineFactory.getSpecialLine());
		DriverFeature.addDriver("Special Line Simulator", testDriver3);

		Job2dDriver janeDriver = new AbstractDriverAdapter();
		DriverFeature.addDriver("Jane Simulator", janeDriver);

		DriverFeature.updateDriverInfo();
	}

	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {
		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("2d jobs Visio");
				DrawerFeature.setupDrawerPlugin(app);

				DriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupLogger(app);

				app.setVisibility(true);
			}
		});
	}

}
