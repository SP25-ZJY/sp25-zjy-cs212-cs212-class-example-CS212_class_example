import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FET extends JFrame {
    private JTextField a;
    private JLabel b;

    public FET() {
        setTitle("FET");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new FlowLayout());

        c();
    }

    private void c() {
        add(new JLabel("This program encrypts reflection mark down files.", JLabel.CENTER));

        a = new JTextField(40);
        add(a);

        JPanel d = new JPanel(new FlowLayout());
        JButton e = new JButton("Browse");
        e.addActionListener(f -> g());
        d.add(e);

        JButton h = new JButton("Encrypt");
        h.addActionListener(f -> k());
        d.add(h);

        JButton i = new JButton("Close");
        i.addActionListener(f -> System.exit(0));
        d.add(i);

        add(d);

        b = new JLabel("", JLabel.CENTER);
        add(b);
    }

    private void g() {
        JFileChooser j = new JFileChooser();
        j.setFileFilter(new FileNameExtensionFilter("U", "txt", "md"));
        int l = j.showOpenDialog(this);
        if (l == JFileChooser.APPROVE_OPTION) {
            File m = j.getSelectedFile();
            a.setText(m.getAbsolutePath());
            n("Selected file: " + m.getName(), Color.GREEN);
        }
    }

    private void k() {
        String o = a.getText();
        if (o.isEmpty()) {
            n("No file selected. Please select a file and try again.", Color.RED);
            return;
        }

        Path p = Paths.get(o);
        if (!Files.exists(p)) {
            n("The file '" + o + "' does not exist.", Color.RED);
            return;
        }

        try {
            String q = new String(Files.readAllBytes(p));
            if (q.startsWith("ENCRYPTED (OFFSET=")) {
                n("The file is already encrypted.", Color.RED);
            } else {
                r(o);
            }
        } catch (IOException s) {
            n("Error: Could not read the file. " + s.getMessage(), Color.RED);
        }
    }

    private void r(String t) {
        try {
            String u = new String(Files.readAllBytes(Paths.get(t)));
            int v = w();
            int x = v - 151212;

            int y = z(v);
            String aa = ab(u, y);
            aa = "ENCRYPTED (OFFSET=" + x + "):\n" + aa;

            Files.write(Paths.get(t), aa.getBytes());
            n("Success: The file has been encrypted successfully.", Color.GREEN);
        } catch (IOException ac) {
            n("Error: Could not encrypt. " + ac.getMessage(), Color.RED);
        }
    }

    private int w() {
        SimpleDateFormat ad = new SimpleDateFormat("MMddyyyy");
        return Integer.parseInt(ad.format(new Date()));
    }

    private int z(int ae) {
        return String.valueOf(ae).chars().map(Character::getNumericValue).sum() % 26;
    }

    private String ab(String af, int ag) {
        StringBuilder ah = new StringBuilder();
        for (char ai : af.toCharArray()) {
            if (Character.isLetter(ai)) {
                char aj = Character.isUpperCase(ai) ? 'A' : 'a';
                char ak = (char) ((ai - aj + ag) % 26 + aj);
                ah.append(ak);
            } else {
                ah.append(ai);
            }
        }
        return ah.toString();
    }

    private void n(String al, Color am) {
        b.setText(al);
        b.setForeground(am);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FET().setVisible(true);
        });
    }
}