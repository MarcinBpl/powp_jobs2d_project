import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class EmailTemplate extends Application {

	public static HTMLEditor htmlEditor;

	public static Button save = new Button("Save");
	public static Button open = new Button("Open");
	public static TextField templateName = new TextField();
	public static final List<Template> TEMPLATES = new ArrayList<>();
	public static ListView listView = new ListView();

	@Override
	public void start(Stage stage) {

		Pane root = new Pane();
		root.setBackground(new Background(new BackgroundFill(Color.web("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY)));

		htmlEditor = new HTMLEditor();
		htmlEditor.setPrefHeight(800);
		htmlEditor.setPrefWidth(600);
		root.getChildren().add(htmlEditor);

		templateName.relocate(650, 100);
		templateName.setPrefWidth(200);
		templateName.setPromptText("Template name");
		root.getChildren().add(templateName);

		save.relocate(650, 150);
		save.setPrefSize(200, 40);
		root.getChildren().add(save);

		listView.relocate(650, 250);
		listView.setMaxHeight(250);
		listView.setPrefWidth(200);
		root.getChildren().add(listView);

		open.relocate(650, 530);
		open.setPrefSize(200, 40);
		root.getChildren().add(open);

		stage.setScene(new Scene(root));
		stage.setTitle("Email Template");
		stage.setWidth(900);
		stage.setHeight(800);
		stage.setResizable(false);
		stage.show();

		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(templateName.getText().length() > 0){
					if(!TEMPLATES.contains(findObject(templateName.getText(), TEMPLATES))){
						listView.getItems().add(templateName.getText());
						TEMPLATES.add(new Template(templateName.getText(), htmlEditor.getHtmlText()));
						templateName.setText("");
					}
				}
			}
		});

		open.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Template template = findObject(listView.getSelectionModel().getSelectedItem().toString(), TEMPLATES);
				htmlEditor.setHtmlText(template.getValue());
			}
		});
	}

	public Template findObject(String name, List<Template> templateList) {
		for (Template template : templateList) {
			if (template.getName().equals(name)) {
				return template;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		launch(args);
	}
}