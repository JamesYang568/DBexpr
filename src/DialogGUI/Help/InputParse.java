package DialogGUI.Help;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用InputParse进行文本的格式化和约束
 */
public class InputParse {

    public static String parseDate(String input) {
        input = input.trim();
        if (input.matches("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$"))
            return input;
        else if (input.indexOf('.') != -1) {  //中间是使用.分隔的
            return getString(input, "\\.");  //这里必须要进行转义！！！
        } else if (input.indexOf(' ') != -1) { //中间使用空格分割
            return getString(input, " ");
        } else {  //直接给出的如20000630类型
            if (input.length() == 8)
                return input.substring(0, 4) + "-" + input.substring(4, 6) + "-" + input.substring(6, 8);
            else {
                errorMessage(0);
                return null;
            }
        }
    }

    private static String getString(String input, String cat) {
        String[] temp = input.split(cat);
        if (temp.length != 3)
            errorMessage(0);
        else {
            for (String s : temp) {
                if (s.length() != 2 && s.length() != 4) {
                    errorMessage(0);
                    return input;
                }
            }
            return temp[0] + "-" + temp[1] + "-" + temp[2];
        }
        return null;
    }

    public static String parseID(String input) {
        if (input.equals(""))
            return input;
        input = input.trim();
        if (input.charAt(0) == '0') {
            input = input.substring(1);
            errorMessage(1);
        }
        return input;
    }

    /**
     * 将date 对象转化为字符串
     */
    public static String ParseDate2S(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
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
