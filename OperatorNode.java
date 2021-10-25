package com.main;

public class OperatorNode extends DataNode{
	//运算符
    public String operator;

    public OperatorNode(DataNode left, DataNode right, String operator) {
        //父类中无用的常量设置为null
        super(null, left, right, 0);
        this.operator = operator;
    }


    //中间节点存放运算符，需空格隔开
    public String toString() {
        return " " + operator + " ";
    }
}
