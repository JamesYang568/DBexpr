package DialogGUI.Help;

import javax.swing.*;

/**
 * 使用InputParse进行文本的格式化和约束
 */
public class InputParse {

    public static String parseDate(String input) {
        input = input.trim();
        if (input.matches("dd-dd-dd"))
            return input;

        if (input.indexOf('.') != -1) {  //中间是使用.分隔的
            return getString(input, ".");
        }
        if (input.indexOf(' ') != -1) { //中间使用空格分割
            return getString(input, " ");
        } else {  //直接给出的如20000630类型
            if (input.length() == 8)
                return input.substring(0, 4) + "-" + input.substring(4, 6) + "-" + input.substring(6, 8);
            else {
                errorMessage(0);
                return input;
            }
        }
    }

    private static String getString(String input, String cat) {
        String[] temp = input.split(cat);
        if (temp.length != 3)
            errorMessage(0);
        else {
            for (String s : temp) {
                if (s.length() != 2) {
                    errorMessage(0);
                    return input;
                }
            }
            return temp[0] + "-" + temp[1] + "-" + temp[2];
        }
        return input;
    }

    public static String parseID(String input) {
        input = input.trim();
        if (input.charAt(0) == '0') {
            errorMessage(1);
        }
        return input;
    }

    //  v=0是日期错误，v=1是id错误  抛出异常
    private static void errorMessage(int v) {
        if (v == 0)
            JOptionPane.showMessageDialog(null, "输入日期格式错误", "出错了", JOptionPane.ERROR_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "输入id号格式错误", "出错了", JOptionPane.ERROR_MESSAGE);
        try {
            throw new Exception("格式错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
