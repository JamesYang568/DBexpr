这是一个Java Swing界面的汽车租赁系统，用于大作业。
A car rental system for Java homework using Swing.

### Brief Introduction
（***Chinese Only***）

整体程序分为四个模块：

Entity模块包含了所有需要传递的信息对象，其中数据全部是根据语义值进行赋值。

Handel模块
    包括DataProcessing，是对数据库的操作接口，所有检验都在类内进行；Create|Search|Delete|Update_SQL_sen是高度封装的SQL语句，方便调用，降低耦合；
ParseEntity是专门用于对数据块进行数组向量整合的，同时还包含了sql.date和util.date的转换。

DialogGUI.Help模块进行的是信息提示和与GUI有关的信息处理类
    TableParse用于对表格进行优化，从而降低界面类对数据库的依赖情况，耦合性较高。InputParse用于对输入数据格式化，包括ID和检验和Date的格式规范
    ExitDialog是退出整个程序时弹出的弹窗，使用它的目的是保护进程安全，进程的最后将会在DP类中使用它，作为最后一次调用，防止数据库断连后还有使用的可能
    SQLLinkErr是数据库连接报错，为了降低耦合性，将其作为一个类，可以在任何地方使用。
    
DialogGUI.Interface是图形界面的主要模块，其中包括了服务端、客户端的界面类以及一些共用的接口
    整个程序从welcome运行，下边界在welcome类（调用的起点），所有的功能使用都必须经过welcome的许可。
    服务端的主界面为ServerConsole，客户端的主界面为ClientConsole，整个程序的其他主功能都需要被这两个类调用
    welcome和xxxConsole无关，因此不存在耦合关系，(数据库可以在任何时间崩溃，welcome仍然可以直接运行。)
    界面主要以JPanel实现，内聚较高。
    
### Acknowledgement
感谢并赞美与罗文平的共同合作！

Many thanks and appreciation for cooperation with my friend Wenping Luo.

### Contact
James Yang --->>> 2625398619@qq.com

Wenping Luo --->>> 2061786671@qq.com
