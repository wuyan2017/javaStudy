package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;

import static com.company.LoanInfo.*;
import static com.company.Login.nam;
import static com.company.PayPlan.*;
import static com.company.client.card;
import static com.company.client.jp;
import static java.awt.Color.*;
/**
 * Created by wuyan on 2017/8/22.
 */
public class Repay extends JPanel{
    JPanel jp0,jp1,jp2,jp3,jp4;
    JLabel jl1,jl2,jl3,jl0;
    JTextField jtf;
    JButton jb1,jb2;

    Connection conn = null;
    Statement statement = null;
    ResultSet res = null;
    ResultSet res2=null;
    String s;
    static float money=0;//还款金额
    int tol;//剩余金额
    int re;//sql更新金额update
    Date loan_time,loan_time1,loan_time2;
    static  int time;
    static String sql2,sql3;
    static float v_guazhang;
    static  int no;
    float moreMoney01,moreMoney02,moreMoney03,moreMoney04,moreMoney05;
    public Repay(){
        /**
         *@Author:吴焰
         *@Date:15:58 2017/9/27
         *@Description:获取本地时间，即付款时间
         */
        Date date=new Date();
        DateFormat d1 = DateFormat.getDateInstance();
        String now=d1.format(date);

        String nowArray[]=now.split("-");
        String loanTime=d1.format(lo_time);
        String loanTimeArray[]=loanTime.split("-");
        int yearDiff=Integer.valueOf(nowArray[0])-Integer.valueOf(loanTimeArray[0]);
        int monthDiff=Integer.valueOf(nowArray[1])-Integer.valueOf(loanTimeArray[1])+12*yearDiff;
        int dayDiff=Integer.valueOf(nowArray[2])+1-Integer.valueOf(loanTimeArray[2]);
         time=30*monthDiff+dayDiff;
        jl0=new JLabel("应还本金+利息（元）："+String.format("%.2f",benxihe));
        jl1=new JLabel("还款金额（元）：");
        jl2=new JLabel("超期罚息");
        jl2.setForeground(red);
        jtf=new JTextField(5);
        jp0=new JPanel();
        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();
        jp4=new JPanel();
        jb1=new JButton("还款");
        jb2=new JButton("返回");
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            PayPlan p=new PayPlan();
            jp.add(p,"p");
            card.show(jp,"p");
            }
        });
        if(v_flag==1&&v_money1!=0){
            jp1.add(jl1);
            jp1.add(jtf);//显示：还款金额（）元
            jp2.add(jb1);
        }
        if(v_flag==2&&v_money2!=0){
            jp1.add(jl1);
            jp1.add(jtf);//显示：还款金额（）元
            jp2.add(jb1);
        }
        if(v_flag==3&&v_money3!=0){
            jp1.add(jl1);
            jp1.add(jtf);//显示：还款金额（）元
            jp2.add(jb1);
        }
        if(v_flag==4&&v_money4!=0){
            jp1.add(jl1);
            jp1.add(jtf);//显示：还款金额（）元
            jp2.add(jb1);
        }
        if(v_flag==5&&v_money5!=0){
            jp1.add(jl1);
            jp1.add(jtf);//显示：还款金额（）元
            jp2.add(jb1);
        }
        if(flag==6&&v_money6!=0){
            jp1.add(jl1);
            jp1.add(jtf);//显示：还款金额（）元
            jp2.add(jb1);
        }
        jp3.add(jl0);
        jp2.add(jb2);
        jp0.setLayout(new GridLayout(5,2,10,10));
        if(v_flag==1&&time>30&&v_money1!=0){
                jp4.add(jl2);
                jp0.add(jp4);
        }
        if(v_flag==2&&time>60&&v_money2!=0){
            jp4.add(jl2);
            jp0.add(jp4);
        }
        if(v_flag==3&&time>90&&v_money3!=0){
            jp4.add(jl2);
            jp0.add(jp4);
        }
        if(v_flag==4&&time>120&&v_money4!=0){
            jp4.add(jl2);
            jp0.add(jp4);
        }
        if(v_flag==5&&time>150&&v_money5!=0){
            jp4.add(jl2);
            jp0.add(jp4);
        }
        jp0.add(jp3);
        jp0.add(jp1);
        jp0.add(jp2);
        this.add(jp0);
        this.setVisible(true);
        this.setSize(600,400);
        try{
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://192.168.1.214:3306/test";
            String user = "root";
            String password = "chengce214";
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();
            String sql = "SELECT * FROM test_wuyan WHERE  NAME ='"+nam+"'";
            res = statement.executeQuery(sql);
            while (res.next()) {
                total=res.getInt("total");
                loan_time=res.getTime("loan_time");
            }
            float finalBenxihe = benxihe;
            jb1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        s=jtf.getText();
                        money=Float.parseFloat(s);
                        if(number==0){
                            sql3="update test_wuyan set number='1',pay_time='"+now+"',record='"+money+"' WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql3);
                            number++;
                        }

                       else if(number==1){

                            sql3="update test_wuyan set number='2',pay_time02='"+now+"',record2='"+money+"' WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql3);
                            number++;
                            System.out.println(pay_time02);
                            System.out.println(number);
                        }
                       else if(number==2){
                            sql3="update test_wuyan set number='3',pay_time03='"+now+"',record3='"+money+"' WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql3);
                            number++;
                            System.out.println(pay_time03);
                            System.out.println(number);
                        }
                      else  if(number==3){
                            sql3="update test_wuyan set number='4',pay_time04='"+now+"',record4='"+money+"' WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql3);
                            number++;
                            System.out.println(pay_time04);
                            System.out.println(number);
                        }
                        else  if(number==4){
                            sql3="update test_wuyan set number='5',pay_time05='"+now+"',record5='"+money+"' WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql3);
                            number++;
                            System.out.println(pay_time05);
                            System.out.println(number);
                        }
                        else  if(number==5){
                            sql3="update test_wuyan set number='6',pay_time06='"+now+"',record6='"+money+"' WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql3);
                            number++;
                            System.out.println(pay_time06);
                            System.out.println(number);
                        }else  if(number==6){
                            sql3="update test_wuyan set number='7',pay_time07='"+now+"',record6='"+money+"' WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql3);
                            number++;
                            System.out.println(pay_time07);
                            System.out.println(number);
                        }else  if(number==7){
                            sql3="update test_wuyan set number='8',pay_time08='"+now+"',record7='"+money+"' WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql3);
                            number++;
                            System.out.println(pay_time07);
                            System.out.println(number);
                        }else  if(number==8){
                            sql3="update test_wuyan set number='9',pay_time09='"+now+"',record7='"+money+"' WHERE NAME ='"+nam+"'";
                            re = statement.executeUpdate(sql3);
                            number++;
                            System.out.println(pay_time09);
                            System.out.println(number);
                        }
                        if(no==1){
                            if(money>0&&money< finalBenxihe){
                                money= v_guazhang+money;
                                JOptionPane.showMessageDialog(null,"还款金额不够,已挂账...");
                                if(flag==1){
                                    sql2="update test_wuyan set guazhang='"+money+"',pay_time2='"+now+"' WHERE NAME ='"+nam+"'";
                                }else if(flag==2){
                                    sql2="update test_wuyan set guazhang2='"+money+"',pay_time2='"+now+"' WHERE NAME ='"+nam+"'";
                                }else if(flag==3){
                                    sql2="update test_wuyan set guazhang3='"+money+"',pay_time3='"+now+"' WHERE NAME ='"+nam+"'";
                                }else if(flag==4){
                                    sql2="update test_wuyan set guazhang4='"+money+"',pay_time4='"+now+"' WHERE NAME ='"+nam+"'";
                                }else {
                                    sql2="update test_wuyan set guazhang5='"+money+"',pay_time5='"+now+"' WHERE NAME ='"+nam+"'";
                                }
                                re = statement.executeUpdate(sql2);
                            }else if(money>=finalBenxihe&&money<total){
                                if(flag==1){
                                    v_mf1=mf1;
                                    checkSumMoney();

                                }else if(flag==2){
                                    v_mf2=mf2;
                                    if(money>benxihe&&money<2*benxihe){
                                        moreMoney02=money-benxihe;
                                        sql2="update test_wuyan set mf2='1',guazhang3='"+moreMoney02+"'WHERE NAME ='"+nam+"'";
                                        re=statement.executeUpdate(sql2);
                                    }
                                }else if(flag==3){
                                    v_mf3=mf3;
                                    if(money>benxihe&&money<2*benxihe){
                                        moreMoney03=money-benxihe;
                                        sql2="update test_wuyan set mf3='1',guazhang4='"+moreMoney03+"'WHERE NAME ='"+nam+"'";
                                        re=statement.executeUpdate(sql2);
                                    }
                                    else if(money>2*benxihe&&money<3*benxihe){
                                        moreMoney04=money-benxihe;
                                        sql2="update test_wuyan set mf3='1',mf4='1',guazhang5='"+moreMoney04+"'WHERE NAME ='"+nam+"'";
                                        re=statement.executeUpdate(sql2);
                                    }
                                }else if(flag==4){
                                    v_mf4=mf4;
                                    if(money>benxihe&&money<2*benxihe){
                                        moreMoney05=money-benxihe;
                                        sql2="update test_wuyan set mf4='1',guazhang5='"+moreMoney05+"'WHERE NAME ='"+nam+"'";
                                        re=statement.executeUpdate(sql2);
                                    }
                                }else if(flag==5){
                                    v_mf5=mf5;
                                    if(money>=benxihe){
                                        moreMoney05=money-benxihe;
                                        sql2="update test_wuyan set mf5='1'WHERE NAME ='"+nam+"'";
                                        re=statement.executeUpdate(sql2);
                                    }
                                }
                                JOptionPane.showMessageDialog(null,"还款成功！");
                            }else if(money==v_n*benxihe){
                                sql2="update test_wuyan set mf1='1',mf2='1',mf3='1',mf4='1',mf5='1'WHERE NAME ='"+nam+"'";
                                JOptionPane.showMessageDialog(null,"全部还清！");
                                re=statement.executeUpdate(sql2);
                            } else{
                                JOptionPane.showMessageDialog(null,"还款金额错误！");
                            }
                        }
                        /**
                         *@Author:吴焰
                         *@Date:18:17 2017/9/30
                         *@Description:
                         *
                         * 还第二次贷款
                         * sc_guazhang01表示：第二次贷款的挂账，第一期
                         */
                        if(no==2){
                            float money2=v_guazhang+money;
                            if(money>0&&money2<benxihe){
                                JOptionPane.showMessageDialog(null,"还款金额不够,已挂账...");
                                if(sc_flag==1){
                                    sql2="update test_wuyan set sc_guazhang01='"+money2+"' WHERE NAME ='"+nam+"'";

                                }else if(sc_flag==2){
                                    sql2="update test_wuyan set sc_guazhang02='"+money2+"',pay_time2='"+now+"' WHERE NAME ='"+nam+"'";

                                }else if(sc_flag==3){
                                    sql2="update test_wuyan set sc_guazhang03='"+money2+"',pay_time3='"+now+"' WHERE NAME ='"+nam+"'";
                                }else if(sc_flag==4){
                                    sql2="update test_wuyan set sc_guazhang04='"+money2+"',pay_time4='"+now+"' WHERE NAME ='"+nam+"'";
                                }else {
                                    sql2="update test_wuyan set sc_guazhang05='"+money2+"',pay_time5='"+now+"' WHERE NAME ='"+nam+"'";
                                }
                                re = statement.executeUpdate(sql2);
                            }else if(money2>=finalBenxihe){
                                money=v_guazhang+money;
                                if(sc_flag==1){
                                    v_mf1=sc_mf1;
                                    checkSumMoney02();
                                }else if(sc_flag==2){
                                    v_mf2=sc_mf2;
                                    checkSumMoney02();
                                }else if(sc_flag==3){
                                    v_mf3=sc_mf3;
                                    checkSumMoney02();
                                }else if(sc_flag==4){
                                    v_mf4=sc_mf4;
                                    checkSumMoney02();
                                }else if(sc_flag==5){
                                    v_mf5=sc_mf5;
                                    JOptionPane.showMessageDialog(null,"超出本期付款范围");
                                }
                                JOptionPane.showMessageDialog(null,"还款成功！");
                            }else if(money2==v_n*benxihe){
                                sql2="update test_wuyan set sc_mf1='1',sc_mf2='1',sc_mf3='1',sc_mf4='1',sc_mf5='1'WHERE NAME ='"+nam+"'";
                                JOptionPane.showMessageDialog(null,"全部还清！");
                                re=statement.executeUpdate(sql2);
                            }
                        }
                        /**
                         *@Author:吴焰
                         *@Date:14:55 2017/10/13
                         *@Description:
                         *
                         * 第三次贷款的还款
                         */
                    if(no==3){
                        float money2=v_guazhang+money;
                            if(money<money2&&money>0){
                                JOptionPane.showMessageDialog(null,"还款金额不够,已挂账...");
                                if(third_flag==1){
                                    sql2="update test_wuyan set third_guazhang01='"+money+"' WHERE NAME ='"+nam+"'";
                                }else if(third_flag==2){
                                    sql2="update test_wuyan set third_guazhang02='"+money+"' WHERE NAME ='"+nam+"'";
                                }else if(third_flag==3){
                                    sql2="update test_wuyan set third_guazhang03='"+money+"' WHERE NAME ='"+nam+"'";
                                }
                                re = statement.executeUpdate(sql2);
                            }else if(money>=money2&&money<total){

                                /**
                                 *@Author:吴焰
                                 *@Date:15:08 2017/10/13
                                 *@Description:
                                 *
                                 * 暂时未完成
                                 */

                            }
                    }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
    }
    public void checkSumMoney() throws SQLException {
        money=v_guazhang+money;
        if(mf1!=1&&money<2*benxihe){
            moreMoney01=money-benxihe;
            v_money_guazhang2=moreMoney01;
            sql2="update test_wuyan set guazhang='0',mf1='1',guazhang2='"+moreMoney01+"',guazhang3='"+moreMoney02+"',guazhang4='"+moreMoney03+"',guazhang5='"+moreMoney04+"'WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }else if(money<3*benxihe){
            moreMoney01=benxihe;
            moreMoney02=money-2*benxihe;
            v_money_guazhang3=moreMoney02;
            sql2="update test_wuyan set guazhang='0',mf1='1',mf2='1',guazhang2='"+moreMoney01+"',guazhang3='"+moreMoney02+"',guazhang4='"+moreMoney03+"',guazhang5='"+moreMoney04+"' WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }else if(money<4*benxihe){
            moreMoney01=benxihe;
            moreMoney02=money-2*benxihe;
            moreMoney03=money-3*benxihe;
            v_money_guazhang4=moreMoney03;
            sql2="update test_wuyan set guazhang='0',mf1='1',mf2='1',mf3='1',guazhang2='"+moreMoney01+"',guazhang3='"+moreMoney02+"',guazhang4='"+moreMoney03+"',guazhang5='"+moreMoney04+"' WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }else if(money<5*benxihe){
            moreMoney01=benxihe;
            moreMoney02=money-2*benxihe;
            moreMoney03=money-3*benxihe;
            moreMoney04=money-4*benxihe;
            v_money_guazhang4=moreMoney04;
            sql2="update test_wuyan set guazhang='0',mf1='1',mf2='1',mf3='1',mf4='1',guazhang2='"+moreMoney01+"',guazhang3='"+moreMoney02+"',guazhang4='"+moreMoney03+"',guazhang5='"+moreMoney04+"' WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }else if(money<6*benxihe){
            /**
             *@Author:吴焰
             *@Date:14:29 2017/10/13
             *@Description:
             */
        }
    }

    public void checkSumMoney02() throws SQLException {
        money=v_guazhang+money;
        if(money<2*benxihe){
            moreMoney01=money-benxihe;
            v_money_guazhang2=moreMoney01;
            sql2="update test_wuyan set sc_guazhang01='0',sc_mf1='1',sc_guazhang02='"+moreMoney01+"'WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }else if(money<3*benxihe){
            moreMoney01=benxihe;
            moreMoney02=money-2*benxihe;
            v_money_guazhang3=moreMoney02;
            sql2="update test_wuyan set sc_mf1='1',sc_mf2='1',sc_guazhang03='"+moreMoney02+"' WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }else if(money<4*benxihe){
            moreMoney01=benxihe;
            moreMoney02=money-2*benxihe;
            moreMoney03=money-3*benxihe;
            v_money_guazhang4=moreMoney03;
            sql2="update test_wuyan set sc_guazhang01='0',sc_mf1='1',sc_mf2='1',sc_mf3='1',sc_guazhang02='"+moreMoney01+"',sc_guazhang03='"+moreMoney02+"',sc_guazhang04='"+moreMoney03+"',sc_guazhang05='"+moreMoney04+"' WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }else if(money<5*benxihe){
            moreMoney01=benxihe;
            moreMoney02=money-2*benxihe;
            moreMoney03=money-3*benxihe;
            moreMoney04=money-4*benxihe;
            v_money_guazhang4=moreMoney04;
            sql2="update test_wuyan set sc_guazhang01='0',sc_mf1='1',sc_mf2='1',sc_mf3='1',sc_mf4='1',sc_guazhang2='"+moreMoney01+"',sc_guazhang3='"+moreMoney02+"',sc_guazhang4='"+moreMoney03+"',sc_guazhang5='"+moreMoney04+"' WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }
    }

    public void MoneyOneTime() throws SQLException {
        money=v_guazhang+money;
        if(money<2*benxihe){
            moreMoney01=money-benxihe;
            v_money_guazhang2=moreMoney01;
            sql2="update test_wuyan set guazhang='0',mf1='1',guazhang2='"+moreMoney01+"',guazhang3='"+moreMoney02+"',guazhang4='"+moreMoney03+"',guazhang5='"+moreMoney04+"'WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }
    }
    public void MoneyTwoTime() throws SQLException {
        money=v_guazhang+money;
        if(money<3*benxihe&&money>2*benxihe){
            moreMoney01=benxihe;
            moreMoney02=money-benxihe;
            v_money_guazhang2=moreMoney01;
            sql2="update test_wuyan set guazhang='0',mf1='1',guazhang2='"+moreMoney01+"',guazhang3='"+moreMoney02+"',guazhang4='"+moreMoney03+"',guazhang5='"+moreMoney04+"'WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }
    }
    public void MoneyThreeTime() throws SQLException {
        money=v_guazhang+money;
        if(money<4*benxihe&&money>3*benxihe){
            moreMoney01=benxihe;
            moreMoney02=benxihe;
            moreMoney03=money-benxihe*3;
            v_money_guazhang2=moreMoney01;
            sql2="update test_wuyan set guazhang='0',mf1='1',guazhang2='"+moreMoney01+"',guazhang3='"+moreMoney02+"',guazhang4='"+moreMoney03+"',guazhang5='"+moreMoney04+"'WHERE NAME ='"+nam+"'";
            re=statement.executeUpdate(sql2);
        }
    }
}
