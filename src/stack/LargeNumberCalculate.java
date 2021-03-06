package stack;

import java.util.Stack;

/**
 * 大数加法
 * 以字符串的形式读入两个数字，再以字符串的形式输出两个数字的和。
 * 输入描述：输入两行，表示两个数字a和b，-109 <= a , b <= 109  ，用双引号括起。
 * 输出描述：输出a+b的值，用双引号括起。
 * 输入例子：
 * "-26"
 * "100"
 * 输出例子：
 * "74"
 */

public class LargeNumberCalculate {
    //把字符串以字符形式放进栈中
    public Stack<Integer> stringToStack(String str) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9')
                stack.push(Integer.valueOf(String.valueOf(c)));
            else
                continue;
        }
        return stack;
    }

    // 大数相加
    public String add(String a, String b) {
        Stack<Integer> stackA = stringToStack(a); // 存放第一个数
        Stack<Integer> stackB = stringToStack(b); // 存放第二个数
        Stack<Integer> stackSum = new Stack<>(); // 存放结果和
        int tempSum; // 两位数求和
        boolean isCarry = false; // 进位标志

        while (!stackA.isEmpty() && !stackB.isEmpty()) {
            tempSum = (Integer) stackA.pop() + (Integer) stackB.pop();
            // 若有进位，加1
            if (isCarry) {
                tempSum++;
                isCarry = false;
            }
            // 位数和大于10，个位数入栈，标志进位
            if (tempSum >= 10) {
                tempSum -= 10;
                stackSum.push(tempSum);
                isCarry = true;
            } else {
                stackSum.push(tempSum);
            }
        }
        // 取不为空的栈
        Stack<Integer> stackTemp = !stackA.isEmpty() ? stackA : stackB;
        while (!stackTemp.isEmpty()) {
            // 若原先有进位
            if (isCarry) {
                int end = (Integer) stackTemp.pop(); // 取出栈中的数
                ++end;
                if (end >= 10) // 大于10，进位
                {
                    end -= 10;
                    stackSum.push(end);
                } else // 小于10，直接入栈
                {
                    stackSum.push(end);
                    isCarry = false;
                }
            }
            // 若原先无进位
            else {
                stackSum.push(stackTemp.pop());
            }
        }
        // 最高位有进位时,直接最后一个数为1
        if (isCarry)
            stackSum.push(1);
        // 把栈中结果转为字符串
        String result = new String();
        while (!stackSum.isEmpty()) {
            result = result.concat(stackSum.pop().toString());
        }
        return result;
    }

    public static void main(String[] args) {
        LargeNumberCalculate largeCalculate = new LargeNumberCalculate();
         String a = "159";
         String b = "73";
//        string a = "6 293 379 654 943 111 722 643 403";
//        string b = "1 523 502 388 432 201 489 337 78";
        System.out.println("和为: " + largeCalculate.add(a, b));
    }

}
