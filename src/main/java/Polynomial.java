import java.util.LinkedList;
import java.util.List;

public class Polynomial {
    private final List<Node> nodeList;

    public Polynomial() {
        this.nodeList = new LinkedList<>();
    }

    public Polynomial(int[] multipliers) {
        this.nodeList = new LinkedList<>();

        int degree = multipliers.length - 1;
        for (int multiplier : multipliers) {
            if (multiplier == 0) {
                degree--;
            } else {
                this.nodeList.add(new Node(multiplier, degree--));
            }
        }
    }

    public static Polynomial sum(Polynomial a, Polynomial b) {
        Polynomial max = max(a, b);
        Polynomial min = min(a, b);
        Polynomial result = new Polynomial();

        for (Node i : max.nodeList) {
            for (Node j : min.nodeList) {
                if (i.getDegree() > j.getDegree()) {
                    result.nodeList.add(new Node(i.getMultiplier(), i.getDegree()));
                    break;
                } else if (i.getDegree() == j.getDegree()) {
                    result.nodeList.add(new Node(i.getMultiplier() + j.getMultiplier(), i.getDegree()));
                    break;
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
}

class Node {
    private final int multiplier;
    private final int degree;

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
}
