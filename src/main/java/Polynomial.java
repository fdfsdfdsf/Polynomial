import java.util.LinkedList;
import java.util.List;

public class Polynomial {
    private final List<Node> nodeList;

    public Polynomial() {
        nodeList = new LinkedList<>();
    }

    public Polynomial(int[] multipliers) {
        nodeList = new LinkedList<>();

        int degree = multipliers.length - 1;
        for (int multiplier : multipliers) {
            if (multiplier == 0) {
                degree--;
            } else {
                nodeList.add(new Node(multiplier, degree--));
            }
        }
    }

    public int size() {
        return nodeList.size();
    }

    public boolean isBigger(Polynomial b) {
        return nodeList.size() > b.size();
    }

    public boolean isSmaller(Polynomial b) {
        return nodeList.size() < b.size();
    }

    public void add(int multiplier, int degree) {
        nodeList.add(new Node(multiplier, degree));
    }

    public void add(int index, int multiplier, int degree) {
        nodeList.add(index, new Node(multiplier, degree));
    }

    public void remove(int index) {
        nodeList.remove(index);
    }

    public Node get(int index) {
        return nodeList.get(index);
    }

    public void set(int index, int multiplier, int degree) {
        nodeList.get(index).setDegree(degree);
        nodeList.get(index).setMultiplier(multiplier);
    }

    public static Polynomial sum(Polynomial a, Polynomial b) {
        return Polynomial.calculation(a, b, Operator.PLUS);
    }

    public static Polynomial sub(Polynomial a, Polynomial b) {
        return Polynomial.calculation(a, b, Operator.MINUS);
    }

    public static Polynomial mul(Polynomial a, Polynomial b) {
        Polynomial result = new Polynomial();

        //Перемножить полиномы
        for (Node i : a.nodeList) {
            for (Node j : b.nodeList) {
                result.add(i.getMultiplier() * j.getMultiplier(), i.getDegree() + j.getDegree());
            }
        }

        //Упрощаем выражение
        for (int i = 0; i < result.size(); i++) {
            for (int j = i + 1; j < result.size(); j++) {
                if (result.get(i).getDegree() == result.get(j).getDegree()) {
                    result.set(i, result.get(i).getMultiplier() + result.get(j).getMultiplier(), result.get(i).getDegree());
                    result.remove(j);
                }
            }
        }

        return result;
    }

    public static Polynomial max(Polynomial a, Polynomial b) {
        return (a.nodeList.size() >= b.nodeList.size()) ? a : b;
    }

    public static Polynomial min(Polynomial a, Polynomial b) {
        return (a.nodeList.size() <= b.nodeList.size()) ? a : b;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Node node : nodeList) {
            sumNodes(node.getMultiplier(), node.getDegree(), result);
        }

        return result.toString()
                .replaceAll("^\\s[+]\\s", "")
                .replaceAll("^\\s[-]\\s", "-");
    }

    private void sumNodes(int multiplier, int degree, StringBuilder result) {
        if (multiplier > 0) {
            if (degree == 0) {
                result.append(" + ").append(multiplier);
            } else if (degree == 1) {
                result.append(" + ").append(multiplier).append("X");
            } else {
                result.append(" + ").append(multiplier).append("X^").append(degree);
            }
        } else if (multiplier < 0) {
            if (degree == 0) {
                result.append(" - ").append(Math.abs(multiplier));
            } else if (degree == 1) {
                result.append(" - ").append(Math.abs(multiplier)).append("X");
            } else {
                result.append(" - ").append(Math.abs(multiplier)).append("X^").append(degree);
            }
        }

    }

    private static Polynomial calculation(Polynomial a, Polynomial b, Operator operator) {
        Polynomial result = new Polynomial();

        //Выравниваем полиномы по длине, путем добавления нулей в начало
        int degree = b.size() - 1;
        while (a.isSmaller(b)) {
            a.add(0, 0, degree--);
        }

        for (Node i : a.nodeList) {
            for (Node j : b.nodeList) {
                if (i.getDegree() == j.getDegree()) {
                    //В зависимости от оператора, производится опреация сложения или вычитания
                    result.nodeList.add(
                            new Node(operator == Operator.PLUS ?
                                    i.getMultiplier() + j.getMultiplier() :
                                    i.getMultiplier() - j.getMultiplier()
                                    , i.getDegree())
                    );
                    break;
                }
            }
        }

        return result;
    }
}

class Node {
    private int multiplier;
    private int degree;

    public Node(int multiplier, int degree) {
        this.multiplier = multiplier;
        this.degree = degree;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getDegree() {
        return degree;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}