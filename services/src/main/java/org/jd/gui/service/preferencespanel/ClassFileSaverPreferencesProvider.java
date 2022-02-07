/*
 * Copyright (c) 2008-2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */

package org.jd.gui.service.preferencespanel;

import org.jd.gui.spi.PreferencesPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import static jd.core.preferences.Preferences.WRITE_LINE_NUMBERS;
import static jd.core.preferences.Preferences.WRITE_METADATA;

public class ClassFileSaverPreferencesProvider extends JPanel implements PreferencesPanel {

    private static final long serialVersionUID = 1L;

    protected JCheckBox writeLineNumbersCheckBox;
    protected JCheckBox writeMetadataCheckBox;

    public ClassFileSaverPreferencesProvider() {
        super(new GridLayout(0,1));

        writeLineNumbersCheckBox = new JCheckBox("Write original line numbers");
        writeMetadataCheckBox = new JCheckBox("Write metadata");

        add(writeLineNumbersCheckBox);
        add(writeMetadataCheckBox);
    }

    // --- PreferencesPanel --- //
    @Override
    public String getPreferencesGroupTitle() { return "Source Saver"; }
    @Override
    public String getPreferencesPanelTitle() { return "Class file"; }
    @Override
    public JComponent getPanel() { return this; }

    @Override
    public void init(Color errorBackgroundColor) {}

    @Override
    public boolean isActivated() { return true; }

    @Override
    public void loadPreferences(Map<String, String> preferences) {
        writeLineNumbersCheckBox.setSelected(!"false".equals(preferences.get(WRITE_LINE_NUMBERS)));
        writeMetadataCheckBox.setSelected(!"false".equals(preferences.get(WRITE_METADATA)));
    }

    @Override
    public void savePreferences(Map<String, String> preferences) {
        preferences.put(WRITE_LINE_NUMBERS, Boolean.toString(writeLineNumbersCheckBox.isSelected()));
        preferences.put(WRITE_METADATA, Boolean.toString(writeMetadataCheckBox.isSelected()));
    }

    @Override
    public boolean arePreferencesValid() { return true; }

    @Override
    public void addPreferencesChangeListener(PreferencesPanel.PreferencesPanelChangeListener listener) {}
}