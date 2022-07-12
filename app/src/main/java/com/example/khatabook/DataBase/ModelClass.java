package com.example.khatabook.DataBase;

public class ModelClass {
    private final String mobile,surname,email,address,item;
    private String ptype;
    private String todaydate;
    String name;
    String payment;
    String id;




    public ModelClass(String id, String name, String surname, String email, String address, String mobile, String item, String payment, String ptype, String todaydate) {

            this.id=id;
            this.name=name;
            this.surname=surname;
            this.email=email;
            this.address=address;
            this.mobile=mobile;
            this.item=item;
            this.payment=payment;
            this.ptype=ptype;
            this.todaydate=todaydate;
        }

    public String getMobile() {
        return mobile;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getItem() {
        return item;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(String todaydate) {
        this.todaydate = todaydate;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }



        /* public String getPtype() {
            return ptype;
        }

        public void setPtype(String ptype) {
            this.ptype = ptype;
        }

        public String getName() {
            return name;
        }

    public String getMobile() {
        return mobile;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getTodaydate() {
        return todaydate;
    }

    public void setName(String name) {
            this.name = name;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String gettodaydate() {
            return todaydate;
        }

        public void setTodaydate(String todaydate) {
            this.todaydate = todaydate;
        }
*/

    }
