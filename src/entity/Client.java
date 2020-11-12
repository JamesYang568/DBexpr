package entity;

public class Client implements Entity {
    //客户号、密码、客户姓名、客户单位、客户电话、客户地址、邮编、是否有效
    private int id;
    private String password;
    private String name;
    private String company;
    private String tel;
    private String addr;
    private String zipcode;
    private int valid;

    public Client() {
        this.valid = 1;
    }

    public Client(int id, String password, String name, String company, String tel, String addr, String zipcode) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.company = company;
        this.tel = tel;
        this.addr = addr;
        this.zipcode = zipcode;
        this.valid = 1;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getAddr() {
        return addr;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getZipcode() {
        return zipcode;
    }

    public boolean isValid() {
        return this.valid == 1;
    }
}
