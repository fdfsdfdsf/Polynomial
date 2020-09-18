import org.junit.Assert;
import org.junit.Test;

public class PolynomialTest {

    @Test
    public void newPolynomialShouldFullPositiveElements() {
        Polynomial a = new Polynomial(new int[]{1, 2, 3});
        Assert.assertEquals("1X^2 + 2X + 3", a.toString());
    }

    @Test
    public void newPolynomialShouldFullNegativeElements() {
        Polynomial a = new Polynomial(new int[]{-1, -2, -3});
        Assert.assertEquals("-1X^2 - 2X - 3", a.toString());
    }

    @Test
    public void newPolynomialShouldPositiveAndNegativeElements() {
        Polynomial a = new Polynomial(new int[]{-1, 2, -3, 4});
        Assert.assertEquals("-1X^3 + 2X^2 - 3X + 4", a.toString());
    }

    @Test
    public void newPolynomialNotShouldZero() {
        Polynomial a = new Polynomial(new int[]{-1, 0, -3, 4});
        Assert.assertEquals("-1X^3 - 3X + 4", a.toString());

        Polynomial b = new Polynomial(new int[]{-1, 0, -3, 4, 0, 6, 7, 0});
        Assert.assertEquals("-1X^7 - 3X^5 + 4X^4 + 6X^2 + 7X", b.toString());
    }

    @Test
    public void sumPolynomials() {
        Polynomial a = new Polynomial(new int[]{1, 2, 3});
        Polynomial b = new Polynomial(new int[]{4, 5, 6, 7});

        //Проверка на перестановку слагаемых
        Polynomial c = Polynomial.sum(a, b);
        Polynomial d = Polynomial.sum(b, a);

        //От перестановки слогаемых сумма не должна меняться
        Assert.assertEquals("4X^3 + 6X^2 + 8X + 10", c.toString());
        Assert.assertEquals("4X^3 + 6X^2 + 8X + 10", d.toString());
    }

    @Test
    public void subPolynomials() {
        Polynomial a = new Polynomial(new int[]{1, 2, 3});
        Polynomial b = new Polynomial(new int[]{4, 5, 6, 7});

        Polynomial c = Polynomial.sub(a, b); // a - b
        Polynomial d = Polynomial.sub(b, a); // b - a

        Assert.assertEquals("-4X^3 - 4X^2 - 4X - 4", c.toString());
        Assert.assertEquals("4X^3 + 4X^2 + 4X + 4", d.toString());
    }
}
