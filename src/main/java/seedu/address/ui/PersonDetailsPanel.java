package seedu.address.ui;

import java.util.Comparator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonDetailsPanel extends UiPart<Region> {
    private static final String FXML = "PersonDetailsPanel/fxml";
    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label company;
    @FXML
    private Circle priorityDot;
    @FXML
    private Label remark;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} to display.
     */

    public PersonDetailsPanel(Person person) {
        super(FXML);
        this.person = person;

        if (person.isStarred()) {
            name.setText(person.getName().fullName + " ★");
        } else {
            name.setText(person.getName().fullName);
        }

        phone.setText("Phone: " + person.getPhone().value);

        setAddress();
        setEmail();
        setCompany();

        String priorityValue = person.getPriority().value;
        if ("high".equals(priorityValue)) {
            priorityDot.setFill(Color.RED);
        } else if ("med".equals(priorityValue)) {
            priorityDot.setFill(Color.ORANGE);
        } else {
            priorityDot.setVisible(false);
        }

        setRemark();

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void setCompany() {
        if (!person.getCompany().value.equals("")) {
            company.setText(person.getCompany().value);
            company.setVisible(true);
        } else {
            company.setPrefHeight(0.0);
        }
    }

    public void setAddress() {
        if (person.getAddress().toString().equals("")) {
            address.setText("Address: -");
        } else {
            address.setText("Address: " + person.getAddress().value);
        }
    }

    public void setEmail() {
        if (person.getEmail().toString().equals("")) {
            email.setText("Email: -");
        } else {
            email.setText("Email: " + person.getEmail().value);
        }
    }

    private void setRemark() {
        if (person.getRemark().toString().equals("")) {
            remark.setText("Remarks: [NONE]");
        } else {
            remark.setText("Remarks: " + person.getRemark().value);
        }
    }

}
