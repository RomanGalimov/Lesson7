import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * @author Roman Galimov
 */
public class MainFrameOld extends JFrame {

    private MyList inputList;
    private MyList outputList;
    private JComboBox<String> comboBox;
    private final static Color[] COLORS = {Color.WHITE, Color.LIGHT_GRAY, Color.GRAY};

    public MainFrameOld() throws Exception {
        super("MainFrameOld");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        initComponents();
    }

    private void initComponents() {
        inputList = new MyList();
        outputList = new MyList(false);
        JButton sortButton = new JButton("Сортировать");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> values = inputList.getValues();
                ArrayList<String> numbers = new ArrayList<>();
                for (String value : values) {
                    if(value.matches("-?\\d+")) {
                        numbers.add(value);
                    }
                }
                Collections.sort(numbers, new Comparator<String>() {
                    @Override
                    public int compare(String a, String b) {
                        return Integer.valueOf(a).compareTo(Integer.valueOf(b));
                    }
                });
                outputList.setValues(numbers);
                outputList.setBackground(getColor());
            }
        });
        add(inputList);
        comboBox = new JComboBox<>(new String[]{"Вариант 1", "Вариант 2", "Вариант 3"});
        add(comboBox);
        add(sortButton);
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

        public List<String> getValues(){
            return Arrays.asList(getText().split("\n"));
        }

        public void setValues(List<String> values) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Object value : values) {
                stringBuilder.append(value.toString()).append("\n");
            }
            setText(stringBuilder.toString());
        }

    }

}