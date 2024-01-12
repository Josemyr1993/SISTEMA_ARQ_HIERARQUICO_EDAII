import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileManagerSystem {

    private JFrame frame;
    private JTree fileTree;
    private DefaultTreeModel treeModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FileManagerSystem window = new FileManagerSystem();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FileManagerSystem() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("File Manager System");

        // Create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        // Create the tree model with the root node
        treeModel = new DefaultTreeModel(root);

        // Create the JTree with the model
        fileTree = new JTree(treeModel);

        // Create buttons
        JButton addButton = new JButton("Adicionar");
        JButton deleteButton = new JButton("Apagar");
        JButton uploadButton = new JButton("Carregar Ficheiro");

        // Add action listeners to the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFile();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFile();
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFile();
            }
        });
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(uploadButton);

        // Add components to the frame
        frame.getContentPane().add(new JScrollPane(fileTree), BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addFile() {
        DefaultMutableTreeNode selectedNode = getSelectedNode();
        if (selectedNode != null) {
            Object[] options = {"Criar Pasta", "Criar Ficheiro"};
            int choice = JOptionPane.showOptionDialog(frame, "Escolha o tipo de item a adicionar:", "Adicionar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice == JOptionPane.CLOSED_OPTION) {
                return;
            }

            String itemName = JOptionPane.showInputDialog(frame, "Digite o nome do item:");
            if (itemName != null && !itemName.isEmpty()) {
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(itemName);

                if (choice == 0) {
                    // Criar Pasta
                    treeModel.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
                } else {
                    // Criar Ficheiro
                    treeModel.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
                }

                fileTree.expandPath(new TreePath(selectedNode.getPath())); // Expandir apenas o node pai
            }
        }
    }

    private void deleteFile() {
        DefaultMutableTreeNode selectedNode = getSelectedNode();
        if (selectedNode != null && selectedNode != treeModel.getRoot()) {
            treeModel.removeNodeFromParent(selectedNode);
        }
    }

    private void uploadFile() {
        DefaultMutableTreeNode selectedNode = getSelectedNode();
        if (selectedNode != null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName();
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(fileName);
                treeModel.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
                fileTree.expandPath(new TreePath(selectedNode.getPath())); // Expandir apenas o node pai
            }
        }
    }

    private DefaultMutableTreeNode getSelectedNode() {
        TreePath selectionPath = fileTree.getSelectionPath();
        if (selectionPath != null) {
            return (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
        }
        return (DefaultMutableTreeNode) treeModel.getRoot();
    }

    public static void expandAll(JTree tree) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        expandAll(tree, new TreePath(root), true);
    }

    private static void expandAll(JTree tree, TreePath parent, boolean expand) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();

        if (node.getChildCount() >= 0) {
            for (int i = 0; i < node.getChildCount(); i++) {
                TreeNode child = node.getChildAt(i);
                TreePath path = parent.pathByAddingChild(child);
                expandAll(tree, path, expand);
            }
        }

        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }
}
