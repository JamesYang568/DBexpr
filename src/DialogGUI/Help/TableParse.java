package DialogGUI.Help;

import handle.ParseEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

public class TableParse {
    static ParseEntity parseEntity = new ParseEntity();

    public static void setCarT(JTable table) {
        Vector columnNames = new Vector(Arrays.asList("ID号", "车辆型号", "租金率", "购入日期", "维修日期", "运行公里", "运行小时"));
        DefaultTableModel defaultModel = new DefaultTableModel(parseEntity.ParseCar(), columnNames) {
            public boolean isCellEditable(int row, int column) {// 表格不允许被编辑
                return false;
            }
        };
        table.setModel(defaultModel);
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 7; i++) {
            table.getColumn(table.getModel().getColumnName(i)).setCellRenderer(render);
        }
    }

    public static void setDriverT(JTable table) {
        Vector columnNames = new Vector(Arrays.asList("ID号", "姓名", "参加工作时间"));
        DefaultTableModel defaultModel = new DefaultTableModel(parseEntity.ParseDriver(), columnNames) {
            public boolean isCellEditable(int row, int column) {// 表格不允许被编辑
                return false;
            }
        };
        table.setModel(defaultModel);
        // 设置居中的另外一种方法
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 3; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(render);
        }
    }

    public static void setClientT(JTable table) {
        Vector columnNames = new Vector(Arrays.asList("ID号", "姓名", "单位", "电话", "地址", "邮编"));
        DefaultTableModel defaultModel = new DefaultTableModel(parseEntity.ParseClient(), columnNames) {
            public boolean isCellEditable(int row, int column) {// 表格不允许被编辑
                return false;
            }
        };
        table.setModel(defaultModel);
        // 设置居中
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 6; i++) {
            table.getColumn(table.getModel().getColumnName(i)).setCellRenderer(render);
        }
    }

    public static void setTransactionT(JTable table, int client_id) {
        Vector columnNames = new Vector(Arrays.asList("ID号", "日期", "牌照号", "地点", "往返里程", "行驶时间", "客户号", "司机号"));
        DefaultTableModel defaultModel = new DefaultTableModel(parseEntity.ParseTransaction(client_id), columnNames) {
            public boolean isCellEditable(int row, int column) {// 表格不允许被编辑
                return false;
            }
        };
        table.setModel(defaultModel);
        // 设置居中
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 8; i++) {
            table.getColumn(table.getModel().getColumnName(i)).setCellRenderer(render);
        }
    }
}
