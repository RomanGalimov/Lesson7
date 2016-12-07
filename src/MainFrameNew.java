import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Roman Galimov
 */
public class MainFrameNew extends JFrame {

    private MyList inputList;
    private MyList outputList;
    private JComboBox<String> comboBox;
    private final static Color[] COLORS = {Color.WHITE, Color.LIGHT_GRAY, Color.GRAY};

    public MainFrameNew() throws Exception {
        super("MainFrameNew");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        initComponents();
    }

    private void initComponents() {
        inputList = new MyList();
        outputList = new MyList(false);
        JButton button = new JButton("Сортировать");
        button.addActionListener(e -> {
            outputList.setValues(inputList.getValues().stream()
                    .filter(value -> value.matches("-?\\d+(.\\d+)?"))
                    .sorted(Comparator.comparing(Double::valueOf))
                    .collect(Collectors.toList()));
            outputList.setBackground(getColor());
        });
        add(inputList);
        comboBox = new JComboBox<>(new String[]{"Вариант 1", "Вариант 2", "Вариант 3"});
        add(comboBox);
        add(button);
        add(outputList);
        pack();
    }

    private Color getColor(){
        int colorIndex = comboBox.getSelectedIndex();
        return COLORS[colorIndex];
    }

    private class MyList extends JTextArea {

        public MyList() {
            super(20, 20);
            setFont(new Font(null, Font.PLAIN, 14));
        }

        public MyList(boolean editable) {
            this();
            setEditable(editable);
        }

        public List<String> getValues() {
            return Arrays.asList(getText().split("\n"));
        }

        public void setValues(List<String> values) {
            setText(String.join("\n", values));
        }

    }

}