package com.company;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
/**
 * Created by wuyan on 2017/8/22.
 */
public class client extends JFrame implements  TreeSelectionListener {
    JSplitPane js;
    JTree jt;
    JPanel jp;
    Loan p1;
    Repay p2;
    PayPlan p3;
    CardLayout card;
    Record p4;

    public  client(){
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("借款");
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("还款");
        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("退出");

        node2.add(new DefaultMutableTreeNode("开始还款"));
        node2.add(new DefaultMutableTreeNode("还款计划"));
        node2.add(new DefaultMutableTreeNode("还款记录"));
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("贷款管理");
        root.add(node1);
        root.add(node2);
        root.add(node3);
        jt = new JTree(root);

        card=new CardLayout();
        jp=new JPanel(card);
        p1=new Loan();
        p2=new Repay();
        p3=new PayPlan();
        p4=new Record();
        jp.add(p1,"p1");
        jp.add(p2,"p2");
        jp.add(p3,"p3");
        jp.add(p4,"p4");

        js=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jt,jp);
        js.setOneTouchExpandable(true);
        js.setDividerLocation(120);
        this.add(js);
        this.setTitle("欢迎进入");
        this.setLocation(300,200);
        this.setSize(800,600);
        this.setVisible(true);
        this.setLocation(600,200);
        jt.addTreeSelectionListener(this);
    }
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        if(e.getSource()==jt){
            DefaultMutableTreeNode node= (DefaultMutableTreeNode) jt.getLastSelectedPathComponent();
                if(node.isLeaf()){
                String str=node.toString();
                System.out.println(str);
                if (str.equals("借款")){
                    //进入借款，可选还款期数
                    card.show(jp,"p1");
                }
               else if(str.equals("开始还款")){
                    //卡片布局进入已还款界面
                    card.show(jp,"p2");
                }
                else if(str.equals("还款计划")){
                    //卡片布局进入计划界面
                    card.show(jp,"p3");
                }
               else if(str.equals("还款记录")){
                    //卡片布局进入还款
                    //new Repay();
                    card.show(jp,"p4");
                }
                else if(str.equals("退出")){
                   System.exit(0);
                }
            }
        }
    }
}
