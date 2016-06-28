package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Geldbetrag;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Das UI für eine Barzahlung
 */
public class BarzahlungsWerkzeugUI
{
    private static final String TITEL = "Barzahlung";

    private JDialog _dialog;

    private JButton _okButton;
    private JButton _abbrechenButton;

    private JTextField _gezahltTextField;

    private JLabel _preisLabel;
    private JLabel _restbetragLabel;

    private int _statusCode;

    /**
     * Erstellt eine neue UI
     */
    public BarzahlungsWerkzeugUI()
    {
        _dialog = new JDialog();
        _dialog.setTitle(TITEL);
        _dialog.setModal(true);
        _dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        _dialog.setLayout(new BorderLayout());

        GridLayout layout = new GridLayout(4, 2, 20, 20);
        JPanel contentPane = new JPanel(layout);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel preisTitelLabel = new JLabel("Preis");
        JLabel gezahltTitelLabel = new JLabel("gezahlt");
        JLabel restTitelLabel = new JLabel("Restbetrag");

        _preisLabel = new JLabel(new Geldbetrag().getFormatiertenString());
        _restbetragLabel = new JLabel(new Geldbetrag().getFormatiertenString());
        Font boldFont = _preisLabel.getFont()
                .deriveFont(Font.BOLD)
                .deriveFont(32f);
        _preisLabel.setFont(boldFont);
        _restbetragLabel.setFont(boldFont);

        _gezahltTextField = new JTextField();

        _okButton = new JButton("OK");
        _abbrechenButton = new JButton("Beenden");
        _abbrechenButton.addActionListener(e -> _dialog.dispose());

        contentPane.add(preisTitelLabel);
        contentPane.add(_preisLabel);
        contentPane.add(gezahltTitelLabel);
        contentPane.add(_gezahltTextField);
        contentPane.add(restTitelLabel);
        contentPane.add(_restbetragLabel);
        contentPane.add(_okButton);
        contentPane.add(_abbrechenButton);

        _dialog.getContentPane().add(contentPane, BorderLayout.CENTER);
    }

    /**
     * Öffne den Dialog bzw. zeige ihm den Nutzer an
     */
    public void zeigeDialog()
    {
        _statusCode = 0;

        _dialog.setSize(500, 300);
        _dialog.setVisible(true);
    }

    /**
     * Schließe den Dialog
     *
     * @param statusCode Welcher Code ausgegeben werden soll
     */
    public void schließeFenster(int statusCode)
    {
        _statusCode = statusCode;

        _dialog.dispose();
    }

    /**
     * Gibt den OK-Button zurück
     *
     * @return Eine Referenz auf den OK-Knopf
     */
    public JButton getOkButton()
    {
        return _okButton;
    }

    /**
     * Gibt den Abbrechen-Button zurück
     *
     * @return Eine Referenz auf den Abbrechen-Knopf
     */
    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    /**
     * Gibt das Bezahl-Betrags-Feld zurück
     *
     * @return Eine Referenz auf das Bezahl-Betrags-Feld
     */
    public JTextField getGezahltTextField()
    {
        return _gezahltTextField;
    }

    /**
     * Setze den Text des Preis-Labels auf die String-Repräsentation des Geldbetrags
     *
     * @require preis != null
     * @param preis Der Geldbetrag
     */
    public void setPreisText(Geldbetrag preis)
    {
        assert preis != null : "Vorbedingung verletzt: null";

        _preisLabel.setText(preis.getFormatiertenString());
    }

    /**
     * Setze den Text des Rest-Labels auf die String-Repräsentation des Restbetrags
     *
     * @require rest != null
     * @param rest Der Geldbetrag
     */
    public void setRestbetragText(Geldbetrag rest)
    {
        assert rest != null : "Vorbedingung verletzt: null";

        _restbetragLabel.setText(rest.getFormatiertenString());
    }

    /**
     * Gibt den vom Nutzer eingegebenen Inhalt als Geldbetrag
     *
     * @return den Geldbetrag oder den Geldbetrag "0" falls der Nutzer nichts eingebeben hat
     * @ensure return != null
     */
    public Geldbetrag getEingegebenenBetrag()
    {
        String eurocent = _gezahltTextField.getText().replaceAll("\\D", "");

        Geldbetrag betrag;
        if (eurocent.length() == 0)
        {
            betrag = new Geldbetrag();
        }
        else if (eurocent.length() > 10 || Long.parseLong(eurocent) > Integer.MAX_VALUE)
        {
            betrag = new Geldbetrag(Integer.MAX_VALUE);
        }
        else
        {
            betrag = new Geldbetrag(Integer.parseInt(eurocent));
        }

        return betrag;
    }

    /**
     * Gibt den Statuscode, mit dem das Programm zuletzt geschlossen wurde
     *
     * @return den Statuscode
     */
    public int getStatusCode()
    {
        return _statusCode;
    }
}
