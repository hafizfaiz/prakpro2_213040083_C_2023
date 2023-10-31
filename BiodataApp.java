import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BiodataApp {
    private JFrame frame;
    private JTextField nameField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField phoneNumberField;
    private JTextArea addressArea;
    private JTable table;
    private DefaultTableModel tableModel;

    public BiodataApp() {
        frame = new JFrame("Biodata App");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin keluar?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

                
        JLabel labelnama = new JLabel("Nama:");
        labelnama.setBounds(50, 20, 350, 15);
        nameField = new JTextField();
        nameField.setBounds(100, 20, 200, 20);

        JLabel labeljenis = new JLabel("Jenis Kelamin:");
        labeljenis.setBounds(5, 50, 350, 15);
        maleRadioButton = new JRadioButton("Laki-laki");
        femaleRadioButton = new JRadioButton("Perempuan");
        maleRadioButton.setBounds(100, 50, 100, 20);
        femaleRadioButton.setBounds(200, 50, 100, 20);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        
        JLabel labelnomor = new JLabel("No Hp:");
        labelnomor.setBounds(50, 80, 350, 15);
        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(100, 80, 200, 20);

        JLabel labelalamat = new JLabel("Alamat:");
        labelalamat.setBounds(45, 110, 350, 15);
        addressArea = new JTextArea();
        addressArea.setBounds(100, 110, 200, 60);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nama");
        tableModel.addColumn("Jenis Kelamin");
        tableModel.addColumn("Nomor HP");
        tableModel.addColumn("Alamat");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 250, 500, 150);

        JButton saveButton = new JButton("Simpan");
        saveButton.setBounds(100, 180, 80, 30);
        JButton editButton = new JButton("Edit");
        editButton.setBounds(190, 180, 80, 30);
        JButton deleteButton = new JButton("Hapus");
        deleteButton.setBounds(280, 180, 80, 30);
        JButton exportButton = new JButton("Export");
        exportButton.setBounds(370, 180, 80, 30);
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBiodata();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBiodata();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBiodata();
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportData();
            }
        });
        frame.add(exportButton);
        
        frame.setLayout(null);

        frame.add(labelnama);
        frame.add(labeljenis);
        frame.add(labelnomor);
        frame.add(exportButton);
        frame.add(labelalamat);        
        frame.add(nameField);
        frame.add(maleRadioButton);
        frame.add(femaleRadioButton);
        frame.add(phoneNumberField);
        frame.add(addressArea);
        frame.add(scrollPane);
        frame.add(saveButton);
        frame.add(editButton);
        frame.add(deleteButton);

        frame.setSize(550, 400);
        frame.setVisible(true);
    }

    private void saveBiodata() {
        String name = nameField.getText();
        String gender = maleRadioButton.isSelected() ? "Laki-laki" : "Perempuan";
        String phoneNumber = phoneNumberField.getText();
        String address = addressArea.getText();

        if (name.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Semua input harus diisi", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.addRow(new String[]{name, gender, phoneNumber, address});
        nameField.setText("");
        maleRadioButton.setSelected(true);
        phoneNumberField.setText("");
        addressArea.setText("");
    }

    private void editBiodata() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String name = nameField.getText();
            String gender = maleRadioButton.isSelected() ? "Laki-laki" : "Perempuan";
            String phoneNumber = phoneNumberField.getText();
            String address = addressArea.getText();

            if (name.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Semua input harus diisi", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            tableModel.setValueAt(name, selectedRow, 0);
            tableModel.setValueAt(gender, selectedRow, 1);
            tableModel.setValueAt(phoneNumber, selectedRow, 2);
            tableModel.setValueAt(address, selectedRow, 3);
            nameField.setText("");
            maleRadioButton.setSelected(true);
            phoneNumberField.setText("");
            addressArea.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih baris untuk diedit", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteBiodata() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih baris untuk dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BiodataApp();
            }
        });
    }
    
     private void exportData() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showSaveDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file + ".txt"))) {
                writer.write("Nama, Jenis Kelamin, No Hp, Alamat\n");
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    for (int col = 0; col < tableModel.getColumnCount(); col++) {
                        writer.write(tableModel.getValueAt(row, col).toString());
                        if (col < tableModel.getColumnCount() - 1) {
                            writer.write(", ");
                        }
                    }
                    writer.write("\n");
                }
                JOptionPane.showMessageDialog(frame, "Data berhasil diekspor ke " + file + ".txt", "Info", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Gagal mengekspor data.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    
}
