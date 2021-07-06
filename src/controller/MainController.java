package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import model.TextParser;

public class MainController implements Initializable {
	File selDir;

	@FXML
	private TextField fileDirTxt;

	@FXML
	private TextArea parsedTxt;

	@FXML
	private ComboBox<String> langComboBox;

	@FXML
	void onHandleCopyText(ActionEvent event) {
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		content.putString(parsedTxt.getText());
		clipboard.setContent(content);
	}

	@FXML
	void onHandleFileSelection(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("File Selection");
		selDir = fileChooser.showOpenDialog(null);
		if (selDir != null) {
			fileDirTxt.setText(selDir.getAbsolutePath());
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(null);
			alert.setTitle("File Selection Error");
			alert.setHeaderText("The selected file could not be recognized");
			alert.setContentText("If this problem presists, manually paste the directory");
			alert.showAndWait();
			fileDirTxt.setText("");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		langComboBox.getItems().setAll(new ArrayList<>(Arrays.asList("English", "French", "Arabic", "Persian", "Urdu",
				"Hindi", "Gujarati", "Russian", "Tajik")));
		langComboBox.getSelectionModel().select(0);

		fileDirTxt.textProperty().addListener((observable, oldVal, newVal) -> {
			if (!newVal.isBlank()) {
				TextParser tp = new TextParser();
				parsedTxt.setText(tp.decipher(selDir, langComboBox.getSelectionModel().getSelectedItem()));
			}
		});

	}

}
