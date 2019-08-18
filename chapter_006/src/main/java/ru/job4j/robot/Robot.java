package ru.job4j.robot;

import java.util.*;

public class Robot {

    public List<Integer> optimalWay(int[][] board, int sx, int sy, int fx, int fy) {
        List<Integer> result = null;
        Deque<Integer> transitory = new LinkedList<>();
        int transitSum;
        int transitSteps;
        int resultSum = -1;
        int resultSteps = -1;
        if (checkData(board, sx, sy, fx, fy)) {
            List<Node> leaves = getTreeLeaves(board, sx, sy, fx, fy);
            for (Node node : leaves) {
                while (node != null) {
                    transitory.addFirst(node.getValue());
                    node = node.getParent();
                }
                List<Integer> transList = List.copyOf(transitory);
                transitSum = calculateWay(transList);
                transitSteps = transList.size();
                if (resultSum == -1
                        || transitSum < resultSum
                        || (transitSum == resultSum && transitSteps < resultSteps)) {
                    resultSum = transitSum;
                    resultSteps = transitSteps;
                    result = transList;
                }
                transitory.clear();
            }
        }
        return result;
    }

    private int calculateWay(List<Integer> transitory) {
        int result = 0;
        int current;
        int previous;
        if (transitory.size() > 0) {
            current = transitory.get(transitory.size() - 1);
            for (int i = transitory.size() - 2; i > -1; i--) {
                previous = current;
                current = transitory.get(i);
                result += Math.abs(previous - current);
            }
        }
        return result;
    }

    private boolean checkData(int[][] board, int sx, int sy, int fx, int fy) {
        boolean result = true;
        int row = board.length;
        int column = board[0].length;
        for (int i = 1; i < row; i++) {
            if (board[i].length != column) {
                result = false;
                System.out.println("the board is not matrix");
                break;
            }
        }
        if (!(result
                && sx >= 0
                && fx >= sx
                && fx < row
                && sy >= 0
                && fy >= sy
                && fy < column)) {
            result = false;
            System.out.println("the start and/or finish have wrong position");
        }
        return result;
    }

    private List<Node> getTreeLeaves(int[][] board, int sx, int sy, int fx, int fy) {
        List<Node> leaves = new ArrayList<>();
        int rows = board.length;
        Node parent;
        int parentX;
        int parentY;
        int grannyX;
        Node kid;
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(sx, sy, board[sx][sy], null, -1));
        while (!stack.isEmpty()) {
            parent = stack.pop();
            parentX = parent.getX();
            parentY = parent.getY();
            grannyX = parent.getGrannyX();
            boolean kids = false;
            if (parentY < fy) {
                int y = parentY + 1;
                kid = new Node(parentX, y, board[parentX][y], parent, parentX);
                stack.push(kid);
                kids = true;
            }
            if (!(parentX == fx && parentY == fy)) {
                if (parentX > 0 && parentX - 1 != grannyX) {
                    int x = parentX - 1;
                    kid = new Node(x, parentY, board[x][parentY], parent, parentX);
                    stack.push(kid);
                    kids = true;
                }
                if (parentX < rows - 1 && parentX + 1 != grannyX) {
                    int x = parentX + 1;
                    kid = new Node(x, parentY, board[x][parentY], parent, parentX);
                    stack.push(kid);
                    kids = true;
                }
            }
            if (!kids) {
                leaves.add(parent);
            }
        }
        return leaves;
    }
}
