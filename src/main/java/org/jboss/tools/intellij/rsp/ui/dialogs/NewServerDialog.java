package org.jboss.tools.intellij.rsp.ui.dialogs;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jboss.tools.rsp.api.dao.Attributes;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Map;

public class NewServerDialog extends DialogWrapper implements DocumentListener {
    private final Attributes optional;
    private final Attributes required;
    private AttributesPanel requiredPanel;
    private AttributesPanel optionalPanel;
    private Map<String, Object> attributeValues;
    private JTextField nameField;
    private String name;
    private JPanel contentPane;

    public NewServerDialog(Attributes required, Attributes optional, Map<String, Object> values) {
        super((Project)null, true, IdeModalityType.IDE);
        this.required = required;
        this.optional = optional;
        this.attributeValues = values;
        setTitle("Create a new Server...");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        createLayout();
        return contentPane;
    }

    private void createLayout() {
        requiredPanel = new AttributesPanel(required, "Required Attributes", attributeValues);
        optionalPanel = new AttributesPanel(optional, "Optional Attributes", attributeValues);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        JLabel name = new JLabel("Server Name: ");
        contentPane.add(name);
        nameField = new JTextField();
        contentPane.add(nameField);
        contentPane.add(requiredPanel);
        contentPane.add(optionalPanel);
        nameField.getDocument().addDocumentListener(this);

    }

    public String getName() {
        return name;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        name = nameField.getText();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        name = nameField.getText();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        name = nameField.getText();
    }
}
