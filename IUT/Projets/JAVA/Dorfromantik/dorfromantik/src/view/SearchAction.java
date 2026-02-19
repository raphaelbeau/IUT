package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Classe responsable de la logique de recherche dans le menu des séries.
 */
public class SearchAction implements ActionListener {
    private final JTextField searchField;
    private final List<JButton> seriesButtons;
    private final List<String> seriesNames;
    private final JPanel buttonPanel;

    public SearchAction(JTextField searchField, List<JButton> seriesButtons, List<String> seriesNames, JPanel buttonPanel) {
        this.searchField = searchField;
        this.seriesButtons = seriesButtons;
        this.seriesNames = seriesNames;
        this.buttonPanel = buttonPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchText = searchField.getText().trim().toLowerCase();
        for (int i = 0; i < seriesNames.size(); i++) {
            String seriesName = seriesNames.get(i).toLowerCase();
            JButton button = seriesButtons.get(i);

            if (seriesName.contains(searchText)) {
                button.setVisible(true);
            } else {
                button.setVisible(false);
            }
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
}
